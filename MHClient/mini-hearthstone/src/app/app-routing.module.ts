import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WaitingplayerComponent } from './waitingplayer/waitingplayer.component'
import { WelcomeComponent } from "./welcome/welcome.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";

const routes: Routes = [
  { path: 'waitForPlayer', component: WaitingplayerComponent },
  { path: 'welcome', component: WelcomeComponent },
  { path: '',   redirectTo: '/welcome', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}


