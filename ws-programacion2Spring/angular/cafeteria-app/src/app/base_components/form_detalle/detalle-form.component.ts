import { Component, OnInit } from '@angular/core';
import { DetalleFacturaProducto } from '../detalle/detalleFacturaProducto';
import { Router, ActivatedRoute } from '@angular/router';
import {DetalleService} from '../detalle/detalle.service';
import {FacturaService} from '../transaccion/factura.service';
import {ProductoService} from '../producto/producto.service';
import {DetalleComponent} from '../detalle/detalle.component';
import swal from 'sweetalert2';
import { Factura } from '../transaccion/factura';
import { Producto } from '../producto/producto';



@Component({
  selector: 'app-detalle-form',
  templateUrl: './detalle-form.component.html',
})
export class DetalleFormComponent implements OnInit {



  facturas: Factura[] = [];
  productos: Producto[] = [];
  detalles: DetalleFacturaProducto[] = [];
  flag: boolean = false;



  titulo: String = "Formulario de Detalles";
  detalle: DetalleFacturaProducto = new DetalleFacturaProducto();

  constructor(
     private DetalleService: DetalleService,
     private FacturaService: FacturaService,
     private ProductoService: ProductoService,
     private router: Router,
     private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarDetalle();
    this.flag = true;
    this.DetalleService.getDetalleFacturaProductos().subscribe(
      detalles => this.detalles = detalles
    )
    this.FacturaService.getFacturas().subscribe(
      facturas => this.facturas = facturas
    )
    this.ProductoService.getProductos().subscribe(
      productos => this.productos = productos
    )
  }

  buscarFactura(recibo:any):void{

    let facturaE: Factura = new Factura();

    this.facturas.forEach((facturucha) =>{
      if(recibo == facturucha.recibo){
        facturaE = facturucha;
        console.log(facturaE);
      }
    });

    this.detalle.factura = facturaE;
  }

  buscarProducto(nombre:any):void{

    let productoE: Producto = new Producto();

    this.productos.forEach((productucho) =>{
      if(nombre == productucho.nombre){
        productoE = productucho;
        console.log(productoE);
      }
    });

    this.detalle.producto = productoE;
  }

  actualizarDetalle():void{
    this.buscarFactura(this.detalle.factura)
    this.buscarProducto(this.detalle.producto)
    this.DetalleService.updateDetalleFacturaProducto(this.detalle).subscribe(
      detalle =>{
        this.router.navigate(['/detalles'])
        swal.fire('Detalle Actualizado', `Cliente ${this.detalle.producto?.nombre} actualizado con éxito!`, 'success')
      }
    )
  }

  public crearDetalle():void{
    this.buscarFactura(this.detalle.factura)
    this.buscarProducto(this.detalle.producto)
      this.DetalleService.createDetalleFacturaProducto(this.detalle).subscribe(
        cliente=> {
          this.router.navigate(['/detalles'])
          swal.fire('Nuevo detalle', `Detalle para la factura: ${this.detalle.factura?.recibo} creado con éxito!`, 'success')
        }
    );
  }

  cargarDetalle():void{
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.DetalleService.getDetalleFacturaProducto(id).subscribe( (detalle) => this.detalle = detalle )
      }
    })
  }

}