package com.foodora.repository.database.dao

import com.foodora.repository.database.model.DbCatalog

interface CatalogDao {
    fun saveCatalog(dbCatalog: DbCatalog?)
    fun getCatalog(): DbCatalog?

}