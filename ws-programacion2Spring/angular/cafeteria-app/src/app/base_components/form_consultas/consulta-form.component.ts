import { Component, OnInit } from '@angular/core';
import { EmpleadoService} from "../empleado/empleado.service"
import { Router, ActivatedRoute } from '@angular/router';

import swal from 'sweetalert2';
import { Empleado } from '../empleado/empleado';


@Component({
  selector: 'app-consulta-form',
  templateUrl: './consulta-form.component.html',
  styleUrls: ['./consulta-form.component.css']
})
export class ConsultaFormComponent implements OnInit {

  nombre: string = "";
  empleado: Empleado | undefined;

  constructor(private EmpleadoService: EmpleadoService,
    private router: Router,
     private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  public consultar():void{
    console.log(this.nombre);
      this.EmpleadoService.consultar1(this.nombre).subscribe(
        empleado=> {
          this.empleado = empleado;
          console.log(empleado);
          swal.fire('Nuevo detalle', `Detalle para la factura: creado con éxito!`, 'success')
        }
    );
  }

}
