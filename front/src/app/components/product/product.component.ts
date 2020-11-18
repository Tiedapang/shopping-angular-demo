import {Component, Input, OnInit} from '@angular/core';
import {Product} from './Product';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ShoppingListService} from '../../services/shopping-list.service';
import {NzModalService} from 'ng-zorro-antd/modal';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
    @Input() product: Product;
    @Input() isVisible: boolean;
    @Input() title = '创建';
    updateProduct: any;

    // tslint:disable-next-line:max-line-length
    constructor(private shoppingListService: ShoppingListService, private message: NzMessageService, private modal: NzModalService) {
    }

    ngOnInit(): void {
        this.updateProduct = new FormGroup({
            id: new FormControl(this.product.id),
            name: new FormControl(this.product.name, Validators.required),
            price: new FormControl(this.product.price, [Validators.required, Validators.pattern(/^[+-]?(0|([1-9]\d*))(\.\d+)?$/)]),
            unit: new FormControl(this.product.unit),
            imgPath: new FormControl(this.product.imgPath)
        });
    }

    handleOk(): void {
        this.shoppingListService.saveProduct(this.updateProduct.value).subscribe((data: any) => {
            this.message.success(`恭喜您🎉，${this.title}商品信息成功！`);
            this.product = this.updateProduct.value;
        }, error => {
            if (error.status === 0) {
                this.message.error(`抱歉，请检查您的网络！`);
            }
        });
        this.isVisible = false;
    }

    handleCancel(): void {
        this.isVisible = false;
    }

    submitForm(): void {
    }
}
