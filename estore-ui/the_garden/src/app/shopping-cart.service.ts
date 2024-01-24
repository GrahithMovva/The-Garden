import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import { User } from './User';

import { Plant } from './Plants/Plant';
import { UserService } from './User.service';


@Injectable({ providedIn: 'root' })
export class ShoppingCartService {

  private CartUrl = 'http://localhost:8080/cart/' ;  // URL to web api
  private Url = 'http://localhost:8080/users'
  

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  
  id !: number;
  user !: User;

  constructor(
    private userService : UserService,
    private http: HttpClient,
    private messageService: MessageService) {}
    
  /** GET hero by this.id. Will 404 if this.id not found */
  getCart(): Observable<Plant[]> {
    
    this.userService.getCurrentUser().subscribe(User => this.id = User.id)
    const url = this.CartUrl + this.id;
    return this.http.get<Plant[]>(url).pipe(
      tap(_ => this.log(`fetched cart of ${this.id } `)),
      catchError(this.handleError<Plant[]>(`getCart this.id = ${this.id }`))
    );
  }



  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addToCart(plant: Plant): Observable<Plant[]> {
    this.userService.getCurrentUser().subscribe(User => this.id = User.id)
    this.userService.getCurrentUser().subscribe(User => this.user = User)
    const url = this.CartUrl + this.id;
    
    this.http.put(this.Url, this.user, this.httpOptions).pipe(
      tap(_ => this.log(`updated User id=${this.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
    return this.http.post<Plant[]>(url, plant, this.httpOptions).pipe(
      tap(_ => this.log(`${plant.name} is added to cart`)), 
      catchError(this.handleError<Plant[]>('add to cart')));
    
  }

  /** DELETE: delete the User from the server */
  checkout(): Observable<Plant[]> {
    this.userService.getCurrentUser().subscribe(User => this.id = User.id)
    const url = this.CartUrl + this.id;
    return this.http.delete<Plant[]>(url, this.httpOptions).pipe(
      tap(_ => this.log(`checked out successfully`)), 
      catchError(this.handleError<Plant[]>('deleteCart')));
  }

  /** PUT: update the hero on the server */
  removeFromCart(plant: Plant): Observable<any> {
    this.userService.getCurrentUser().subscribe(User => this.id = User.id)
    this.userService.getCurrentUser().subscribe(User => this.user = User)
    const url = this.CartUrl + this.id;
    this.http.put(this.Url, this.user, this.httpOptions).pipe(
      tap(_ => this.log(`updated User id=${this.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
    return this.http.put(url, plant, this.httpOptions).pipe(
      tap(_ => this.log(`${plant.name} is removed from cart`)),
      catchError(this.handleError<any>('remove from cart'))
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
    this.messageService.add(`shopping-cartService: ${message}`);
  }
  
}
