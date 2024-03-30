import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from './user';
import { MessageService } from './message.service';
import { Need } from './need';
import { NeedService } from './need.service';

@Injectable({ providedIn: 'root' })
export class UserService {

  private usersUrl = 'http://localhost:8080/users';  // URL to web api

  constructor(
    private http: HttpClient,
    private needService: NeedService,
    private messageService: MessageService) { }

  /** Log a userService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`UserService: ${message}`);
  }

  /** GET users from the server */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl)
    .pipe(
      catchError(this.handleError<User[]>('getUsers', []))
    );
  }

  findUserByName(name: string): Observable<User | null> {
    return this.http.get<User[]>(this.usersUrl).pipe(
      map(users => users.find(user => user.name === name) || null),
      catchError(this.handleError<User | null>('findUserByName'))
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

    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

/** GET user by id. Will 404 if id not found */
getUser(id: number): Observable<User> {
  const url = `${this.usersUrl}/${id}`;
  return this.http.get<User>(url).pipe(
    tap(_ => this.log(`fetched user id=${id}`)),
    catchError(this.handleError<User>(`getUser id=${id}`))
  );
}

  /** PUT: update the user on the server */
  updateUser(user: User): Observable<any> {
    return this.http.put(this.usersUrl, user, this.httpOptions).pipe(
      tap(_ => this.log(`updated user id=${user.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  /** POST: add a new user to the server */
addUser(user: User): Observable<User> {
  return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
    tap((newUser: User) => this.log(`added user w/ id=${newUser.id}`)),
    catchError(this.handleError<User>('addUser'))
  );
}

/** DELETE: delete the user from the server */
deleteUser(id: number): Observable<User> {
  const url = `${this.usersUrl}/${id}`;

  return this.http.delete<User>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted user id=${id}`)),
    catchError(this.handleError<User>('deleteUser'))
  );
}

/* GET users whose name contains search term */
searchUsers(term: string): Observable<User[]> {
  // if (!term.trim()) {
  //   if(this.findUserByName(term) == null){
  //   // if not search term, return empty user array.
  //   return of([]);
  // }
  return this.http.get<User[]>(`${this.usersUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found users matching "${term}"`) :
       this.log(`no users matching "${term}"`)),
    catchError(this.handleError<User[]>('searchUsers', []))
  );
}

async removeAllFromCart(user: User, product: Need){
  await this.removeAllFromCart2(user, product);
}

async removeAllFromCart2(user: User, product: Need): Promise<void> {
  var newuser = user;
  this.needService.getNeed(Math.abs(product.id)).subscribe(inventoryVariant=>{this.needService.updateNeed(
    {id : Math.abs(product.id),
    name : product.name,
    cost : product.cost,
    quantity : (inventoryVariant.quantity+product.quantity),
    type: product.type
    }as Need).subscribe()});

    let i; 
    for (i = 0; i < user.basket.length; i++) {
      if (user.basket[i].id == product.id) {
        break; 
      }
    }
    newuser.basket.splice(i, 1); 
    this.updateUser(
      {id : user.id, 
      name: user.name,  
      basket: newuser.basket} as User).subscribe();
    
  }
  async removeOneFromCart(user: User, product: Need){
    await this.removeAllFromCart2(user, product);
  }
  
  async removeOneFromCart2(user: User, product: Need): Promise<void> {
    var newuser = user;
    this.needService.getNeed(Math.abs(product.id)).subscribe(inventoryVariant=>{this.needService.updateNeed(
      {id : Math.abs(product.id),
      name : product.name,
      cost : inventoryVariant.cost,
      quantity : (inventoryVariant.quantity+1),
      type: product.type
      }as Need).subscribe()});

      let i; 
      for (i = 0; i < user.basket.length; i++) {
        if (user.basket[i].id == product.id) {
          break; 
        }
      }
     
      newuser.basket[i].quantity = newuser.basket[i].quantity -1;
        
      
      
      this.updateUser(
        {id : user.id, 
        name: user.name,  
        basket: newuser.basket} as User).subscribe();
      
    }
}