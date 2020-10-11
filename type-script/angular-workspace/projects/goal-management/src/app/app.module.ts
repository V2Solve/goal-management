import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopbarComponent } from './topbar/topbar.component';
import { MenubarComponent } from './menubar/menubar.component';
import { BottombarComponent } from './bottombar/bottombar.component';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    MenubarComponent,
    BottombarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
