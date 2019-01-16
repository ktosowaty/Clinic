import { Component, OnInit} from '@angular/core';
import { User } from './_models/user';
import { Router } from '@angular/router';
import { AuthenticationService} from './_services/authentication.service';
import { ToastrService } from 'ngx-toastr';
declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Bolo&Bajer ginekolodzy-położnicy';
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  ngOnInit(): void {}
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
