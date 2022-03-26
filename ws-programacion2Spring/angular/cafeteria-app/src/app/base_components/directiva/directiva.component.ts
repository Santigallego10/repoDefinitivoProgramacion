import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html',
})
export class DirectivaComponent implements OnInit {

  listaCursos: string[] = ['Java','Java EE','Spring'];
  habilitar: boolean = true;

  
  constructor(){
  }

  setHabilitar(): void {
    this.habilitar = (this.habilitar == true)?false:true;
  }

  ngOnInit(): void {
  }

}
