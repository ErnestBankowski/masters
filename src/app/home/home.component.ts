import { OktaAuthService } from '@okta/okta-angular';
import { UserService } from '../shared/user/user.service';
import { ProjectService } from '../shared/project/project.service';
import { SprintService } from '../shared/sprint/sprint.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
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

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  isAuthenticated: boolean;
  functionalities: any[] = [];
  projects: any[] = [];
  sprints: any[] = [];
  functionalityColumns = ['id', 'name', 'state', 'sprint', 'project', 'creator', 'creationDate'];
  projectColumns = ['id', 'name', 'owner', 'start', 'end'];
  sprintColumns = ['id', 'name', 'creator', 'project', 'start', 'end'];

  constructor(
    private oktaAuth: OktaAuthService, 
    private userService: UserService,
    private functionalityService: FunctionalityService,
    private projectService: ProjectService,
    private sprintsService: SprintService,
    public createProjectDialog: MatDialog) {
  }

  async ngOnInit() {
    this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    // Subscribe to authentication state changes
    this.oktaAuth.$authenticationState.subscribe(
      (isAuthenticated: boolean)  => {
        this.isAuthenticated = isAuthenticated;
        if(this.isAuthenticated){ 
          this.userService.authenticate();
        }
      }
    );

    this.functionalityService.getForUser().subscribe(data => {
      var temp: any[] = [];
      for (const fetchedFunc of data) {
        temp.push({
          id: fetchedFunc.id,
          name: fetchedFunc.name,
          creator: fetchedFunc.creator, 
          state: fetchedFunc.state, 
          creationDate: fetchedFunc.creationDate,
          sprint: fetchedFunc.sprint});
      }
      this.functionalities = temp;
    });

    this.projectService.getForUser().subscribe(data => {
      var temp: any[] = [];
      for(const project of data) {
        temp.push({
          id: project.id,
          owner: project.projectOwnerId,
          start: project.projectStartTime,
          end: project.projectEndTime,
          name: project.projectName
        });
      }
      this.projects = temp;
    });

    this.sprintsService.getForUser().subscribe(data => {
      var temp: any[] = [];
      for(const sprint of data) {
        temp.push({
          id: sprint.id,
          creator: sprint.creator,
          project: sprint.project,
          start: sprint.sprintStartTime,
          end: sprint.sprintEndTime,
          name: sprint.sprintName
        });
      }
      this.sprints = temp;
    });
  }

  openCreateProjectDialog() {
    let dialogRef = this.createProjectDialog.open(ProjectEditComponent);
  }

}