import { Component, OnInit } from '@angular/core';
import {Producto} from "./producto";
import { ProductoService } from './producto.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
})
export class ProductoComponent implements OnInit {

  productos: Producto[] = [];

  constructor(private ProductoService: ProductoService) { }

  ngOnInit(): void {
    this.ProductoService.getProductos().subscribe(
      productos => this.productos = productos
    )
  }

  delete(producto: Producto): void {
    Swal.fire({
      title: 'Está seguro??',
      text: `¿Seguro que desea eliminar al producto ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.ProductoService.deleteEmplado(producto.idProducto).subscribe(
          response => {
            console.log('llegue sapo');
            this.productos = this.productos.filter(cli => cli !== producto)
            Swal.fire(
              'Producto Eliminado!',
              `Producto ${producto.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )
      }
    }) }

}
