import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto/producto';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductoService } from '../producto/producto.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-producto-form',
  templateUrl: './producto-form.component.html',
  styleUrls: ['./producto-form.component.css']
})
export class ProductoFormComponent implements OnInit {

  titulo: string = "Formulario de Productos";
  productos: Producto[] = [];
  flag:boolean = false;
  producto: Producto = new Producto();

  constructor(
    private ProductoService: ProductoService,
     private router: Router,
     private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.cargarProducto();
    this.flag = true;
    this.ProductoService.getProductos().subscribe(
      productos => this.productos = productos
    )
  }

  actualizarProducto():void{
    this.ProductoService.updateProducto(this.producto).subscribe(
      producto =>{
        this.router.navigate(['/productos'])
        swal.fire('Producto Actualizado', `Producto ${this.producto.nombre} actualizado con éxito!`, 'success')
      }
    )
  }


  

  
  public crearProducto():void{
    this.productos.forEach((productucho) =>{
      if(productucho.nombre == this.producto.nombre){
        console.log('esta repetido mi so');
        swal.fire('Producto ya registrado', `El numero de documento ingresado pertence a otro producto`);
        this.flag = false;
      }else{
      }
    });
    if(this.flag==true){
      this.ProductoService.createProducto(this.producto).subscribe(
        producto=> {
          this.router.navigate(['/productos'])

          swal.fire('Nuevo producto', `Producto ${this.producto.nombre} creado con éxito!`, 'success')
        }
    );
    }
  }

  cargarProducto():void{
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.ProductoService.getProducto(id).subscribe( (producto) => this.producto = producto )
      }
    })
  }

}
