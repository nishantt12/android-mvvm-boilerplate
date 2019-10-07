package com.foodora.viewModels

import android.content.Context
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodora.R
import com.foodora.model.catalog.Catalog
import com.foodora.model.catalog.Product
import com.foodora.repository.CatalogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ProductViewModel @Inject constructor(
        private val catalogRepository: CatalogRepository, val context: Context) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var showProgress: MutableLiveData<Void> = MutableLiveData()
    var hideProgress: MutableLiveData<Void> = MutableLiveData()
    var product: MutableLiveData<Product> = MutableLiveData()


    fun getProduct(productId: String) {
        showProgress.postValue(null)
        catalogRepository.getProduct(productId) {
            product.postValue(it)
        }

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    companion object {
        private val TAG = ProductViewModel::class.java.simpleName
    }
}
