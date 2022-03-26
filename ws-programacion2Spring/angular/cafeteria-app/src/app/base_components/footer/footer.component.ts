import { Component } from "@angular/core";
 
@Component({
    selector:'app-footer', //el nombre da igual pero ese es como lo llamo
    templateUrl: './footer.html' //llamo al archivo html
    //---como minimo deben estar los dos anteriores----
})
export class FooterComponent{
    title = "Footer Component :)";
}