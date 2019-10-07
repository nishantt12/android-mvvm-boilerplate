package com.foodora.activities

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.foodora.MyApplication
import com.foodora.R
import com.foodora.adapter.CatalogAdapter
import com.foodora.base.BaseActivity
import com.foodora.databinding.ActivityCatalogBinding
import com.foodora.model.catalog.Catalog
import com.foodora.util.ViewModelUtil
import com.foodora.viewModels.CatalogViewModel

import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.activity_catalog.toolbar
import kotlinx.android.synthetic.main.layout_toolbar.*
import timber.log.Timber
import javax.inject.Inject

class CatalogActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var activityCatalogBinding: ActivityCatalogBinding? = null
    @Inject
    lateinit var catalogViewModel: CatalogViewModel
    private var compositeDisposable = CompositeDisposable()
    private var showProgress: MutableLiveData<Void> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        activityCatalogBinding = DataBindingUtil.setContentView(this, R.layout.activity_catalog)
        init()
        setObservers()
        setupSwipeRefresh()
        fetchCatalog()
    }

    private fun fetchCatalog(isForced: Boolean = false) {
        catalogViewModel.getCatalog(isForced)
    }

    private fun init() {
        MyApplication.getComponent().injectCatalogActivity(this)
        val viewModelFactory = ViewModelUtil.createFor(catalogViewModel)
        catalogViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                CatalogViewModel::class.java)
        activityCatalogBinding?.catalogViewModel = catalogViewModel

        val rotation = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation
        if (rotation == 0 || rotation == 180) {
            recyclerView.layoutManager = GridLayoutManager(this, 1)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)

        }


    }

    private fun setObservers() {
        showProgressDialog()
        catalogViewModel.catalogLiveData.observe(this, Observer { catalog ->
            Timber.d("my catalogs  $catalog")
            swipe_refresh_layout.setRefreshing(false);

            val adapter = CatalogAdapter(catalog.items) {
                val intent = getNewIntent(this, it)
                startActivity(intent)
            }
            recyclerView.adapter = adapter

            hideProgressDialog()
        })
    }

    private fun setupSwipeRefresh() {
        swipe_refresh_layout.setOnRefreshListener(this);
        swipe_refresh_layout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipe_refresh_layout.post {
            fetchCatalog(true)

        }
    }

    override fun onRefresh() {
        fetchCatalog(true)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Timber.d("onConfigurationChanged landscape")
        } else {
            Timber.d("onConfigurationChanged portrait")

        }
    }
}
