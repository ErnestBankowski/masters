import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export enum UserRoles {
  PROJECT_MANAGER = "Project Manager",
  DEVELOPER ="Developer",
  ARCHITECT ="Architect",
  TESTER = "Tester"
}

@Injectable()
export class ProjectService {
  public API = '//localhost:8080';
  public PROJECT_API = this.API + '/project';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/project');
  }

  getForUser(): Observable<any> {
    return this.http.get(this.PROJECT_API);
  }

  get(id: string) {
    return this.http.get(this.PROJECT_API + '/' + id);
  }

  save(project: any): Observable<any> {
    let result: Observable<Object>;
    if (project['href']) {
      result = this.http.put(project.href, project);
    } else {
      result = this.http.post(this.PROJECT_API, project);
    }
    return result;
  }

  saveParticipant(projectParticipant: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.PROJECT_API +'/saveParticipant', projectParticipant);
    return result;
  }

  getParticipants(id: string) : Observable<any>{
    return this.http.get(this.PROJECT_API + '/participants/' + id);
  }

  getUserRoles(id: string): Observable<any> {
    return this.http.get(this.PROJECT_API + '/roles/' + id);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
