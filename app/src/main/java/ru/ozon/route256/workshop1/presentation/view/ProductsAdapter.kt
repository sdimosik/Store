package ru.ozon.route256.workshop1.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI


class ProductsAdapter(
    val glide: RequestManager,
    val onClick: (ProductInListUI) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ProductInListUI> =

            object : DiffUtil.ItemCallback<ProductInListUI>() {
                override fun areItemsTheSame(
                    o1: ProductInListUI,
                    o2: ProductInListUI
                ): Boolean {
                    return o1.guid == o2.guid
                }

                override fun areContentsTheSame(
                    o1: ProductInListUI,
                    o2: ProductInListUI
                ): Boolean {
                    return o1 == o2
                }
            }
    }

    private val differ: AsyncListDiffer<ProductInListUI> =
        AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(products: List<ProductInListUI>) {
        differ.submitList(products)
    }

    fun getList(): List<ProductInListUI>{
        return differ.currentList
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.productIV)
        private val name: TextView = itemView.findViewById(R.id.nameTV)
        private val price: TextView = itemView.findViewById(R.id.priceTV)
        private val rating: AppCompatRatingBar = itemView.findViewById(R.id.ratingView)
        private val countView: TextView = itemView.findViewById(R.id.countViewTV)

        private lateinit var item: ProductInListUI

        init {
            itemView.setOnClickListener {
                onClick(item)
            }
        }

        fun bind(item: ProductInListUI) {
            this.item = item

            glide.load(item.image)
                .transition(withCrossFade())
                .into(photo)

            name.text = item.name
            price.text = item.price
            rating.rating = item.rating.toFloat()
            countView.text = item.countView.toString()
        }
    }
}