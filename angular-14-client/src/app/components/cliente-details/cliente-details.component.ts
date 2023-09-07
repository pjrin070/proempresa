import { Component, Input, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/services/cliente.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/models/Cliente.model';

@Component({
  selector: 'app-cliente-details',
  templateUrl: './cliente-details.component.html',
  styleUrls: ['./cliente-details.component.css']
})
export class ClienteDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentCliente: Cliente = {
    id_cliente : 0,
    nombres : '',
    apellidos : '',
    nro_documento : '',
    email : '' ,
    //create_at :  new Date(),
    //ventas : []
  };
  
  message = '';

  constructor(
    private clienteService: ClienteService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getCliente(this.route.snapshot.params["id"]);
    }
  }

  getCliente(id: string): void {
    this.clienteService.get(id)
      .subscribe({
        next: (data) => {
          this.currentCliente = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
 
  updateCliente(): void {
    this.message = '';

    this.clienteService.update(this.currentCliente.id_cliente, this.currentCliente)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'El Cliente fue actualizado correctamente!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteCliente(): void {
    this.clienteService.delete(this.currentCliente.id_cliente)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/clientes']);
        },
        error: (e) => console.error(e)
      });
  }

}