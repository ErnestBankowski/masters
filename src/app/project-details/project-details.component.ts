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
  roles: Role[] = [{role: "Project Manager"}, {role: "Architect"}, {role: "Developer"}, {role: "Tester"}];
  displayedColumns = ['email', 'role', 'enrollTime'];
  sprintColumns = ['name', 'creator', 'start', 'end'];

  constructor(private route: ActivatedRoute, private router: Router, private projectService: ProjectService, private userService: UserService, private sprintService: SprintService) {
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
      for (const fetchedSprint of data) {
        temp.push({
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


  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  saveParticipant(form: NgForm) {
    this.projectService.saveParticipant(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  saveSprint(form: NgForm) {
    this.sprintService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));;
  }

  gotoList() {
    this.router.navigate(['/project-list']);
  }

}

export interface Element {
  email: string;
  role: string;
  enrollTime: string;
}
