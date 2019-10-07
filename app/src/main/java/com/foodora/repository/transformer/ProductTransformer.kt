package com.foodora.repository.transformer

import com.foodora.model.catalog.Product
import com.foodora.repository.database.model.DbProduct


fun toBusinessModel(dbProduct: DbProduct): Product{

    return Product(dbProduct.id,dbProduct.sku, dbProduct.name, dbProduct.brand
    ,dbProduct.image, dbProduct.published_at, dbProduct.is_active, toBusinessModel(dbProduct.price))


}





