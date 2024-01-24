import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import { User } from './User';



@Injectable({ providedIn: 'root' })
export class UserService {

  private UsersUrl = 'http://localhost:8080/users' ;  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.UsersUrl)
      .pipe(
        tap(_ => this.log('fetched Users')),
        catchError(this.handleError<User[]>('getUsers', []))
      );
  }

  /** GET User by id. Return `undefined` when id not found */
  getUserNo404<Data>(id: number): Observable<User> {
    const url = `${this.UsersUrl}/?id=${id}`;
    return this.http.get<User[]>(url)
      .pipe(
        map(Users => Users[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} User id=${id}`);
        }),
        catchError(this.handleError<User>(`getUser id=${id}`))
      );
  }

  /** GET hero by id. Will 404 if id not found */
  getUser(id: number): Observable<User> {
    const url = `${this.UsersUrl}/${id}`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`fetched User id=${id}`)),
      catchError(this.handleError<User>(`getUser id=${id}`))
    );
  }

  /* GET Users whose name contains search term */
  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<User[]>(`${this.UsersUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found Users matching "${term}"`) :
         this.log(`no Users matching "${term}"`)),
      catchError(this.handleError<User[]>('searchUsers', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addUser(User: User): Observable<User> {
    return this.http.post<User>(this.UsersUrl, User, this.httpOptions).pipe(
      tap((newUser: User) => this.log(`added User w/ id=${newUser.id}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  /** DELETE: delete the User from the server */
  deleteUser(id: number): Observable<User> {
    const url = `${this.UsersUrl}/${id}`;

    return this.http.delete<User>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted User id=${id}`)), 
      catchError(this.handleError<User>('deleteUser')));
  }

  /** PUT: update the hero on the server */
  updateUser(User: User): Observable<any> {
    return this.http.put(this.UsersUrl, User, this.httpOptions).pipe(
      tap(_ => this.log(`updated User id=${User.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`CartService: ${message}`);
  }
}