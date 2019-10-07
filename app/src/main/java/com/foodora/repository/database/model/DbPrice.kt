package com.foodora.repository.database.model

import io.realm.RealmObject

open class DbPrice(var current: Double = 0.0, var original: Double = 0.0, var currency: String? = null) : RealmObject()