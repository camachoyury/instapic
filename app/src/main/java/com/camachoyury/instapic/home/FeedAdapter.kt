package com.camachoyury.instapic.home

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camachoyury.instapic.databinding.FeedItemBinding
import com.camachoyury.instapic.model.Post



class FeedAdapter(private val posts: List<Post>): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    val placeholder = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRSQq-SFfIbpUUmnCXw8AtH0I4XbrWbcAvr1A&usqp=CAU"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)

    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)

    }

    inner class ViewHolder(private val itemBinding : FeedItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(post:Post){
            itemBinding.postImage.loadImage(post.image!!)
            itemBinding.usernameText.text =  post.username
            itemBinding.captionText.setCaptionText(post.username, post.caption)

        }
    }

    fun ImageView.loadImage(image: String) {
        GlideApp.with(this).load(image).centerCrop().into(this)
    }

    private fun TextView.setCaptionText(username: String, caption: String) {
        val usernameSpannable = SpannableString(username)
        usernameSpannable.setSpan(
            StyleSpan(Typeface.BOLD), 0, usernameSpannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        usernameSpannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
//                widget.context.showToast("Username is clicked")
            }
            override fun updateDrawState(ds: TextPaint) {}
        }, 0, usernameSpannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = SpannableStringBuilder().append(usernameSpannable).append(" ")
            .append(caption)
        movementMethod = LinkMovementMethod.getInstance()
    }

}