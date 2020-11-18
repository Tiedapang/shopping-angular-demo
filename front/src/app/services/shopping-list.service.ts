import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../components/product/Product';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ShoppingListService {
    constructor(private http: HttpClient) {
    }

    getProducts(): Observable<Product[]> {
        return this.http.get<Product[]>('/api/product/list');
    }

    saveProduct(product: Product): Observable<any> {
        return this.http.post('/api/product', product);
    }

    deleteProduct(product: Product): Observable<any> {
        return this.http.delete(`/api/product/${product.id}`);
    }

    getAllProducts(pageNumber: number, pageSize: number): Observable<any> {
        return this.http.get(`/api/product?pageSize=${pageSize}&pageNumber=${pageNumber}`);
    }

    getProductsPageAbleByConditions(pageNumber: number, pageSize: number, productName: string,
                                    productMinPrice: number, productMaxPrice: number): Observable<any> {
        return this.http.get(`/api/product?pageSize=${pageSize}&pageNumber=${pageNumber}
        &productName=${productName}&productMinPrice=${productMinPrice}&productMaxPrice=${productMaxPrice}`);
    }
}
