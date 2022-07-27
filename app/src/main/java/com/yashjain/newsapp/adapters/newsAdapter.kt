package com.yashjain.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashjain.newsapp.POJO.Article
import com.yashjain.newsapp.R

class newsAdapter : RecyclerView.Adapter<newsAdapter.ViewHolder>() {



    private val diffCallBack = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

    val differ =  AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.articleImage)
             holder.title.text = article.title
             holder.source.text=article.source.name
             holder.description.text = article.description
             holder.publishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener:((Article)->Unit)?=null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
       return  differ.currentList.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val articleImage: ImageView = itemView.findViewById(R.id.ArticleImage)
        val title: TextView = itemView.findViewById(R.id.Title)
        val description: TextView = itemView.findViewById(R.id.description)
        val source: TextView = itemView.findViewById(R.id.Source)
        val publishedAt: TextView = itemView.findViewById(R.id.publishedAt)
    }
}