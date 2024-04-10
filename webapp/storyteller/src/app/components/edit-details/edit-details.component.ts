import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-edit-details',
  templateUrl: './edit-details.component.html',
  styleUrl: './edit-details.component.scss'
})
export class EditDetailsComponent implements OnInit{

  constructor(private keyCloakService: KeycloakService) {
  }

  ngOnInit() {
    console.log(this.keyCloakService.getUsername());
  }

}
