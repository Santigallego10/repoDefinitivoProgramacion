import { Component, OnInit } from '@angular/core';
import {Factura} from "../transaccion/factura"
import { Router, ActivatedRoute } from '@angular/router';
import {FacturaService} from "../transaccion/factura.service"
import Swal from 'sweetalert2';
import html2canvas from 'html2canvas';
import {DetalleService} from "../detalle/detalle.service"
import { DetalleFacturaProducto } from '../detalle/detalleFacturaProducto';


declare const require: any;

  const jspdf = require('jspdf');

@Component({
  selector: 'app-factura-view',
  templateUrl: './factura-view.component.html'
})
export class FacturaViewComponent implements OnInit {

  

  detalles: DetalleFacturaProducto[] = [];

  factura: Factura | undefined;
  
  constructor(
    private FacturaService:FacturaService,
    private DetalleService: DetalleService,
    private router: Router,
    private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarFactura();
    this.DetalleService.getDetalleFacturaProductos().subscribe(
      detalles => this.detalles = detalles
    )
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
    html2canvas(DATA!, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');
      const bufferX = 20;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`PDF DOMICILIOS`);
    });
  }


}
