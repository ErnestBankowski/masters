import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../shared/project/project.service';
import { UserService } from '../shared/user/user.service';
import { SprintService } from '../shared/sprint/sprint.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SprintEditComponent } from '../sprint-edit/sprint-edit.component';
import { ProjectEditComponent } from '../project-edit/project-edit.component';
import { FunctionalityEditComponent } from '../functionality-edit/functionality-edit.component';
import { FunctionalityService } from '../shared/functionality/functionality.service';

export class User {
  constructor(public email: string) { }
}

export class Role {
  constructor(public role: string) { }
}

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit, OnDestroy{
  project: any = {};
  sub: Subscription;
  userControl : FormControl = new FormControl();
  filteredUsers: Observable<any[]>;
  users: any[] = [];
  projectParticipants: any[] = [];
  sprints: any[] = [];
  functionalities: any[] = [];
  roles: Role[] = [{role: "Project Manager"}, {role: "Architect"}, {role: "Developer"}, {role: "Tester"}];
  displayedColumns = ['email', 'role', 'enrollTime'];
  sprintColumns = ['name', 'creator', 'start', 'end'];
  functionalityColumns = ['id', 'name', 'state', 'sprint', 'creator', 'creationDate'];
  userRoles: any[] = [];
  isProjectManager: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService, 
    private userService: UserService, 
    private sprintService: SprintService, 
    private functionalityService: FunctionalityService,
    public createSprintDialog: MatDialog,
    public createFunctionalityDialog: MatDialog) {
      this.filteredUsers = this.userControl.valueChanges
        .pipe(
          startWith(''),
          map(user => this.filterUsers(user))
        );
    }

    filterUsers(email: string) {
      return this.users.filter(user =>
      user.email.toLowerCase().indexOf(email.toLowerCase()) === 0);
    }

  ngOnInit() {
    var id = "";
    this.sub = this.route.params.subscribe(params => {
      id = params['id'];
      if (id) {
        this.projectService.get(id).subscribe((project: any) => {
          if (project) {
            this.project = project;
            this.project.href = project._links.self.href;
          } else {
            console.log(`Project with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });

        this.projectService.getUserRoles(id).subscribe(data => {
          this.isProjectManager = (data.indexOf("Project Manager") > -1);     
        });
      }
    });
   
    this.projectService.getParticipants(id).subscribe(data => {
      var temp: any[] = [];
      for (const fetchedParticipant of data) {
        temp.push({email: fetchedParticipant.participant.email, role: fetchedParticipant.role, enrollTime: fetchedParticipant.enrollTime});
      }
      this.projectParticipants = temp;
    });

    this.sprintService.getAll(id).subscribe(data => {
      var temp: any[] = [];
      for (var fetchedSprint of data) {
        temp.push({
          id: fetchedSprint.id,
          name: fetchedSprint.sprintName,
          creator: fetchedSprint.creator.email, 
          start: fetchedSprint.sprintStartTime, 
          end: fetchedSprint.sprintEndTime});        
      }
      this.sprints = temp;
    });


    this.userService.getAll().subscribe(data => {
      for (const fetchedUser of data) {
        this.users.push(fetchedUser);
      }
    });

    this.functionalityService.getForProject(id).subscribe(data => {
      var temp: any[] = [];
      for (const func of data) {
        temp.push({
          id: func.id,
          name: func.name,
          creator: func.creator, 
          state: func.state, 
          creationDate: func.creationDate,
          sprint: func.sprint});        
      }
      this.functionalities = temp;
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  saveParticipant(form: NgForm) {
    this.projectService.saveParticipant(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  gotoList() {
    this.router.navigateByUrl('/home', {skipLocationChange: true}).then(()=>
    this.router.navigate(['/project-details/'+this.project.id]));
  }

  openCreateSprintDialog() {
    let dialogRef = this.createSprintDialog.open(SprintEditComponent, {
      data: { id: this.project.id }
    });
  }

  openCreateFunctionalityDialog() {
    let dialogRef = this.createFunctionalityDialog.open(FunctionalityEditComponent, {
      data: { projectId: this.project.id }
    });
  }

}

export interface Element {
  email: string;
  role: string;
  enrollTime: string;
}
