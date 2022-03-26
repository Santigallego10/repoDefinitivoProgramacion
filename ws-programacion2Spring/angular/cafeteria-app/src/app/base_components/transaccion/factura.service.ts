import { Injectable } from '@angular/core';
import { Factura } from './factura';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class FacturaService {
  
    private urlEndPoint: string = 'http://localhost:8080/api/cafeteria/facturas';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  
  
    constructor(private http: HttpClient) { }
  
    createFactura(Factura:Factura):Observable<Factura> {
      return this.http.post<Factura>(this.urlEndPoint,Factura,{headers:this.httpHeaders});
    }
    getFacturas(): Observable<Factura[]>{
      return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Factura[])
      );
    }
    getFactura(id: number): Observable<Factura>{
      return this.http.get<Factura>(`${this.urlEndPoint}/${id}`)
    }
    updateFactura(factura: Factura): Observable<Factura>{
      return this.http.put<Factura>(`${this.urlEndPoint}/${factura.idFactura}`, factura, {headers: this.httpHeaders})
    }
  
    deleteFactura(id: number): Observable<Factura>{
      return this.http.delete<Factura>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
    }
  
    
  
  
  }