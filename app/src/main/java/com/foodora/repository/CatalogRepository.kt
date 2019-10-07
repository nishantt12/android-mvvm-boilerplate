package com.foodora.repository

import com.foodora.base.BaseRepository
import com.foodora.model.catalog.Catalog
import com.foodora.model.catalog.Product
import com.foodora.repository.api.CatalogService
import com.foodora.repository.database.dao.impl.CatalogDaoImpl
import com.foodora.repository.transformer.toBusinessModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class CatalogRepository constructor(var catalogService: CatalogService) : BaseRepository() {
    private var compositeDisposable = CompositeDisposable()

    companion object {
        private val TAG = "CatalogRepository"
    }

    fun getCatalog(isForced: Boolean = false, callback: (Catalog?, Boolean) -> Unit) {
        val catalogDao = CatalogDaoImpl()
        val dbCatalog = catalogDao.getCatalog()
        if (isForced || dbCatalog == null)
            catalogService.getCatalog().observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Timber.d("getCatalog")
                        catalogDao.saveCatalog(it)
                        callback(toBusinessModel(it), false)

                    }, {
                        callback(null, true)
                    })
        else {
            Timber.d("")
            callback(toBusinessModel(dbCatalog), false)
        }


    }

    fun getProduct(productId: String?, callback: (Product?) -> Unit) {
        getCatalog { catalog, b ->
            catalog?.let {
                callback.invoke(findProduct(it, productId))
            }
        }
    }

    private fun findProduct(catalog: Catalog, productId: String?): Product? {
        return catalog.items.find { it.id == productId }
    }

    override fun onCleared() {
        compositeDisposable.let { compositeDisposable.dispose() }
    }
}