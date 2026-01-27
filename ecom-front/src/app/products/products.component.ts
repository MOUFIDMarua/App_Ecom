import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  products: any;
  constructor(private http:HttpClient) {}
  ngOnInit() : void {
    // Route through the gateway (CORS enabled) + base path /api from data-rest
    this.http.get("http://localhost:8888/inventory-service/api/products").subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
      }
    });
  }
}
