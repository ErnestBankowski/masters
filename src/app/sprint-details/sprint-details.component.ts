import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../shared/project/project.service';
import { UserService } from '../shared/user/user.service';
import { SprintService } from '../shared/sprint/sprint.service';
import { FunctionalityService } from '../shared/functionality/functionality.service';
import { NgForm } from '@angular/forms';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {startWith} from 'rxjs/operators/startWith';
import {map} from 'rxjs/operators/map';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-sprint-details',
  templateUrl: './sprint-details.component.html',
  styleUrls: ['./sprint-details.component.css']
})
export class SprintDetailsComponent implements OnInit, OnDestroy {
  sprint: any = {};
  functionalityColumns = ['id', 'name', 'state', 'creator', 'creationDate'];
  functionalities: any[] = [];
  sub: Subscription;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private sprintService: SprintService, 
    private functionalityService: FunctionalityService) { }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
  
  ngOnInit() {
    var id = "";
    this.sub = this.route.params.subscribe(params => {
      id = params['id'];
      if (id) {
        this.sprintService.get(id).subscribe((sprint: any) => {
          if (sprint) {
            this.sprint = sprint;
            this.sprint.href = sprint._links.self.href;
          }
        });
      }
    });

    this.functionalityService.getAll(id).subscribe(data => {
      var temp: any[] = [];
      for (const fetchedFunc of data) {
        temp.push({
          id: fetchedFunc.id,
          name: fetchedFunc.name,
          creator: fetchedFunc.creator, 
          state: fetchedFunc.state, 
          creationDate: fetchedFunc.creationDate});
      }
      this.functionalities = temp;
    });
  }

}
