import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ProjectService {
  public API = '//localhost:8080';
  public PROJECT_API = this.API + '/project';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/project');
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

  remove(href: string) {
    return this.http.delete(href);
  }
}
