import { DoctorDetail } from './../../classes/doctor-detail';
import { Router } from '@angular/router';
import { AdminService } from './../../services/admin.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.css']
})
export class AddMemberComponent implements OnInit {
  private doctordetail = new DoctorDetail();
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
    goverment: new FormControl('', Validators.required),
    speciality : new FormControl('', Validators.required),
    role : new FormControl('', Validators.required)
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
      this.doctordetail.firstName = this.FirstName.value;
      this.doctordetail.lastName = this.LastName.value;
      this.doctordetail.emailId = this.Email.value;
      this.doctordetail.username = this.UserName.value;
      this.doctordetail.password = this.Password.value;
      this.doctordetail.phone = this.Phone.value;
      this.doctordetail.govermentId = this.GovermentId.value;
      this.doctordetail.speciality = this.Speciality.value;
      this.doctordetail.role = this.Role.value;
      this.adminService.addDoctor(this.doctordetail).subscribe(
        data => {
          console.log('data '+ data.body);
          if (data.body == 'Doctor registered successfully!'){
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
  get Role()
  {
    return this.form.get('role');
  }
  get Speciality()
  {
    return this.form.get('speciality');
  }

}
