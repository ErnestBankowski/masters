import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { FunctionalityService, FunctionalityState, TestCaseState } from '../shared/functionality/functionality.service';
import { ProjectService } from '../shared/project/project.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UsecaseEditComponent } from '../usecase-edit/usecase-edit.component';
import { SpecificationEditComponent } from '../specification-edit/specification-edit.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-functionality-details',
  templateUrl: './functionality-details.component.html',
  styleUrls: ['./functionality-details.component.css']
})
export class FunctionalityDetailsComponent implements OnInit, OnDestroy {

  functionality: any = {};
  specification: any = {};
  usecase: any = {};
  testSteps: any[] = [];
  users: any[] = [];
  sub: Subscription;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private functionalityService: FunctionalityService,  
    private projectService: ProjectService,  
    public createSpecificationDialog: MatDialog,
    public createUseCaseDialog: MatDialog,
    public createUseCaseStepDialog: MatDialog,
    public snackBar: MatSnackBar,
  ) { }

  ngOnInit() {
    var id = "";
    this.specification.creator = {};
    this.usecase.creator = {};

    this.sub = this.route.params.subscribe(params => {
      id = params['id'];
      if (id) {
        this.functionalityService.get(id).subscribe((functionality: any) => {
          if (functionality) {
            this.functionality = functionality;
            this.projectService.getParticipants(this.functionality.sprint.project.id).subscribe(data => {
              var temp: any[] = [];
              for (const fetchedParticipant of data) {
                temp.push({email: fetchedParticipant.participant.email, role: fetchedParticipant.role, enrollTime: fetchedParticipant.enrollTime});
              }
              this.users = temp;
            });    
          }
        });

        this.functionalityService.getSpecification(id).subscribe((specification: any) => {
          if (specification) {
            this.specification = specification;
            this.specification.href = specification._links.self.href;
          }
        });

        this.functionalityService.getUseCase(id).subscribe((usecase: any) => {
          if (usecase) {
            this.usecase = usecase;
            this.functionalityService.getTestSteps(this.usecase.id).subscribe(data => {
              for (const step of data) {
                this.testSteps.push(step);
              }
            });
          }
        });
      }
    });

  }

  promote() {
    if(this.functionality.state == FunctionalityState.NEW) {
      if(!this.specification.id || !this.usecase.id) {
        this.snackBar.open('You have to create specification and use case in order to proceed.', 'close', {
          duration: 5000
        });
        return;
      }
      this.functionality.state = FunctionalityState.SPECIFIED;
    } else if(this.functionality.state == FunctionalityState.SPECIFIED ) {
      this.snackBar.open('You have to assign stakeholders in order to proceed.', 'close', {
        duration: 5000
      });
      return;

    }
    this.functionalityService.save(this.functionality).subscribe(result => {
    }, error => console.error(error));
  } 

  createUseCase() {
    this.functionalityService.saveUsecase({ functionality: this.functionality.id }).subscribe(result => {
    }, error => console.error(error));
  }

  createSpecification() {
    let dialogRef = this.createSpecificationDialog.open(SpecificationEditComponent, {
      data: { id: this.functionality.id }
    });
  }

  createUsecaseStep() {
    let dialogRef = this.createUseCaseStepDialog.open(UsecaseEditComponent, {
      data: { functionality: this.functionality.id, usecase: this.usecase.id }
    });
  }
  
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  approveUc(teststep) {
    teststep.testResult = TestCaseState.PASSED;
    this.functionalityService.saveTestStep(teststep).subscribe(result => {
    }, error => console.error(error));
  }

  failUc(teststep) {
    teststep.testResult = TestCaseState.FAILED;
    this.functionalityService.saveTestStep(teststep).subscribe(result => {
    }, error => console.error(error));
  }

}
