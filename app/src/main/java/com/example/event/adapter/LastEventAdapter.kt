package com.example.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.event.R
import com.example.event.R.layout.event_list_terbaru
import com.example.event.model.Event
import kotlinx.android.extensions.LayoutContainer

class LastEventAdapter(private val context: Context, private val events: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<LastEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val viewHolder = LayoutInflater.from(context).inflate(event_list_terbaru, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var name : TextView = itemView.findViewById(R.id.event_textView_name)
        private var image : ImageView = itemView.findViewById(R.id.event_imageView)
        private var location : TextView = itemView.findViewById(R.id.event_textView_location)
        private var tanggal : TextView = itemView.findViewById(R.id.event_textView_date)
        private var bulan : TextView = itemView.findViewById(R.id.event_textView_month)

        fun bindItem(events: Event, listener: (Event) -> Unit){
            var tgl = events.date?.substring(0,2)
            var bln = events.date?.substring(3,6)

            name.text = events.name
            Glide.with(itemView.context).load(events.image).into(image)
            location.text = events.location
            tanggal.text = tgl
            bulan.text = bln
            itemView.setOnClickListener { listener(events) }
        }
    }
}