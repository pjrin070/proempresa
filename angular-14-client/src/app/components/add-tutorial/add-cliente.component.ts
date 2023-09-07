import { Component } from '@angular/core';
import { Cliente } from 'src/app/models/Cliente.model';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-add-cliente',
  templateUrl: './add-cliente.component.html',
  styleUrls: ['./add-cliente.component.css']
})
export class AddClienteComponent {

  cliente: Cliente = {
    id_cliente : 0,
    nombres : '',
    apellidos : '',
    nro_documento : '',
    email : ''  
    //create_at :  new Date(),
    //ventas : []
  };
  submitted = false;

  constructor(private clienteService: ClienteService) { }

  saveCliente(): void {
    const data = { 
      id_cliente : this.cliente.id_cliente,
      nombres : this.cliente.nombres,
      apellidos : this.cliente.apellidos,
      nro_documento : this.cliente.nro_documento,
      email : this.cliente.email,
     // create_at : this.cliente.create_at,
    //  ventas : this.cliente.ventas 
    };

    this.clienteService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newCliente(): void {
    this.submitted = false;
    this.cliente = {
      id_cliente : 0,
      nombres : '',
      apellidos : '',
      nro_documento : '',
      email : '' ,
     // create_at :  new Date(),
  //    ventas : []
    };
  }

}