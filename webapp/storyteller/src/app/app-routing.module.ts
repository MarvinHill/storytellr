import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DiscoveryPageComponent} from "./components/discovery-page/discovery-page.component";
import { AuthGuard } from './guard/auth.guard';
import { ApiTestComponent } from './components/api-test/api-test.component';
import {BookDetailComponent} from "./components/book-detail/book-detail.component";
import {EditDetailsComponent} from "./components/edit-details/edit-details.component";
import {ChapterEditComponent} from "./components/chapter-edit/chapter-edit.component";
import {WriteOverviewComponent} from "./components/write-overview/write-overview.component";
import {ReadPageComponent} from "./components/read-page/read-page.component";


const routes: Routes = [
  { path: '', redirectTo: '/discovery', pathMatch: 'full'},
  { path: 'testApi', component: ApiTestComponent, canActivate: [AuthGuard]},
  { path: 'discovery', component: DiscoveryPageComponent },
  { path: 'book-details', component: BookDetailComponent},
  { path: 'edit-details', component: EditDetailsComponent},
  { path: 'editor', component: ChapterEditComponent},
  { path: 'write', component: WriteOverviewComponent},
  { path: 'read', component: ReadPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
