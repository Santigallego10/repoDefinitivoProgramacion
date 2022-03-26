import { Component, OnInit } from '@angular/core';
import {Empleado} from '../empleado/empleado';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpleadoService } from '../empleado/empleado.service';
import swal from 'sweetalert2';



@Component({
  selector: 'app-empleado-form',
  templateUrl: './empleado-form.component.html',
  styleUrls: ['./empleado-form.component.css']
})
export class EmpleadoFormComponent implements OnInit {

  empleados: Empleado[] = [];
  flag:boolean = false;
  titulo: String = "Formulario de Empleados";
  empleado: Empleado = new Empleado();

  constructor(
    private EmpleadoService: EmpleadoService,
     private router: Router,
     private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarEmpleado();
    this.flag = true;
    this.EmpleadoService.getEmpleados().subscribe(
      empleados => this.empleados = empleados
    )
  }

  actualizarEmpleado():void{
    this.EmpleadoService.updateEmpleado(this.empleado).subscribe(
      empleado =>{
        this.router.navigate(['/empleados'])
        swal.fire('Empleado Actualizado', `Empleado ${this.empleado.nombre} actualizado con éxito!`, 'success')
      }
    )
  }

  public crearEmpleado():void{
    this.empleados.forEach((empleaducho) =>{
      if(empleaducho.documento == this.empleado.documento){
        console.log('esta repetido mi so');
        swal.fire('Empleado ya registrado', `El numero de documento ingresado pertence a otro empleado`);
        this.flag = false;
      }else{
      }
    });
    if(this.flag==true){
      this.EmpleadoService.createEmpleado(this.empleado).subscribe(
        empleado=> {
          this.router.navigate(['/empleados'])

          swal.fire('Nuevo empleado', `Empleado ${this.empleado.nombre} creado con éxito!`, 'success')
        }
    );
    }
  }

  cargarEmpleado(): void {
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.EmpleadoService.getEmpleado(id).subscribe( (empleado) => this.empleado = empleado )
      }
    })
  }

}
