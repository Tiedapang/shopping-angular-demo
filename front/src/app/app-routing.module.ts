import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ShoppingListComponent} from './components/shopping-list/shopping-list.component';


const routes: Routes = [
  {path: 'shopping', component: ShoppingListComponent},
  {path: '', pathMatch: 'full', redirectTo: '/shopping'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
