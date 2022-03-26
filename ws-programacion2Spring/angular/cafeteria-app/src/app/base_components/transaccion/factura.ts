import { Cliente } from "../cliente/cliente";
import { DetalleFacturaProducto } from "../detalle/detalleFacturaProducto";
import { Empleado } from "../empleado/empleado";

export class Factura{
    idFactura: number = 0;
    empleado_string: string = "";
    fecha!: Date;
    recibo: string = "";
    empleado: Empleado | undefined;
    cliente: Cliente | undefined;
    detalles: DetalleFacturaProducto | undefined;
}