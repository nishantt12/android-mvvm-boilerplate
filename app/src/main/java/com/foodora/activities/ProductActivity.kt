package com.foodora.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.foodora.MyApplication
import com.foodora.R
import com.foodora.model.catalog.Product
import com.foodora.util.ViewModelUtil
import com.foodora.viewModels.ProductViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.*
import timber.log.Timber
import javax.inject.Inject

fun getNewIntent(context: Context, product: Product?): Intent {
    return Intent(context, ProductActivity::class.java).apply {
        putExtra("image", product?.image)
    }

}

class ProductActivity : AppCompatActivity() {

    @Inject
    lateinit var productViewModel: ProductViewModel
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        init()
        setObservers()
        if (intent != null && intent.getStringExtra("image") != null) {
            Glide.with(this)
                    .load(intent.getStringExtra("image"))
                    .into(img_product)
        } else if (intent.data != null) {
            Timber.d("${intent.data}")
            val data = intent.data.toString()
            val productId = data.split("/")
            productViewModel.getProduct(productId[productId.lastIndex])

        }
    }


    private fun init() {
        MyApplication.getComponent().injectProductActivity(this)
        val viewModelFactory = ViewModelUtil.createFor(productViewModel)
        productViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                ProductViewModel::class.java)


    }

    private fun setObservers() {

        productViewModel.product.observe(this, Observer { product ->
            Timber.d("my catalogs  $product")
            Glide.with(this)
                    .load(product.image)
                    .into(img_product)
        })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


}