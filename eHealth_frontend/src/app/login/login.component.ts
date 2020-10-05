import { Response } from './../classes/response';
import { TokenStorageService } from '../services/token-storage.service';
import { Component, Directive, Inject, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AdminDetail } from '../classes/admin-detail';

import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})
export class LoginComponent implements OnInit {

  private tokenService = new TokenStorageService();
  private adminDetail = new AdminDetail();
  private response = new Response();
  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(){
    if(this.tokenService.getToken())
    {
      if(this.tokenService.getUser().roles == 'ADMIN')
      {
        this.router.navigate(['hospital-admin', this.tokenService.getToken()]);
      }
      else{
      this.router.navigate(['profile', this.tokenService.getToken()]);
    }
  }
  }

  // create the form object.
    form = new FormGroup({
      username : new FormControl('' , Validators.required),
      password : new FormControl('' , Validators.required)
  });

  Login(LoginInformation)
  {

      this.adminDetail.username = this.Username.value;
      this.adminDetail.password = this.Password.value;
      if(this.adminDetail.username == '' || this.adminDetail.password== ''){ alert('Please enter details');}
else{
      let resp = this.adminService.login(this.adminDetail);
      resp.subscribe(
        data => {
          if(data.body == 'Username doesn\'t exists')
          {
            alert(data.body + ' please register ');
          }
          else if(data.body == 'invalid username or password')
          {
            alert("Invalid username and password");
          }
          else if(data.token != null)
            {
              this.tokenService.saveToken(data.token);
              console.log(data);
              this.tokenService.saveUser(data);
              if(data.roles == 'ADMIN'){
              this.router.navigate(['hospital-admin',this.tokenService.getToken()]);
              }
              else {
                this.router.navigate(['profile', this.tokenService.getToken()]);
            }
          }
          }
      );
        }
  }

  get Username(){
      return this.form.get('username');
  }

  get Password(){
      return this.form.get('password');
  }

 public Register()
  {

    this.router.navigate(['signup']);
  }


}
