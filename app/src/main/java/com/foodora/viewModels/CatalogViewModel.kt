package com.foodora.viewModels

import android.content.Context
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodora.R
import com.foodora.model.catalog.Catalog
import com.foodora.repository.CatalogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
        private val catalogRepository: CatalogRepository, val context: Context) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var showProgress: MutableLiveData<Void> = MutableLiveData()
    var hideProgress: MutableLiveData<Void> = MutableLiveData()
    var catalog: MutableLiveData<Catalog> = MutableLiveData()


    fun getCatalog(isForced: Boolean) {
        showProgress.postValue(null)
        catalogRepository.getCatalog(isForced).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("getCatalog")
                    hideProgress.postValue(null)
                    catalog.postValue(it)
                }, {
                    hideProgress.postValue(null)
                    errorMessage.postValue(context.getString(R.string.somehting_went_wrong))
                })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    companion object {
        private val TAG = CatalogViewModel::class.java.simpleName
    }
}
