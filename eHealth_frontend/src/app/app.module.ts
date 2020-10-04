import { authInterceptorProviders } from './helper/auth-intercepter';


import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// import Http module
import { HttpModule} from '@angular/http';
// import ReactiveFormsModule for reactive form
import { ReactiveFormsModule} from '@angular/forms';
// import module for Routing.
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';

import { ProfileComponent } from './profile/profile.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatDialogModule } from "@angular/material/dialog";
import { MatTabsModule } from "@angular/material/tabs";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatIconModule } from "@angular/material/icon";
import { MatCardModule } from '@angular/material/card';
import { AdminService } from './services/admin.service';
import { HospitalAdminComponent } from './hospital-admin/hospital-admin.component';
import { PatientComponent } from './patient/patient.component';
import { AddMemberComponent } from './hospital-admin/add-member/add-member.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
    ProfileComponent,
    HospitalAdminComponent,
    PatientComponent,
    AddMemberComponent,

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatCardModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    RouterModule.forRoot([
      {
        path : '',
        component : HomeComponent
      },
      {
        path : 'login',
        component : LoginComponent
      },
      {
        path : 'signup',
        component : SignupComponent
      },
      {
        path : 'profile/:adminId',
        component : ProfileComponent
      },
      {
        path : 'hospital-admin/:adminId',
        component : HospitalAdminComponent
      },
      {
        path : 'addmember/:adminId',
        component : AddMemberComponent
      }
    ]),

    BrowserAnimationsModule
  ],
  providers: [
    AdminService, authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
