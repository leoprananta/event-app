package com.example.event.views.user.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class FavoriteActivity : AppCompatActivity() {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recyclerView = findViewById(R.id.favorite_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = AllEventAdapter(this, events) {
            startActivity<DetailActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        showFavoriteSubject()

    }

    private fun showFavoriteSubject() {
        this.DB.use {
            val result = select(Event.FAVORITE_EVENT)

            val terdaftar = result.parseList(classParser<Event>())

            events.addAll(terdaftar)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        events.clear()
        showFavoriteSubject()
        super.onResume()
    }
}
