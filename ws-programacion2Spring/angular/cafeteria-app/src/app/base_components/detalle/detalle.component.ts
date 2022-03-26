import { Component, OnInit } from '@angular/core';
import { DetalleFacturaProducto } from './detalleFacturaProducto';
import { DetalleService } from './detalle.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  detalles: DetalleFacturaProducto[] = [];

  constructor(private DetalleService: DetalleService) { }

  ngOnInit(): void {
    this.DetalleService.getDetalleFacturaProductos().subscribe(
      detalles => this.detalles = detalles
    )
  }

  delete(detalle: DetalleFacturaProducto): void {
    Swal.fire({
      title: 'Está seguro??',
      text: `¿Seguro que desea eliminar el detalle${detalle.producto?.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.DetalleService.deleteDetalleFacturaProducto(detalle.idDetalleFacturaProducto).subscribe(
          response => {
            this.detalles = this.detalles.filter(cli => cli !== detalle)
            Swal.fire(
              'DetalleFacturaProducto Eliminado!',
              `DetalleFacturaProducto ${detalle.producto?.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )
      }
    }) }

}
