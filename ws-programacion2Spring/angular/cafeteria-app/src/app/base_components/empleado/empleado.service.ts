import { Injectable } from '@angular/core';
import {Empleado} from './empleado'
import { Observable } from 'rxjs';
import {of } from 'rxjs';
import {map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private urlEndPoint: string = 'http://localhost:8080/api/cafeteria/empleados';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  createEmpleado(Empleado:Empleado):Observable<Empleado> {
    return this.http.post<Empleado>(this.urlEndPoint,Empleado,{headers:this.httpHeaders});
  }

  getEmpleados(): Observable<Empleado[]>{
    return this.http.get(this.urlEndPoint).pipe(
    map(response => response as Empleado[])
    );
  }
  getEmpleado(id: number): Observable<Empleado>{
    return this.http.get<Empleado>(`${this.urlEndPoint}/${id}`)
  }
  updateEmpleado(empleado: Empleado): Observable<Empleado>{
    return this.http.put<Empleado>(`${this.urlEndPoint}/${empleado.idEmpleado}`, empleado, {headers: this.httpHeaders})
  }
  deleteEmplado(id: number): Observable<Empleado>{
    return this.http.delete<Empleado>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }

  consultar1(nombre: String): Observable<Empleado>{
    return this.http.put<Empleado>(`${this.urlEndPoint}/consulta/${nombre}`, {headers: this.httpHeaders})
  }
}
