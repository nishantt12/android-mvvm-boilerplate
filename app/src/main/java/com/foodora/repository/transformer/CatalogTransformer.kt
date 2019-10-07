package com.foodora.repository.transformer

import com.foodora.model.catalog.Catalog
import com.foodora.model.catalog.Product
import com.foodora.repository.database.model.DbCatalog

fun toBusinessModel(dbCatalog: DbCatalog): Catalog{
    val items = mutableListOf<Product>()
    dbCatalog?.items?.forEach {
        items.add(toBusinessModel(it))
    }
    return Catalog(items)
}





