import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesListComponent } from './components/clientes-list/clientes-list.component';
import { ClienteDetailsComponent } from './components/cliente-details/cliente-details.component';
import { AddClienteComponent } from './components/add-tutorial/add-cliente.component';

const routes: Routes = [
  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  { path: 'clientes', component: ClientesListComponent },
  { path: 'clientes/:id', component: ClienteDetailsComponent },
  { path: 'add', component: AddClienteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }