package com.kuldeep.posts.list

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.deliverylist.R
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*

class PostListAdapter(private val picasso: Picasso)
    : ListAdapter<DeliveriesData, PostListAdapter.ListViewHolder>(PostWithUserDC()) {

    var interaction: Interaction? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ) = ListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false), interaction)

    override fun onBindViewHolder(
            holder: ListViewHolder,
            position: Int
    ) = holder.bind(getItem(position)!!, picasso)

    fun swapData(data: List<DeliveriesData>) {
        submitList(data.toMutableList())
    }

    inner class ListViewHolder(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            if (clicked != null) {
                interaction?.postClicked(clicked, itemView.tvTitle, itemView.tvBody, itemView.ivAvatar)
            }
        }

        fun bind(item: DeliveriesData, picasso: Picasso) = with(itemView) {
            tvTitle.text = item.description
            tvBody.text = item.description
            picasso.load(item.imageUrl)
                    .into(itemView.ivAvatar)

            //SharedItem transition
            ViewCompat.setTransitionName(tvTitle, item.description)
            ViewCompat.setTransitionName(tvBody, item.description)
            ViewCompat.setTransitionName(ivAvatar, item.imageUrl)
        }
    }

    interface Interaction {
        fun postClicked(
            post: DeliveriesData,
            tvTitle: TextView,
            tvBody: TextView,
            ivAvatar: ImageView)
    }

    private class PostWithUserDC : DiffUtil.ItemCallback<DeliveriesData>() {
        override fun areItemsTheSame(oldItem: DeliveriesData, newItem: DeliveriesData) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DeliveriesData, newItem: DeliveriesData) = oldItem == newItem
    }
}