import { Component } from '@angular/core';
import { AuthService } from '../../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  // standalone: true,
  // imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  form : FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });
  constructor(private authSurvice: AuthService, private fb: FormBuilder, private router: Router){

  }

  login(){
    let user = this.authSurvice.login(this.form.value.username, this.form.value.password);
    if(!user){
      alert('Invalid username or password');
    }
    else{
      this.router.navigateByUrl('/admin');
    }

  }

}
