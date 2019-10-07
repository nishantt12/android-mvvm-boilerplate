package com.foodora.adapter

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.foodora.R
import com.foodora.model.catalog.Product
import kotlinx.android.synthetic.main.item_catalog.view.*
import timber.log.Timber

class CatalogAdapter(private val items: List<Product>, private val listener: ((Product?) -> Unit)) : RecyclerView.Adapter<CatalogAdapter.CatalogHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catalog, parent, false)
        return CatalogHolder(view)
    }


    override fun onBindViewHolder(holder: CatalogHolder, position: Int) {
        val product = items[position]
        holder.bindCatalog(product, listener)

    }

    class CatalogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCatalog(product: Product?, listener: ((Product?) -> Unit)) {

            itemView.setOnClickListener {
                listener.invoke(product)
            }
            Glide.with(itemView)
                    .asBitmap()
                    .load(product?.image)
                    .into(object : SimpleTarget<Bitmap>() {

                        override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                            Palette.Builder(resource).generate { it?.let { palette ->
                                val dominantColor = palette.getDominantColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
                               Timber.d("dominantColor: $dominantColor")
                                itemView.main_layout.setBackgroundColor(dominantColor)

                            } }

                            itemView.img_catalog.setImageBitmap(resource)
                        }


                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })

            itemView.tv_name.text = product?.name
            Timber.d("originalPrice: 0")

            if (product?.price?.original?.compareTo(product?.price?.current) == 0) {
                Timber.d("originalPrice: 1")
                itemView.tv_price_original.visibility = View.GONE
            } else {
                itemView.tv_price_original.visibility = View.VISIBLE
                val originalPrice = "${product?.price?.original} ${product?.price?.currency}"
                Timber.d("originalPrice: $originalPrice")
                itemView.tv_price_original.paintFlags = itemView.tv_price_original.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

//                itemView.tv_price_original.setPaintFlags(itemView.tv_price_original.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG.inv())
                itemView.tv_price_original.text = originalPrice
            }
            itemView.tv_price.text = "${product?.price?.current} ${product?.price?.currency}"
            itemView.tv_brand.text = product?.name

        }


    }
}