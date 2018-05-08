import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { GiphyService } from './shared/giphy/giphy.service';
import { AppComponent } from './app.component';
import { ProjectService } from './shared/project/project.service';
import { UserService } from './shared/user/user.service';
import { SprintService } from './shared/sprint/sprint.service';
import { HttpClientModule } from '@angular/common/http';
import { ProjectListComponent } from './project-list/project-list.component'
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent, OktaAuthModule } from '@okta/okta-angular';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { HomeComponent } from './home/home.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material';
import {MatTabsModule} from '@angular/material/tabs';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDividerModule} from '@angular/material/divider';
import {MatMenuModule} from '@angular/material/menu';
import { SprintEditComponent } from './sprint-edit/sprint-edit.component';
import { SprintListComponent } from './sprint-list/sprint-list.component';
import { SprintDetailsComponent } from './sprint-details/sprint-details.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import { FunctionalityEditComponent } from './functionality-edit/functionality-edit.component';
import { FunctionalityDetailsComponent } from './functionality-details/functionality-details.component';
import { SpecificationEditComponent } from './specification-edit/specification-edit.component';
import { UsecaseEditComponent } from './usecase-edit/usecase-edit.component';

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
    path: 'project-details/:id',
    component: ProjectDetailsComponent
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
    path: 'sprint-add',
    component: SprintEditComponent
  },
  {
    path: 'sprint-edit/:id',
    component: SprintEditComponent
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
    HomeComponent,
    ProjectDetailsComponent,
    SprintEditComponent,
    SprintListComponent,
    SprintDetailsComponent,
    FunctionalityEditComponent,
    FunctionalityDetailsComponent,
    SpecificationEditComponent,
    UsecaseEditComponent
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
    MatTabsModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatDividerModule,
    MatMenuModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
	  OktaAuthModule.initAuth(config)
  ],
  providers: [ProjectService, UserService, SprintService, GiphyService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
