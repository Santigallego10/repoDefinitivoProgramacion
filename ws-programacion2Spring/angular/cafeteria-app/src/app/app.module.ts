import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HeaderComponent } from './base_components/header/header.component';
import { FooterComponent } from './base_components/footer/footer.component';
import { AppComponent } from './app.component';
import { DirectivaComponent } from './base_components/directiva/directiva.component';
import { ClienteComponent } from './base_components/cliente/cliente.component';
import { ClienteService } from './base_components/cliente/cliente.service';
import {EmpleadoService} from '../app/base_components/empleado/empleado.service'
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { RouterModule,Routes } from '@angular/router';
import { ClienteFormComponent } from './base_components/form_cliente/cliente-form.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './base_components/home/home/home.component';
import { EmpleadoComponent } from './base_components/empleado/empleado.component';
import { EmpleadoFormComponent } from './base_components/form_empleado/empleado-form.component';
import { ProductoComponent } from './base_components/producto/producto.component';
import { ProductoFormComponent } from './base_components/form_producto/producto-form.component';
import { ProductoService } from './base_components/producto/producto.service';
import { FacturaComponent } from './base_components/transaccion/factura.component';
import { FacturaFormComponent } from './base_components/form_transaccion/transaccion-form.component';
import { FacturaService } from './base_components/transaccion/factura.service';
import { DetalleComponent } from './base_components/detalle/detalle.component';
import { DetalleService } from './base_components/detalle/detalle.service';
import { DetalleFormComponent } from './base_components/form_detalle/detalle-form.component';
import { ConsultaFormComponent } from './base_components/form_consultas/consulta-form.component';
import { FacturaViewComponent } from './base_components/facturaView/factura-view.component';



const routes: Routes=[
  {path:'',redirectTo:'/home',pathMatch:'full'},
  {path:'directivas',component:DirectivaComponent},
  {path:'formCliente',component:ClienteFormComponent},
  {path:'formEmpleado',component:EmpleadoFormComponent},
  {path:'formFactura',component:FacturaFormComponent},
  {path:'formDetalle',component:DetalleFormComponent},
  {path:'formProducto',component:ProductoFormComponent},
  {path:'formFactura/:id',component:FacturaFormComponent},
  {path:'clientes',component:ClienteComponent},
  {path:'empleados',component:EmpleadoComponent},
  {path:'productos',component:ProductoComponent},
  {path:'facturas',component:FacturaComponent},
  {path:'detalles',component:DetalleComponent},
  {path:'facturaView/:id',component:FacturaViewComponent},
  {path:'formDetalle/:id',component:DetalleFormComponent},
  {path:'formCliente/:id',component:ClienteFormComponent},
  {path:'formEmpleado/:id',component:EmpleadoFormComponent},
  {path:'formProducto/:id',component:ProductoFormComponent},
  {path:'formConsulta',component:ConsultaFormComponent},
  {path:'home',component:HomeComponent},
]


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClienteComponent,
    ClienteFormComponent,
    HomeComponent,
    EmpleadoComponent,
    EmpleadoFormComponent,
    ProductoComponent,
    ProductoFormComponent,
    FacturaComponent,
    FacturaFormComponent,
    DetalleComponent,
    DetalleFormComponent,
    ConsultaFormComponent,
    FacturaViewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [EmpleadoService, ClienteService, ProductoService, FacturaService, DetalleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
