package com.foodora.model.catalog

data class Product(val id: String?, val sku: String?, val name: String?, val brand: String?,
                   val image: String?, val published_at: String?, val is_active: Boolean, val price: Price?)