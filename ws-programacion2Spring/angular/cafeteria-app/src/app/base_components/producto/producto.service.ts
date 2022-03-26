import { Injectable } from '@angular/core';
import {Producto} from './producto'
import { Observable } from 'rxjs';
import {of } from 'rxjs';
import {map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPoint: string = 'http://localhost:8080/api/cafeteria/productos';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  createProducto(Producto:Producto):Observable<Producto> {
    return this.http.post<Producto>(this.urlEndPoint,Producto,{headers:this.httpHeaders});
  }

  getProductos(): Observable<Producto[]>{
    return this.http.get(this.urlEndPoint).pipe(
    map(response => response as Producto[])
    );
  }
  getProducto(id: number): Observable<Producto>{
    return this.http.get<Producto>(`${this.urlEndPoint}/${id}`)
  }
  updateProducto(producto: Producto): Observable<Producto>{
    return this.http.put<Producto>(`${this.urlEndPoint}/${producto.idProducto}`, producto, {headers: this.httpHeaders})
  }
  deleteEmplado(id: number): Observable<Producto>{
    return this.http.delete<Producto>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }
}
