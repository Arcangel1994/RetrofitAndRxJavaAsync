package com.isavanzados.retrofitrxjava.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.isavanzados.retrofitrxjava.Model.Post
import com.isavanzados.retrofitrxjava.R
import java.lang.StringBuilder

class PostAdapter(internal var context: Context, internal var postList: List<Post>): RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {

        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.post_layout, p0, false)

        return PostViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return postList.size

    }

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {

        p0.author.text = "${p1+1}"
        p0.title.text = postList[p1].url
        p0.content.text = postList[p1].title

    }


}