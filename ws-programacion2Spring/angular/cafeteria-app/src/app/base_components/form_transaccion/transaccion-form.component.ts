import { Component, OnInit } from '@angular/core';
import { Factura } from '../transaccion/factura';
import { Router, ActivatedRoute } from '@angular/router';
import {Observable } from 'rxjs';
import {FacturaService} from '../transaccion/factura.service'
import {EmpleadoService} from '../empleado/empleado.service'
import {ClienteService} from '../cliente/cliente.service'
import swal from 'sweetalert2';
import { Empleado } from '../empleado/empleado';
import { Cliente } from '../cliente/cliente';



@Component({
  selector: 'app-transaccion-form',
  templateUrl: './transaccion-form.component.html',
  styleUrls: ['./transaccion-form.component.css']
})
export class FacturaFormComponent implements OnInit {

  titulo: string = "Formulario de Facturas";
  facturas: Factura[] = [];
  flag:boolean = false;
  factura: Factura = new Factura();
  empleados: Empleado[] = [];
  clientes: Cliente[] = [];

  constructor(
       private FacturaService: FacturaService,
       private EmpleadoService: EmpleadoService,
       private ClienteService: ClienteService,
       private router: Router,
       private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarFactura();
    this.flag = true;
    this.FacturaService.getFacturas().subscribe(
      facturas => this.facturas = facturas
    )
    this.EmpleadoService.getEmpleados().subscribe(
      empleados => this.empleados = empleados
    )
    this.ClienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    )
  }

  actualizarFactura():void{
    this.buscarEmpleado(this.factura.empleado);
    this.buscarCliente(this.factura.cliente);
    this.FacturaService.updateFactura(this.factura).subscribe(
      factura =>{
        this.router.navigate(['/facturas'])
        swal.fire('Factura Actualizado', `Factura ${this.factura.recibo} actualizado con éxito!`, 'success')
      }
    )
  }

  buscarEmpleado(nombre:any):void{

    let empleadoE: Empleado = new Empleado;

    this.empleados.forEach((empleaducho) =>{
      if(nombre == empleaducho.nombre){
        empleadoE = empleaducho;
        console.log(empleadoE);
      }
    });

    this.factura.empleado = empleadoE;
  }

  buscarCliente(nombre:any):void{

    let clienteE: Cliente = new Cliente;

    this.clientes.forEach((clientucho) =>{
      if(nombre == clientucho.nombre){
        clienteE = clientucho;
        console.log(clienteE);
      }
    });

    this.factura.cliente = clienteE;
  }


  

  
  public crearFactura():void{
    this.buscarEmpleado(this.factura.empleado);
    this.buscarCliente(this.factura.cliente);
    this.facturas.forEach((facturucha) =>{
      if(facturucha.recibo == this.factura.recibo){
        console.log('esta repetido mi so');
        swal.fire('Factura ya registrada', 'El numero de factura ya existe');
        this.flag = false;
      }else{
      }
    });
    if(this.flag==true){
      this.FacturaService.createFactura(this.factura).subscribe(
        factura=> {
          this.router.navigate(['/facturas'])

          swal.fire('Nuevo factura', `Factura ${this.factura.recibo} creada con éxito!`, 'success')
        }
    );
    }
  }

  cargarFactura():void{
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.FacturaService.getFactura(id).subscribe( (factura) => this.factura = factura )
      }
    })
  }

}
      
