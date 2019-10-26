package com.example.event.views.user.eventsaya

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.adapter.AllEventAdapter
import com.example.event.model.DB
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import com.example.event.views.detail.DetailEventSayaActivity
import com.example.event.views.user.tambahevent.TambahEventActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class EventSayaActivity : AppCompatActivity() {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_saya)

        recyclerView = findViewById(R.id.eventSaya_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = AllEventAdapter(this, events) {
            startActivity<DetailEventSayaActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        showFavoriteSubject()

        val btnTambah = findViewById<FloatingActionButton>(R.id.btn_tambah)


        btnTambah.setOnClickListener {
            startActivity<TambahEventActivity>()
        }
    }

    private fun showFavoriteSubject() {
        this.DB.use {
            val result = select(Event.TAMBAH_EVENT)

            val tambah = result.parseList(classParser<Event>())

            events.addAll(tambah)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        events.clear()
        showFavoriteSubject()
        super.onResume()
    }
}
