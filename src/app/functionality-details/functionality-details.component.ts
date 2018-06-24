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
import { UserRoles } from '../shared/project/project.service';

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
  testers: any[] = [];
  architects: any[] = [];
  developers: any[] = [];
  sub: Subscription;
  canBePromoted: any;  
  isProjectManager: any;
  isArchitect: any;
  isDeveloper: any;
  isTester: any;

  
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
              for (const fetchedParticipant of data) {
                if(fetchedParticipant.role == UserRoles.ARCHITECT) {
                  this.architects.push({email: fetchedParticipant.participant.email, role: fetchedParticipant.role, enrollTime: fetchedParticipant.enrollTime});                 
                } else if(fetchedParticipant.role == UserRoles.DEVELOPER) {
                  this.developers.push({email: fetchedParticipant.participant.email, role: fetchedParticipant.role, enrollTime: fetchedParticipant.enrollTime});   
                } else if(fetchedParticipant.role == UserRoles.TESTER) {
                  this.testers.push({email: fetchedParticipant.participant.email, role: fetchedParticipant.role, enrollTime: fetchedParticipant.enrollTime});               
                }
              } 
            });    
          }
          if(!this.functionality.responsibleArchitect) this.functionality.responsibleArchitect = {};
          if(!this.functionality.responsibleDeveloper) this.functionality.responsibleDeveloper = {};
          if(!this.functionality.responsibleTester) this.functionality.responsibleTester = {};
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

        this.projectService.getUserRoles(id).subscribe(data => {
          this.isProjectManager = (data.indexOf("Project Manager") > -1);     
          this.isArchitect = (data.indexOf("Architect") > -1);    
          this.isDeveloper = (data.indexOf("Developer") > -1);    
          this.isTester = (data.indexOf("Tester") > -1);    
        });
      }
    });

  }

  promote() {
    if(this.functionality.state == FunctionalityState.NEW) {
      if(!this.functionality.responsibleArchitect.email || !this.functionality.responsibleDeveloper.email || !this.functionality.responsibleTester.email) {
        this.snackBar.open('You have to assign stakeholders in order to proceed!', 'close', {
          duration: 5000
        });
        return;
      }
      this.functionality.state = FunctionalityState.ASSIGNED;
    } else if(this.functionality.state == FunctionalityState.ASSIGNED ) {
      if(!this.specification.id || !this.usecase.id) {
        this.snackBar.open('You have to create specification and use case in order to proceed!', 'close', {
          duration: 5000
        });
        return;
      }
      this.functionality.state = FunctionalityState.SPECIFIED;
    } else if(this.functionality.state == FunctionalityState.SPECIFIED ) {
      this.functionality.state = FunctionalityState.IN_DEVELOPMENT;
    } else if(this.functionality.state == FunctionalityState.IN_DEVELOPMENT ) {
      this.functionality.state = FunctionalityState.IN_TESTING;
    } else if(this.functionality.state == FunctionalityState.IN_TESTING ) {
      var hasFailedTests = false;
      var hasPendingTests = false;
      for(var i = 0; i < this.testSteps.length; i++) {
        if(this.testSteps[i].testResult == TestCaseState.FAILED) hasFailedTests = true;
        if(this.testSteps[i].testResult == TestCaseState.NEW) hasPendingTests = true;
      }
      if(hasPendingTests) {
        this.snackBar.open('All test cases has to be validated before proceeding!', 'close', {
          duration: 5000
        });
        return;
      } else if(hasFailedTests) {
        this.functionality.state = FunctionalityState.IN_DEVELOPMENT;
      } else {
        this.functionality.state = FunctionalityState.UNDER_REVIEW;
      }
    } else if(this.functionality.state == FunctionalityState.UNDER_REVIEW ) {
      this.functionality.state = FunctionalityState.COMPLETED;
    }
    this.functionalityService.save(this.functionality).subscribe(result => {
    }, error => console.error(error));
    setTimeout(() => {
      this.gotoList();
    }, 500);
   
  } 

  assignUsers(architectEmail, developerEmail, testerEmail) {
    if(architectEmail){
      for(var i = 0; i < this.architects.length; i ++) {
        if(this.architects[i].email == architectEmail) {
          this.functionality.responsibleArchitect = this.architects[i];
        }
      }
    } 
    if(developerEmail){
      for(var i = 0; i < this.developers.length; i ++) {
        if(this.developers[i].email == developerEmail) {
          this.functionality.responsibleDeveloper = this.developers[i];
        }
      }
    }
    if(testerEmail){
      for(var i = 0; i < this.testers.length; i ++) {
        if(this.testers[i].email == testerEmail) {
          this.functionality.responsibleTester = this.testers[i];
        }
      }
    }
    this.functionalityService.save(this.functionality).subscribe(result => {
    }, error => console.error(error));
  }

  gotoList() {
    this.router.navigateByUrl('/home', {skipLocationChange: true}).then(()=>
    this.router.navigate(['/functionality-details/'+this.functionality.id]));
  }

  createUseCase() {
    this.functionalityService.saveUsecase({ functionality: this.functionality.id }).subscribe(result => {
    }, error => console.error(error));
    setTimeout(() => {
      this.gotoList();
    }, 500);
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

  shouldBeAbleToPromote() {
    if(this.functionality.state == FunctionalityState.NEW && this.isProjectManager) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.ASSIGNED && this.isArchitect) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.SPECIFIED && this.isDeveloper) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.IN_DEVELOPMENT && this.isDeveloper) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.IN_TESTING && this.isTester) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.UNDER_REVIEW && this.isArchitect) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.VALIDATED && this.isArchitect) {
      return true;
    }
    if(this.functionality.state == FunctionalityState.COMPLETED) {
      return false;
    }
    return false;
  }

}
