import { Component, OnDestroy, OnInit, Inject} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import  {MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SprintService } from '../shared/sprint/sprint.service';
import { FunctionalityService } from '../shared/functionality/functionality.service';

@Component({
  selector: 'app-usecase-edit',
  templateUrl: './usecase-edit.component.html',
  styleUrls: ['./usecase-edit.component.css']
})
export class UsecaseEditComponent implements OnInit, OnDestroy {
  functionality: any;
  sprints: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private functionalityService: FunctionalityService, 
    private sprintService: SprintService,
    public dialogRef: MatDialogRef<UsecaseEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      this.functionality = data.id;
    }

  ngOnInit() {
  }

  gotoList() {
    this.router.navigateByUrl('/project-list', {skipLocationChange: true}).then(()=>
    this.router.navigate(['/functionality-details/'+this.functionality]));
  }

  closeDialog() {
    this.dialogRef.close();
  }

  ngOnDestroy() {
  }

  save(form: NgForm) {
    this.functionalityService.saveUsecase(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
