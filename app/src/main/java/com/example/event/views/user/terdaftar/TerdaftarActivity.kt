package com.example.event.views.user.terdaftar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.adapter.AllEventAdapter
import com.example.event.model.DB
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class TerdaftarActivity : AppCompatActivity() {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terdaftar)

        recyclerView = findViewById(R.id.terdaftar_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = AllEventAdapter(this, events){
            startActivity<DetailActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        showTerdaftarSubject()


        //var nilai = adapter.itemCount * 10

    }

    private fun showTerdaftarSubject() {
        this.DB.use {
            val result = select(Event.TERDAFTAR_EVENT)

            val terdaftar = result.parseList(classParser<Event>())

            events.addAll(terdaftar)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        events.clear()
        showTerdaftarSubject()
        super.onResume()
    }
}
