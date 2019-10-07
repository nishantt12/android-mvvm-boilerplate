package com.foodora.repository.database.dao.impl

import com.foodora.repository.database.dao.CatalogDao
import com.foodora.repository.database.model.DbCatalog
import io.realm.Realm
import timber.log.Timber

class CatalogDaoImpl : CatalogDao {

    override fun saveCatalog(dbCatalog: DbCatalog?) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.where(DbCatalog::class.java).findAll().deleteAllFromRealm()
        realm.copyToRealm(dbCatalog)
        realm.commitTransaction()
    }

    override fun getCatalog(): DbCatalog? {
        val realm = Realm.getDefaultInstance()
        val realmResults = realm.where(DbCatalog::class.java).findAll()
        if (realmResults.isNotEmpty()) {
            Timber.d("getCatalog from realm")
            return realmResults[0]
        } else {
            Timber.d("realm is empty")

        }
        return null
    }
}