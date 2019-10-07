package com.foodora.repository.api

import com.foodora.model.catalog.Catalog
import com.foodora.model.catalog.Product

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogService {

    @GET(ApiUrl.GET_CATALOG)
    fun getCatalog(): Observable<Catalog>


    @GET(ApiUrl.GET_PRODUCT)
    fun getProduct(@Path("product_id") productId: String?): Observable<Product>

}