import { Component, OnDestroy, OnInit, Inject} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import  {MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SprintService } from '../shared/sprint/sprint.service';
import { FunctionalityService } from '../shared/functionality/functionality.service';

@Component({
  selector: 'app-functionality-edit',
  templateUrl: './functionality-edit.component.html',
  styleUrls: ['./functionality-edit.component.css']
})
export class FunctionalityEditComponent implements OnInit, OnDestroy {
  project: any;
  sprint: any;
  sprints: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private functionalityService: FunctionalityService, 
    private sprintService: SprintService,
    public dialogRef: MatDialogRef<FunctionalityEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      this.project = data.projectId;
      this.sprint = data.sprintId;
    }

  ngOnInit() {
    this.sprintService.getAll(this.project).subscribe(data => {
      this.sprints = data;
    });
  }

  gotoList() {
    this.router.navigateByUrl('/project-list', {skipLocationChange: true}).then(()=>
    this.router.navigate(['/project-details/'+this.project]));
  }

  closeDialog() {
    this.dialogRef.close();
  }

  ngOnDestroy() {
  }

  save(form: NgForm) {
    this.functionalityService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  isSprintIdProvided() {
    return this.sprint && !this.project;
  }

}
