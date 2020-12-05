package com.hemanth.newsapp.views.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hemanth.newsapp.R
import com.hemanth.newsapp.model.Articles
import kotlinx.android.synthetic.main.articles_item.view.*

class ArticlesAdapter(private val context: Context,
                      private val articlesList: ArrayList<Articles>,
                      private val viewHeight: Int
                      ): RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder>() {

    inner class ArticlesHolder(v: View): RecyclerView.ViewHolder(v) {
        val txtTitle: TextView = v.txtTitle
        val txtDescription: TextView = v.txtDescription
        val txtAuthor: TextView = v.txtAuthor
        val imgArticle: ImageView = v.imgArticle
        val layoutArticle: CardView = v.layoutArticle
        init {
            imgArticle.layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, viewHeight)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesHolder {
        return ArticlesHolder(LayoutInflater.from(context)
            .inflate(R.layout.articles_item,
                null))
    }

    override fun onBindViewHolder(holder: ArticlesHolder, position: Int) {
        holder.txtTitle.text = articlesList[position].title
        holder.txtDescription.text = articlesList[position].description
        holder.txtAuthor.text = articlesList[position].author

        Glide.with(context)
            .load(articlesList[position].urlToImage)
            .into(holder.imgArticle)

        holder.layoutArticle.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(articlesList[position].url)))
        }

    }

    override fun getItemCount(): Int = articlesList.size
}