import {Component, OnInit} from '@angular/core';
import {ShoppingListService} from '../../services/shopping-list.service';
import {Product} from '../product/Product';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalService} from 'ng-zorro-antd/modal';
import {FormControl, FormGroup, Validators} from '@angular/forms';


@Component({
    selector: 'app-shopping-list',
    templateUrl: './shopping-list.component.html',
    styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit {
    products: Product[];
    isCartAvailable: boolean;
    productName: string;
    productMinPrice: number;
    productMaxPrice: number;
    pageNumber: number;
    pageSize: number;
    isVisible = false;
    product: Product;
    title: string;

    constructor(private shoppingListService: ShoppingListService, private message: NzMessageService, private modal: NzModalService) {
        this.isCartAvailable = false;
    }

    ngOnInit(): void {
        this.pageSize = 10;
        this.pageNumber = 1;
        this.getProducts();
        this.product = {id: 0, name: '', imgPath: '', unit: '', price: 0};
    }

    checkCart(): void {
        this.isCartAvailable = !this.isCartAvailable;
    }

    clearInputs(): void {
        this.productName = '';
        this.productMaxPrice = null;
        this.productMinPrice = null;
    }

    getProducts(): void {
        this.shoppingListService.getAllProducts(this.pageNumber, this.pageSize).subscribe((productsResponse) => {
            this.products = productsResponse.content;
        }, error => {
            this.message.error(`抱歉，${error.error.message}`);
        });
    }

    getProductsByConditions(): void {
        this.shoppingListService.getProductsPageAbleByConditions(this.pageNumber, this.pageSize, this.productName,
            this.productMinPrice, this.productMaxPrice).subscribe((productsResponse) => {
            this.products = productsResponse.content;
        }, error => {
            this.message.error(`抱歉，${error.error.message}`);
        });
    }
    editProduct(data: Product): void {
        this.product = data;
        this.isVisible = true;
        this.title = '更新';
    }
    warning(product: Product): void{
        this.modal.warning({
            nzTitle: '删除商品',
            nzContent: '您确认要删除该商品信息吗？',
            nzOkText: '确定',
            nzCancelText: '取消',
            nzOnOk:  () =>
                new Promise((resolve, reject) => {
                    setTimeout(resolve , 1000);
                }).then(
                    () => {
                        this.shoppingListService.deleteProduct(product).subscribe((data: any) => {
                            this.message.success('恭喜您🎉，删除商品信息成功！');
                            window.location.reload();
                        }, error => {
                            if ( error.status === 0){
                                this.message.error(`抱歉，请检查您的网络！`);
                            }else {
                                this.message.error(`抱歉，${error.error.message}`);
                            }
                        });
                    }
                ).catch(() => console.log('Oops errors!'))
        });
    }

    addProduct(): void{
        this.isVisible = true;
        this.title = '创建';
    }
}
