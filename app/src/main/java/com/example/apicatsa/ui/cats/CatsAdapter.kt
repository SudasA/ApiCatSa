package com.example.apicatsa.ui.cats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.apicatsa.R
import com.example.apicatsa.data.entity.Cat
import kotlinx.android.synthetic.main.cat_item_view.view.*

class CatsAdapter(
    private val glide: RequestManager,
    private val cats: List<Cat>,
    private val onClickAction: (Cat) -> Unit,
    private val onLongClickAction: (Cat) -> Boolean
) : RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CatsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cat_item_view, parent, false))

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) =
        holder.bind(glide, cats[position], onClickAction, onLongClickAction)

    override fun getItemCount(): Int =
        cats.size

    class CatsViewHolder(catItemView: View) : RecyclerView.ViewHolder(catItemView) {
        fun bind(glide: RequestManager, cat: Cat, onClickAction: (Cat) -> Unit, onLongClickAction: (Cat) -> Boolean) {
            itemView.setOnClickListener { onClickAction(cat) }
            itemView.setOnLongClickListener { onLongClickAction(cat) }
            glide.load(cat.imageUrl).centerCrop().into(itemView.catImageView)
        }
    }
}