import { Component } from "@angular/core";
 
@Component({
    selector:'app-header', //el nombre da igual pero ese es como lo llamo
    styleUrls: ['./header.css'],
    templateUrl: './header.html' //llamo al archivo html
    //---como minimo deben estar los dos anteriores----
})
export class HeaderComponent{
    title = "Header Component :)";
}




