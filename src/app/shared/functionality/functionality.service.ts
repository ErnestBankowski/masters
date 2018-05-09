import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { URLSearchParams } from '@angular/http';

@Injectable()
export class FunctionalityService {
  public API = '//localhost:8080';
  public FUNCTIONALITY_API = this.API + '/functionality';

  constructor(private http: HttpClient) { }

  getAll(id: string): Observable<any> {
    return this.http.get(this.FUNCTIONALITY_API + '/for/' + id);
  }

  get(id: string) {
    return this.http.get(this.FUNCTIONALITY_API + '/' + id);
  }

  save(sprint: any): Observable<any> {
    let result: Observable<Object>;
    if (sprint['href']) {
      result = this.http.put(sprint.href, sprint);
    } else {
      result = this.http.post(this.FUNCTIONALITY_API, sprint);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
