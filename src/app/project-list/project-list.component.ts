import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../shared/project/project.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import {MatTabsModule} from '@angular/material/tabs';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Array<any>;

  constructor(private projectService: ProjectService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.projectService.getAll().subscribe(data => {
      this.projects = data;
      for (const project of this.projects) {
        this.giphyService.get(project.projectName).subscribe(url => project.giphyUrl = url);
      }
    });
  }

}
