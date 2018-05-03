import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {
  public API = '//localhost:8080';
  public USER_API = this.API + '/users';

  constructor(private http: HttpClient) { }

  authenticate() {
    this.http.get(this.USER_API + '/authenticate').subscribe(result => {
    }, error => console.error(error));;
  } 

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/users/get');
  }

}
