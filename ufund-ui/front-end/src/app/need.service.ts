import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Need } from './need';
import { FundingBasket } from './fundingbasket';
import { MessageService } from './message.service';
import { User } from './user';



@Injectable({ providedIn: 'root' })
export class NeedService {

  private needsUrl = 'http://localhost:8080/need'
  private fundingURl = 'http://localhost:8080/fundingbasket'
  private userUrl = 'http://localhost:8080/users'

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET needs from the server */
  getNeeds(): Observable<Need[]> {
    console.log("Test")
    return this.http.get<Need[]>(this.needsUrl)
      .pipe(
        tap(_ => this.log('fetched needs')),
        catchError(this.handleError<Need[]>('getNeeds', []))
      );
  }

    /** GET baskets from the server */
    getFundingBaskets(): Observable<FundingBasket[]> {
      console.log("Test2")
      return this.http.get<FundingBasket[]>(this.fundingURl)
        .pipe(
          tap(_ => this.log('fetched baskets')),
          catchError(this.handleError<FundingBasket[]>('getFundingBaskets', []))
        );
    }

    /** GET users from the server */
    getUser(): Observable<User[]> {
      //console.log("Test")
      return this.http.get<User[]>(this.userUrl)
        .pipe(
          tap(_ => this.log('fetched user')),
          catchError(this.handleError<User[]>('getUser', []))
        );
    }

  /** GET need by id. Return `undefined` when id not found */
  getNeedNo404<Data>(id: number): Observable<Need> {
    const url = `${this.needsUrl}/?id=${id}`;
    return this.http.get<Need[]>(url)
      .pipe(
        map(needs => needs[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} need id=${id}`);
        }),
        catchError(this.handleError<Need>(`getNeed id=${id}`))
      );
  }

  /** GET need by id. Will 404 if id not found */
  getNeed(id: number): Observable<Need> {
    const url = `${this.needsUrl}/${id}`;
    return this.http.get<Need>(url).pipe(
      tap(_ => this.log(`fetched need id=${id}`)),
      catchError(this.handleError<Need>(`getNeed id=${id}`))
    );
  }

    /** GET need by id. Will 404 if id not found */
    getFundingBasket(id: number): Observable<FundingBasket> {
      const url = `${this.fundingURl}/${id}`;
      return this.http.get<FundingBasket>(url).pipe(
        tap(_ => this.log(`fetched basket id=${id}`)),
        catchError(this.handleError<FundingBasket>(`getFundingBasket id=${id}`))
      );
    }

    getUserId(id: number): Observable<User> {
      const url = `${this.userUrl}/${id}`;
      return this.http.get<User>(url).pipe(
        tap(_ => this.log(`fetched user id=${id}`)),
        catchError(this.handleError<User>(`getUser id=${id}`))
      );
    }

  /* GET needs whose name contains search term */
  searchNeeds(term: string): Observable<Need[]> {
    if (!term.trim()) {
      // if not search term, return empty need array.
      return of([]);
    }
    return this.http.get<Need[]>(`${this.needsUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found needs matching "${term}"`) :
         this.log(`no needs matching "${term}"`)),
      catchError(this.handleError<Need[]>('searchNeeds', []))
    );
  }

  //////// Save methods //////////
  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.userUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => this.log(`added  id=${newUser.id}, name =${newUser.name}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  /** POST: add a new need to the server */
  addNeed(need: Need): Observable<Need> {
    return this.http.post<Need>(this.needsUrl, need, this.httpOptions).pipe(
      tap((newNeed: Need) => this.log(`added need w/ id=${newNeed.id}, cost=${newNeed.cost}, 
                                        quantity=${newNeed.quantity}, type=${newNeed.type}`)),
      catchError(this.handleError<Need>('addNeed'))
    );
  }

  /** PUT: update the need on the user server */
  addBasket(item: Need): Observable<User> {
    return this.http.post<User>(this.userUrl, item, this.httpOptions).pipe(
      tap((newitem: User) => this.log(`basket = ${item}`)),
      catchError(this.handleError<User>('addBasket'))
    );
  }

    /** PUT: update the need on the server */
    updateBasket(item: FundingBasket): Observable<any> {
      return this.http.put(this.fundingURl, item, this.httpOptions).pipe(
        tap(_ => this.log(`id=${item.id}, name=${item.name}, cost =${item.cost}, quantity =${item.quantity},
        type =${item.type}`)),
        catchError(this.handleError<any>('update'))
      );
    }

  /** DELETE: delete the need from the server */
  deleteNeed(id: number): Observable<Need> {
    const url = `${this.needsUrl}/${id}`;

    return this.http.delete<Need>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted need id=${id}`)),
      catchError(this.handleError<Need>('deleteNeed'))
    );
  }

    /** DELETE: delete the need from the server */
    deleteBasket(id: number): Observable<FundingBasket> {
      const url = `${this.fundingURl}/${id}`;
  
      return this.http.delete<FundingBasket>(url, this.httpOptions).pipe(
        tap(_ => this.log(`deleted item id=${id}`)),
        catchError(this.handleError<FundingBasket>('deleteitem'))
      );
    }
  
  /** PUT: update the need on the server */
  updateNeed(need: Need): Observable<any> {
    return this.http.put(this.needsUrl, need, this.httpOptions).pipe(
      tap(_ => this.log(`updated need id=${need.id}`)),
      catchError(this.handleError<any>('updateNeed'))
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

  /** Log a NeedService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`NeedService: ${message}`);
  }
}