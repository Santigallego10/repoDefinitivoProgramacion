import { Component } from '@angular/core';

@Component({  //archivo TS que define estructura del componente 
  selector: 'app-root', //identificador
  templateUrl: './app.component.html', //etiqueta html
  styleUrls: ['./app.component.css'] //CSS enalazado
})
export class AppComponent {
  title = 'cafeteria-app';
  curso: string= "Programacion 2"; //no es necesario definir la variable pero se puede hacer
  nombre = "Santiago Gallego Gil";
  edad: number = 18;
}
