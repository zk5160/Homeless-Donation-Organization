import { Component } from '@angular/core';
import { AuthService } from '../../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NeedService } from '../need.service';
import { User } from '../user';
import { CurrentUserService } from '../current-user.service';
import { UserService } from '../user.service';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-login',
  // standalone: true,
  // imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  form!: FormGroup;
  users: User[] = [];

  constructor(private authSurvice: AuthService, private fb: FormBuilder, private router: Router, private needService: NeedService,
    private userService: UserService, private currentUser: CurrentUserService){
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  ngOnInit(): void {

    this.currentUser.setCurrentUser("GUEST");
    
    this.userService.getUsers().subscribe((checkusers: User[]) =>{this.users = checkusers;
      for (let index = 0; index < checkusers.length; index++) {
        this.usernames[index] = checkusers[index].name;
      }
      })
  }
  usernames : string[] = [];

  login(){
    const username: string = this.form.value.username.trim(); // Extracting the username and trimming whitespace
    const password = this.form.value.password;
    //console.log('test');
    console.log('Raw username', username);
    if (username ==null){
      alert('no username');
    }
    // Check if username is empty
    if (!username) {
      alert('Please enter a username');
    } else {
      // Pass username and password to AuthService login method
      //const user = this.authSurvice.login(username, password);

      // Check if user is successfully logged in
      if (username) {
        if (username.toLowerCase() === 'admin') {
          this.router.navigateByUrl('/needs');
        } 
        else if(this.usernames.includes(username)){
          //alert("Returning user");
          //not working
          this.currentUser.setCurrentUser(username);
          this.router.navigateByUrl('/dashboard');
        }
        else {
          //need to save to json
          //id will increment correctly, 0 is placeholder
          //need to fix, can only add user
          const newUser: User = { id: 0, name: username,  basket : []};
          this.needService.addUser(newUser).subscribe();
          this.currentUser.setCurrentUser(username);
          this.router.navigateByUrl('/dashboard');
        }
      } else {
        alert('Invalid username or password');
      }
    }
    }

}
