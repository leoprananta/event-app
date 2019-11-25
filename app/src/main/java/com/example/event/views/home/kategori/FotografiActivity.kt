package com.example.event.views.home.kategori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.adapter.AllEventAdapter
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import org.jetbrains.anko.startActivity

class FotografiActivity : AppCompatActivity() {

    private var events : MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotografi)

        initData()

        recyclerView = findViewById(R.id.fotografi_recyclerView_event)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = AllEventAdapter(this, events) {
            startActivity<DetailActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        recyclerView.isNestedScrollingEnabled = false
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.kategori_fotografi)
        val image = resources.getStringArray(R.array.poster_kategori_fotografi)
        val location = resources.getStringArray(R.array.location_kategori_fotografi)
        val htm = resources.getStringArray(R.array.htm_kategori_fotografi)
        val kuota = resources.getStringArray(R.array.kuota_kategori_fotografi)
        val date = resources.getStringArray(R.array.date_kategori_fotografi)
        val by = resources.getStringArray(R.array.eventby_kategori_fotografi)
        val desc = resources.getStringArray(R.array.desc_kategori_fotografi)

        for(i in name.indices ){
            events.add(
                Event(name[i],
                    image[i],
                    location[i],
                    htm[i],
                    kuota[i],
                    date[i],
                    by[i],
                    desc[i])
            )
        }
    }
}
