import { Component, OnInit, OnDestroy } from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { FunctionalityService } from '../shared/functionality/functionality.service'
import { FunctionalityState } from '../shared/functionality/functionality.service'

@Component({
  selector: 'app-functionality-details',
  templateUrl: './functionality-details.component.html',
  styleUrls: ['./functionality-details.component.css']
})
export class FunctionalityDetailsComponent implements OnInit, OnDestroy {

  functionality: any = {};
  sub: Subscription;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private functionalityService: FunctionalityService
  ) { }

  ngOnInit() {
    var id = "";
    this.sub = this.route.params.subscribe(params => {
      id = params['id'];
      if (id) {
        this.functionalityService.get(id).subscribe((functionality: any) => {
          if (functionality) {
            this.functionality = functionality;
            this.functionality.href = functionality._links.self.href;
          }
        });
      }
    });

  }

  promote() {
    this.functionality.state = FunctionalityState.ASSIGNED;
    this.functionalityService.save(this.functionality).subscribe(result => {
    }, error => console.error(error));
  } 

  createUseCase() {}

  createSpecification() {}
  
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
