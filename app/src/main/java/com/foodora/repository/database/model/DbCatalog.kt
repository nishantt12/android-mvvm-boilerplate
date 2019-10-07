package com.foodora.repository.database.model

import io.realm.RealmList
import io.realm.RealmObject

open class DbCatalog(var items: RealmList<DbProduct>? =null): RealmObject()