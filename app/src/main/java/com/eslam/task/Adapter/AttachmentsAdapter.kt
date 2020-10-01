package com.eslam.task.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.eslam.task.Model.UnitModel.Attachments
import com.eslam.task.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_attachment.view.*

class AttachmentsAdapter(
    private var imgList: List<Attachments>) : RecyclerView.Adapter<AttachmentsAdapter.AttachmentViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AttachmentsAdapter.AttachmentViewHolder {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_attachment, viewGroup, false)
        return AttachmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        Picasso.get()
            .load(imgList.get(position).image)
            .fit()
            .into(holder.attachmentImage)
    }

    override fun getItemCount(): Int = imgList.size

    class AttachmentViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val attachmentImage: ImageView by lazy { itemView.attachmentImage }

    }
}

