package dog.snow.androidrecruittest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.db.Item
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * author marcinm on 2019-05-15.
 */
class ItemsRecyclerViewAdapter :
    RecyclerView.Adapter<ItemsRecyclerViewAdapter.OrderViewHolder>() {

    private var items = emptyList<Item>()

    fun setData(data: List<Item>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            itemView.textview_item_name.text = item.name
            itemView.textview_item_description.text = item.description
            Glide.with(itemView).load(item.icon).into(itemView.imageview_item_icon)
        }
    }
}