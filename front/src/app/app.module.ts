import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShoppingListComponent } from './components/shopping-list/shopping-list.component';
import { ProductComponent } from './components/product/product.component';
import { ShoppingComponent } from './components/shopping/shopping.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN, en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import {NzButtonModule} from 'ng-zorro-antd/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import en from '@angular/common/locales/en';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {NzModalModule} from 'ng-zorro-antd/modal';
import {NzGridModule} from 'ng-zorro-antd/grid';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzPopconfirmModule} from 'ng-zorro-antd/popconfirm';
import { NzMessageModule } from 'ng-zorro-antd/message';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzTableModule} from 'ng-zorro-antd/table';
registerLocaleData(zh);


// @ts-ignore
// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    ShoppingListComponent,
    ProductComponent,
    ShoppingComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NzButtonModule,
        NzIconModule,
        NzModalModule,
        NzMessageModule,
        BrowserAnimationsModule,
        NzGridModule,
        NzFormModule,
        NzPopconfirmModule,
        NzInputModule,
        NzTableModule
    ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }],
  bootstrap: [AppComponent]
})
export class AppModule { }
