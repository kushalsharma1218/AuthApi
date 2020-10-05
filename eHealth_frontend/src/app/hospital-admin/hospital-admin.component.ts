import { TokenStorageService } from './../services/token-storage.service';
import { Router } from '@angular/router';
import { AdminService } from './../services/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hospital-admin',
  templateUrl: './hospital-admin.component.html',
  styleUrls: ['./hospital-admin.component.css']
})
export class HospitalAdminComponent implements OnInit {

  constructor(private adminService : AdminService, private router : Router, private tokenservice : TokenStorageService) { }

  ngOnInit(): void {
    // this.adminService.getAllUsers().subscribe(
    //   data =>{
    //   console.log(data);
    //   }
    // )
    }

    routeToAddMember()
    {

      this.router.navigate(['addmember', this.tokenservice.getToken()]);
    }

    public logout()
    {
      this.adminService.logout();
    }
}
