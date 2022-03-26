import { Component, OnInit } from '@angular/core';
import { Empleado } from './empleado';
import { EmpleadoService } from './empleado.service'
import Swal from 'sweetalert2';


@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {

  empleados: Empleado[] = [];

  constructor(private EmpleadoService: EmpleadoService) { 

  }

  ngOnInit(): void {
    this.EmpleadoService.getEmpleados().subscribe(
      empleados => this.empleados  = empleados
    )
  }

  delete(empleado: Empleado): void {
    Swal.fire({
      title: 'Está seguro??',
      text: `¿Seguro que desea eliminar al empleado ${empleado.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.EmpleadoService.deleteEmplado(empleado.idEmpleado).subscribe(
          response => {
            console.log('llegue sapo');
            this.empleados = this.empleados.filter(cli => cli !== empleado)
            Swal.fire(
              'Empleado Eliminado!',
              `Empleado ${empleado.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )
      }
    }) }

}
