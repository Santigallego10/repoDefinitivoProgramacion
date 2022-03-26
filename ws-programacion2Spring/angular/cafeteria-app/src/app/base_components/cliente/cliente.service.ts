import { Injectable } from '@angular/core';
import { Cliente } from './cliente';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint: string = 'http://localhost:8080/api/cafeteria/clientes';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  constructor(private http: HttpClient) { }

  createCliente(Cliente:Cliente):Observable<Cliente> {
    return this.http.post<Cliente>(this.urlEndPoint,Cliente,{headers:this.httpHeaders});
  }
  getClientes(): Observable<Cliente[]>{
    return this.http.get(this.urlEndPoint).pipe(
    map(response => response as Cliente[])
    );
  }
  getCliente(id: number): Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`)
  }
  updateClient(cliente: Cliente): Observable<Cliente>{
    return this.http.put<Cliente>(`${this.urlEndPoint}/${cliente.idCliente}`, cliente, {headers: this.httpHeaders})
  }

  deleteClient(id: number): Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }

  


}
