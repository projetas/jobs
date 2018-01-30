import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {VehicleModule} from './vehicle/vehicle.module';
import {BrowserModule} from '@angular/platform-browser';
import {async, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {RouterOutlet} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AppRoutes} from './app.routes';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [
        RouterOutlet
      ],
      imports: [
        BrowserModule,
        FormsModule,
        AppRoutes,
        HttpClientModule,
        VehicleModule,
        BrowserAnimationsModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('app');
  }));
  it('should render title in a h1 tag', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.navbar-brand').textContent).toContain('Vehicle Work');
  }));
});
