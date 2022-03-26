import { Component, OnInit } from '@angular/core';
import {Factura} from "./factura"
import { Router, ActivatedRoute } from '@angular/router';
import {FacturaService} from "./factura.service"
import Swal from 'sweetalert2';
import html2canvas from 'html2canvas';
import {DetalleService} from "../detalle/detalle.service"
import { DetalleFacturaProducto } from '../detalle/detalleFacturaProducto';

declare const require: any;

const jspdf = require('jspdf');

@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  facturas:Factura[] = [];
  detalles: DetalleFacturaProducto[] = [];
  detallesPropios:DetalleFacturaProducto[] = [];
  factura: Factura | undefined;


  constructor(
    private FacturaService:FacturaService,
    private DetalleService: DetalleService,
    private router: Router,
    private activateRoute: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.cargarFactura();
    this.FacturaService.getFacturas().subscribe(
      facturas => this.facturas = facturas
    )
    this.DetalleService.getDetalleFacturaProductos().subscribe(
      detalles => this.detalles = detalles
    )
    this.cargarDetallesPropios();
  }

  cargarDetallesPropios() {
    this.detalles.forEach((detallucho)=>{
      if(detallucho.factura?.recibo == this.factura?.recibo){
        this.detallesPropios.push(detallucho);
      }
    })
  }

  cargarFactura():void{
    this.activateRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.FacturaService.getFactura(id).subscribe( (factura) => this.factura = factura )
      }
    })
  }

  downloadPDF() {
    const DATA = document.getElementById('htmlData');
    const doc = new jspdf('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 3
    };
    html2canvas(document.body).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save('FACTURAS_MOCAWA_CAFE.pdf');
    });
  }

  delete(factura: Factura): void {
    Swal.fire({
      title: 'Está seguro??',
      text: `¿Seguro que desea eliminar al factura ${factura.recibo}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.FacturaService.deleteFactura(factura.idFactura).subscribe(
          response => {
            this.facturas = this.facturas.filter(cli => cli !== factura)
            Swal.fire(
              'Factura Eliminado!',
              `Factura ${factura.recibo} eliminado con éxito.`,
              'success'
            )
          }
        )
      }
    }) }

}


