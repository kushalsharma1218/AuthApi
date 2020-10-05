import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminDetail } from '../classes/admin-detail';
import { AdminService } from '../services/admin.service';
import { Router } from '@angular/router';
import { ITS_JUST_ANGULAR } from '@angular/core/src/r3_symbols';
import { equal } from 'assert';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  private adminDetail = new AdminDetail();
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isEmailAlreadyTaken = false;
  isUsernameAlreadyTaken = false;



  form = new FormGroup({
    firstName : new FormControl('' , Validators.required),
    lastName : new FormControl('', Validators.required),
    email : new FormControl('' , Validators.required),
    userName : new FormControl('' , Validators.required),
    password : new FormControl('' , Validators.required),
    confirmPassword : new FormControl('' , Validators.required),
    phone : new FormControl('', Validators.required),
    goverment: new FormControl('', Validators.required)
});
  constructor(private adminService: AdminService, private router: Router) { }
  ngOnInit() {

  }

  AdminForm()
  {
     let pass = this.Password.value;
     let confirmPass = this.ConfirmPassword.value;
     if(pass == confirmPass)
     {
        this.adminDetail.firstName = this.FirstName.value;
        this.adminDetail.lastName = this.LastName.value;
        this.adminDetail.emailId = this.Email.value;
        this.adminDetail.username = this.UserName.value;
        this.adminDetail.password = this.Password.value;
        this.adminDetail.phone = this.Phone.value;
        this.adminDetail.govermentId = this.GovermentId.value;
        this.adminService.saveAdminDetails(this.adminDetail).subscribe(
             data => {
              console.log('data '+ data.body);
              if (data.body == 'User registered successfully!'){
              this.isSuccessful = true;
              this.isSignUpFailed = false;
              alert(data.body);
              this.router.navigate(['/login']);
              }
              else if(data.body == 'Error: Email is already in use!' || data.body == 'Error: Username is already taken!')
              {
                alert(data.body);
                this.isSuccessful = false;
                this.isSignUpFailed =  true;
              }
            },
            err => {
              this.isSignUpFailed = true;
            }
        );
          }
     else
     {
        alert("Password and confirm password not match.");
     }
  }
  get FirstName(){
    return this.form.get('firstName');
  }
  get LastName(){
    return this.form.get('lastName');
  }
  get Email(){
      return this.form.get('email');
  }
  get UserName(){
    return this.form.get('userName')
  }
  get Password(){
      return this.form.get('password');
  }
  get ConfirmPassword(){
      return this.form.get('confirmPassword');
  }
  get Phone()
  {
    return this.form.get('phone');
  }
  get GovermentId()
  {
    return this.form.get('goverment');
  }
}
