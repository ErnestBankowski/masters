import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../shared/project/project.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProjectEditComponent } from '../project-edit/project-edit.component';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Array<any>;

  constructor(private projectService: ProjectService, private giphyService: GiphyService, public createProjectDialog: MatDialog) { }

  ngOnInit() {
    this.projectService.getAll().subscribe(data => {
      this.projects = data;
      for (const project of this.projects) {
        this.giphyService.get(project.projectName).subscribe(url => project.giphyUrl = url);
      }
    });
  }

  openCreateProjectDialog() {
    let dialogRef = this.createProjectDialog.open(ProjectEditComponent);
  }

}
