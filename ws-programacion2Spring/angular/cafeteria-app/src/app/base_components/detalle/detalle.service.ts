import { Injectable } from '@angular/core';
import { DetalleFacturaProducto} from './detalleFacturaProducto';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class DetalleService {
  
    private urlEndPoint: string = 'http://localhost:8080/api/cafeteria/detalles';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  
  
    constructor(private http: HttpClient) { }
  
    createDetalleFacturaProducto(DetalleFacturaProducto:DetalleFacturaProducto):Observable<DetalleFacturaProducto> {
      return this.http.post<DetalleFacturaProducto>(this.urlEndPoint,DetalleFacturaProducto,{headers:this.httpHeaders});
    }
    getDetalleFacturaProductos(): Observable<DetalleFacturaProducto[]>{
      return this.http.get(this.urlEndPoint).pipe(
      map(response => response as DetalleFacturaProducto[])
      );
    }
    getDetalleFacturaProducto(id: number): Observable<DetalleFacturaProducto>{
      return this.http.get<DetalleFacturaProducto>(`${this.urlEndPoint}/${id}`)
    }
    updateDetalleFacturaProducto(factura: DetalleFacturaProducto): Observable<DetalleFacturaProducto>{
      return this.http.put<DetalleFacturaProducto>(`${this.urlEndPoint}/${factura.idDetalleFacturaProducto}`, factura, {headers: this.httpHeaders})
    }
  
    deleteDetalleFacturaProducto(id: number): Observable<DetalleFacturaProducto>{
      return this.http.delete<DetalleFacturaProducto>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
    }
  
    
  
  
  }