import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './security/authentication.service';
import { JwtInfo } from './security/jwt-info';
import { Role } from './security/role.enum';

@Component({
	selector: 'prj-root',
	templateUrl: './app.component.html',
	styleUrls: []
})
export class AppComponent {

	currentUser: JwtInfo;

    constructor(private router: Router, private authenticationService: AuthenticationService) {
        this.authenticationService.currentUser.subscribe(res => this.currentUser = res);
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }

}
