import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente/cliente';
import { Router, ActivatedRoute } from '@angular/router';
import {ClienteService} from '../cliente/cliente.service';
import {ClienteComponent} from '../cliente/cliente.component';
import swal from 'sweetalert2';



@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
})
export class ClienteFormComponent implements OnInit {



  clientes: Cliente[] = [];
  flag: boolean = false;



  titulo: String = "Formulario de Clientes";
  cliente: Cliente = new Cliente();

  constructor(
    private ClienteService: ClienteService,
     private router: Router,
     private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCliente();
    this.flag = true;
    this.ClienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    )
  }

  actualizarCliente():void{
    this.ClienteService.updateClient(this.cliente).subscribe(
      cliente =>{
        this.router.navigate(['/clientes'])
        swal.fire('Cliente Actualizado', `Cliente ${this.cliente.nombre} actualizado con éxito!`, 'success')
      }
    )
  }

  public crearCliente():void{
    if (this.cliente.nombre == ''){
      swal.fire('Nombre no ingresado', `No es posible crear un cliente sin nombre`)
    }
    if(this.cliente.numeroContacto.length < 10){
      swal.fire('Numero no valido', `El numero de telefono debe tener como minimo 10 digitos`)
    }
    this.clientes.forEach((clientucho) =>{
      if(clientucho.numeroContacto == this.cliente.numeroContacto){
        console.log('esta repetido mi so');
        swal.fire('Numero ya registrado', `El numero de telefono ingresado pertence a otro cliente`);
        this.flag = false;
      }else{
      }
    });
    if(this.flag==true){
      this.ClienteService.createCliente(this.cliente).subscribe(
        cliente=> {
          this.router.navigate(['/clientes'])

          swal.fire('Nuevo cliente', `Cliente ${this.cliente.nombre} creado con éxito!`, 'success')
        }
    );
    }
  }

  cargarCliente():void{
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.ClienteService.getCliente(id).subscribe( (cliente) => this.cliente = cliente )
      }
    })
  }



  


  



}
