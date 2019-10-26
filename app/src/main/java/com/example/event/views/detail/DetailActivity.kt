package com.example.event.views.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.event.R
import com.example.event.model.DB
import com.example.event.model.Event
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var isTerdaftar: Boolean = false
    private lateinit var eventName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val event = intent.getParcelableExtra<Event>("data")
        mainLayout(event)

        eventName = "${event.name}"

        favoriteState()

        terdaftarState()

        setTerdaftar()

        if(isTerdaftar){
            longToast("Kamu Sudah Terdaftar")
        }

        btn_daftar.setOnClickListener {
            if (isTerdaftar) {
                removeFromTerdaftar()
            } else {
                addToTerdaftar()
            }
            isTerdaftar = !isTerdaftar
            setTerdaftar()
        }

    }

    private fun mainLayout(event: Event) {
        val name = findViewById<TextView>(R.id.detail_textView_eventName)
        val poster = findViewById<ImageView>(R.id.detail_imageView_poster)
        val location = findViewById<TextView>(R.id.detail_textView_eventLocation)
        val kuota = findViewById<TextView>(R.id.detail_textView_eventKuota)
        val htm = findViewById<TextView>(R.id.detail_textView_eventHtm)
        val tanggal = findViewById<TextView>(R.id.detail_textView_eventDate)
        val desc = findViewById<TextView>(R.id.detail_textView_eventDesc)
        val eventBy = findViewById<TextView>(R.id.detail_textView_eventBy)

        name.text = event.name
        Glide.with(this).load(event.image).into(poster)
        location.text = event.location
        kuota.text = event.kuota
        htm.text = event.htm
        tanggal.text = event.date
        desc.text = event.desc
        eventBy.text = event.by
    }

    private fun favoriteState() {
        DB.use {
            val result = select(Event.FAVORITE_EVENT)
                .whereArgs(Event.NAME + "={name}", "name" to eventName)

            val favorite = result.parseList(classParser<Event>())

            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun addToFavorite() {
        val event = intent.getParcelableExtra<Event>("data")
        try {
            DB.use {
                insert(
                    Event.FAVORITE_EVENT,
                    Event.NAME to event.name,
                    Event.POSTER to event.image,
                    Event.LOCATION to event.location,
                    Event.HTM to event.htm,
                    Event.KUOTA to event.kuota,
                    Event.DATE to event.date,
                    Event.BY to event.by,
                    Event.DESC to event.desc
                )
                toast("Added to Favorite")
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    private fun removeFromFavorite() {
        try {
            DB.use {
                delete(
                    Event.FAVORITE_EVENT,
                    Event.NAME + "={name}", "name" to eventName
                )
                toast("Removed from Favorite")
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.second_menu, menu)

        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.addToFav -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.added_to_fav)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.add_to_fav)
        }
    }



    private fun terdaftarState() {
        DB.use {
            val result = select(Event.TERDAFTAR_EVENT)
                .whereArgs(Event.NAME + "={name}", "name" to eventName)

            val terdaftar = result.parseList(classParser<Event>())

            if (!terdaftar.isEmpty()) {
                isTerdaftar = true
            }
        }
    }

    private fun addToTerdaftar() {
        val event = intent.getParcelableExtra<Event>("data")
        try {
            DB.use {
                insert(
                    Event.TERDAFTAR_EVENT,
                    Event.NAME to event.name,
                    Event.POSTER to event.image,
                    Event.LOCATION to event.location,
                    Event.HTM to event.htm,
                    Event.KUOTA to event.kuota,
                    Event.DATE to event.date,
                    Event.BY to event.by,
                    Event.DESC to event.desc
                )
                toast("Berhasil Mendaftar")
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    private fun removeFromTerdaftar() {
        try {
            DB.use {
                delete(
                    Event.TERDAFTAR_EVENT,
                    Event.NAME + "={name}", "name" to eventName
                )
                toast("Batal Daftar")
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    private fun setTerdaftar() {
        if (isTerdaftar) {
            val posisi: String? = "BATAL DAFTAR"
            btn_daftar.text = posisi
        } else {
            val posisi: String? = "DAFTAR"
            btn_daftar.text = posisi
        }
    }
}
