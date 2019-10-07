package com.foodora.repository

import com.foodora.base.BaseRepository
import com.foodora.model.catalog.Catalog
import com.foodora.model.catalog.Product
import com.foodora.repository.api.CatalogService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CatalogRepository constructor(var catalogService: CatalogService) : BaseRepository() {
    private var compositeDisposable = CompositeDisposable()

    companion object {
        private val TAG = "CatalogRepository"
    }

    fun getCatalog(isForced: Boolean = false): Observable<Catalog> {
        return catalogService.getCatalog()
    }

    fun getProduct(productId: String?, callback: (Product?) -> Unit) {
        getCatalog().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.invoke(findProduct(it, productId))
                }
                )


    }

    private fun findProduct(catalog: Catalog, productId: String?): Product? {
        return catalog.items.find { it.id == productId }
    }

    override fun onCleared() {
        compositeDisposable.let { compositeDisposable.dispose() }
    }
}