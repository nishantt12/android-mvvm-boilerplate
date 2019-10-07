package com.foodora.repository.database.model

import io.realm.RealmObject

open class DbProduct(var id: String? = null, var sku: String?= null, var name: String?= null, var brand: String?= null,
                var image: String?= null, var published_at: String?= null, var is_active: Boolean = true, var price: DbPrice? = null): RealmObject()