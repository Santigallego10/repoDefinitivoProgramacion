import { Producto } from '../producto/producto'
import { Factura } from '../transaccion/factura'

export class DetalleFacturaProducto{
    idDetalleFacturaProducto: number = 0;
    producto: Producto | undefined;
    factura: Factura | undefined;
}