package com.foodora.repository.transformer

import com.foodora.model.catalog.Price
import com.foodora.repository.database.model.DbPrice


fun toBusinessModel(dbPrice: DbPrice?): Price? {
    dbPrice?.let {
        return Price(dbPrice?.current, dbPrice.original, dbPrice.currency)
    }
    return null
}





