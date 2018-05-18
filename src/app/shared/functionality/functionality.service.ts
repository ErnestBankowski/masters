import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { URLSearchParams } from '@angular/http';
import { SprintService }  from '../sprint/sprint.service';

export enum FunctionalityState {
  NEW = "NEW",
  SPECIFIED ="SPECIFIED",
  ASSIGNED ="ASSIGNED",
  IN_DEVELOPMENT ="IN DEVELOPMENT",
  UNDER_REVIEW ="UNDER REVIEW",
  IN_TESTING ="IN TESTING",
  VALIDATED ="VALIDATED",
  COMPLETED ="COMPLETED",
  WITHDRAWN ="WITHDRAWN",
}

export enum TestCaseState {
  NEW = "NEW",
  FAILED ="FAILED",
  PASSED ="PASSED"
}

@Injectable()
export class FunctionalityService {
  public API = '//localhost:8080';
  public FUNCTIONALITY_API = this.API + '/functionality';
  public USECASE_API = this.API + '/usecase';
  public SPECIFICATION_API = this.API + '/specification';

  constructor(private http: HttpClient,
  public sprintService: SprintService) { }

  getAll(id: string): Observable<any> {
    return this.http.get(this.FUNCTIONALITY_API + '/for/' + id);
  }

  get(id: string) {
    return this.http.get(this.FUNCTIONALITY_API + '/' + id);
  }

  getSpecification(id: string) {
    return this.http.get(this.SPECIFICATION_API + '/for/' + id);
  }

  getUseCase(id: string) {
    return this.http.get(this.USECASE_API + '/for/' + id);
  }

  getTestSteps(id: string): Observable<any> {
    return this.http.get(this.USECASE_API + '/step/for/' + id);
  }

  save(functionality: any): Observable<any> {
    let result: Observable<Object>;
    if (functionality['id']) {
      result = this.http.put(this.FUNCTIONALITY_API, functionality);
    } else {
      result = this.http.post(this.FUNCTIONALITY_API, functionality);
    }
    return result;
  }

  saveSpecification(specification: any): Observable<any> {
    let result: Observable<Object>;
    if (specification['id']) {
      result = this.http.put(this.SPECIFICATION_API, specification);
    } else {
      result = this.http.post(this.SPECIFICATION_API, specification);
    }
    return result;
  }

  saveUsecase(usecase: any): Observable<any> {
    let result: Observable<Object>;
    if (usecase['id']) {
      result = this.http.put(this.USECASE_API, usecase);
    } else {
      result = this.http.post(this.USECASE_API, usecase);
    }
    return result;
  }

  saveTestStep(teststep: any): Observable<any> {
    let result: Observable<Object>;
    if (teststep['id']) {
      result = this.http.put(this.USECASE_API+"/step", teststep);
    } else {
      result = this.http.post(this.USECASE_API+"/step", teststep);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
