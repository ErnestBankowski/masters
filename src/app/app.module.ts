import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { GiphyService } from './shared/giphy/giphy.service';
import { AppComponent } from './app.component';
import { ProjectService } from './shared/project/project.service';
import { UserService } from './shared/user/user.service';
import { HttpClientModule } from '@angular/common/http';
import { ProjectListComponent } from './project-list/project-list.component'
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent, OktaAuthModule } from '@okta/okta-angular';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { HomeComponent } from './home/home.component';

const config = {
  issuer: 'https://dev-531715.oktapreview.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oaet8g4w9f6C2JLb0h7'
};

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'project-list',
    component: ProjectListComponent
  },
  {
    path: 'project-add',
    component: ProjectEditComponent
  },
  {
    path: 'project-edit/:id',
    component: ProjectEditComponent
  },
  {
    path: 'implicit/callback',
    component: OktaCallbackComponent
  },
];

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    ProjectEditComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
	OktaAuthModule.initAuth(config)
  ],
  providers: [ProjectService, UserService, GiphyService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
