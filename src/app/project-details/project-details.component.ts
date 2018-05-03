import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../shared/project/project.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit, OnDestroy{
  project: any = {};
  sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
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
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/project-list']);
  }

}
