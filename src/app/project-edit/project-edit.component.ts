import { Component, OnDestroy, OnInit, Inject} from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../shared/project/project.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit, OnDestroy {
  project: any = {};
  sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService,
    private giphyService: GiphyService,
    public dialogRef: MatDialogRef<ProjectEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){

  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.projectService.get(id).subscribe((project: any) => {
          if (project) {
            this.project = project;
            this.project.href = project._links.self.href;
            this.giphyService.get(project.projectName).subscribe(url => project.giphyUrl = url);
          } else {
            console.log(`Project with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/project-list']);
    this.dialogRef.close();
  }

  save(form: NgForm) {
    this.projectService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  closeDialog() {
    this.dialogRef.close();
  }

  remove(href) {
    this.projectService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
