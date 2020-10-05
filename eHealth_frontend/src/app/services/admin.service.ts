import { DoctorDetail } from './../classes/doctor-detail';
import { FormGroup } from '@angular/forms';
import { TokenStorageService } from './token-storage.service';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { AdminDetail } from '../classes/admin-detail';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private tokenService = new TokenStorageService();
  private  baseUrl = 'http://localhost:8080/';
  private loginUrl = this.baseUrl + 'authenticate';
  constructor(private http: HttpClient, private router: Router) { }
  saveAdminDetails(adminDetail: AdminDetail): Observable<any>
  {
      let url = this.baseUrl + 'register';
      return this.http.post(url,
        {
        firstName : adminDetail.firstName,
        lastName :  adminDetail.lastName,
        emailId :  adminDetail.emailId,
        username  : adminDetail.username,
        phone : adminDetail.phone,
        password : adminDetail.password,
        roles : [
          {
            role : 'PATIENT'
          }
        ]
      }, httpOptions);
  }
  login(adminDetail: AdminDetail): Observable<any>
  {
      return this.http.post(this.loginUrl, adminDetail, httpOptions);
  }

  logout()
  {
    this.tokenService.signOut();

  }

getAllUsers(): Observable<any>
{
    return this.http.get(this.baseUrl + 'admin/getAllUsers', httpOptions);
}

addDoctor(doctordetail : DoctorDetail): Observable<any>
{
  return this.http.post(this.baseUrl + 'addDoctor', doctordetail, httpOptions);
}
}
