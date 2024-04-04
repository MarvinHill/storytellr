import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DiscoveryPageComponent} from "./components/discovery-page/discovery-page.component";
import { AuthGuard } from './guard/auth.guard';
import { ApiTestComponent } from './components/api-test/api-test.component';

const routes: Routes = [
  { path: '', redirectTo: '/discovery', pathMatch: 'full'},
  { path: 'testApi', component: ApiTestComponent, canActivate: [AuthGuard]},
  { path: 'discovery', component: DiscoveryPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
