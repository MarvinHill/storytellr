import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DiscoveryPageComponent} from "./components/discovery-page/discovery-page.component";
import {BookDetailComponent} from "./components/book-detail/book-detail.component";

const routes: Routes = [
  { path: '', redirectTo: '/discovery', pathMatch: 'full' },
  { path: 'discovery', component: DiscoveryPageComponent },
  { path: 'book-details', component: BookDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
