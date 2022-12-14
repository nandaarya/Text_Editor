package com.dicoding.text_editor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListTextEditorAdapter (private val listTextEditor: ArrayList<TextEditor>) :
    RecyclerView.Adapter<ListTextEditorAdapter.ListViewHolder> () {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: TextEditor)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_text_editor, parent, false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val textEditor = listTextEditor[position]

        Glide.with(holder.itemView.context)
            .load(textEditor.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = textEditor.name
        holder.tvDetail.text = textEditor.detail

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTextEditor[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listTextEditor.size
    }
}