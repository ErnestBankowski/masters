import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SprintService {
  public API = '//localhost:8080';
  public SPRINT_API = this.API + '/sprint';

  constructor(private http: HttpClient) { }

  getAll(id: string): Observable<any> {
    return this.http.get(this.SPRINT_API + '/for/' + id);
  }

  get(id: string) {
    return this.http.get(this.SPRINT_API + '/' + id);
  }

  save(sprint: any): Observable<any> {
    let result: Observable<Object>;
    if (sprint['href']) {
      result = this.http.put(sprint.href, sprint);
    } else {
      result = this.http.post(this.SPRINT_API, sprint);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
