package com.isavanzados.retrofitrxjava.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.post_layout.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val author = itemView.txt_author
    val title = itemView.txt_title
    val content = itemView.txt_content

}