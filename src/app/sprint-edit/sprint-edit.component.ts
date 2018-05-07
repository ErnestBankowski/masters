import { Component, OnDestroy, OnInit, Inject} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import  {MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SprintService } from '../shared/sprint/sprint.service';

@Component({
  selector: 'app-sprint-edit',
  templateUrl: './sprint-edit.component.html',
  styleUrls: ['./sprint-edit.component.css']
})
export class SprintEditComponent implements OnInit, OnDestroy {
  project: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private sprintService: SprintService, 
    public dialogRef: MatDialogRef<SprintEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      this.project = data.id;
    }

  ngOnInit() {
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
    this.sprintService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
