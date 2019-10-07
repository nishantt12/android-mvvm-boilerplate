package com.foodora.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodora.R
import com.foodora.model.catalog.Product
import kotlinx.android.synthetic.main.item_catalog.view.*

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
                    .load(product?.image)
                    .into(itemView.img_catalog)

            itemView.tv_name.text = product?.name
            if (product?.price?.original == product?.price?.current) {
                itemView.tv_price_original.visibility = View.GONE
            } else {
                itemView.tv_price_original.visibility = View.VISIBLE
                itemView.tv_price_original.setPaintFlags(itemView.tv_price_original.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
            }
            itemView.tv_price.text = " ${product?.price?.original} ${product?.price?.currency}"
            itemView.tv_brand.text = product?.name

        }


    }
}