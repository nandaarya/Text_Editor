package com.dicoding.text_editor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridTextEditorAdapter (private val listTextEditor: ArrayList<TextEditor>) :
    RecyclerView.Adapter<GridTextEditorAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TextEditor)
    }

    class GridViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_text_editor, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listTextEditor[position].photo)
            .apply(RequestOptions().override(350, 350))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTextEditor[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listTextEditor.size
    }
}