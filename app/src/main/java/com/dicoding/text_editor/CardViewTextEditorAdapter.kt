package com.dicoding.text_editor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewTextEditorAdapter(private val listTextEditor: ArrayList<TextEditor>) :
    RecyclerView.Adapter<CardViewTextEditorAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: TextEditor)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_text_editor, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val textEditor = listTextEditor[position]

        Glide.with(holder.itemView.context)
            .load(textEditor.photo)
            .apply(RequestOptions().override(350, 350))
            .into(holder.imgPhoto)

        holder.tvName.text = textEditor.name
        holder.tvDetail.text = textEditor.detail

        holder.btnShare.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Share " + listTextEditor[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Kamu memilih " + listTextEditor[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listTextEditor[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listTextEditor.size
    }
}