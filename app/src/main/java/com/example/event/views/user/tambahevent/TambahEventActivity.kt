package com.example.event.views.user.tambahevent

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.event.R
import org.jetbrains.anko.db.insert
import com.example.event.model.DB
import com.example.event.model.Event
import org.jetbrains.anko.toast

class TambahEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_event)

        val nama = findViewById<EditText>(R.id.tambahEvent_editText_nama)
        val poster = findViewById<EditText>(R.id.tambahEvent_editText_foto)
        val lokasi = findViewById<EditText>(R.id.tambahEvent_editText_lokasi)
        val htm = findViewById<EditText>(R.id.tambahEvent_editText_htm)
        val kuota = findViewById<EditText>(R.id.tambahEvent_editText_kuota)
        val tgl = findViewById<EditText>(R.id.tambahEvent_editText_tgl)
        val by = findViewById<EditText>(R.id.tambahEvent_editText_by)
        val desc = findViewById<EditText>(R.id.tambahEvent_editText_desc)

        val btnTambahEvent = findViewById<Button>(R.id.btn_tambahEvent)

        btnTambahEvent.setOnClickListener {
            try {
                DB.use {
                    insert(
                        Event.TAMBAH_EVENT,
                        Event.NAME to nama.text.toString(),
                        Event.POSTER to poster.text.toString(),
                        Event.LOCATION to lokasi.text.toString(),
                        Event.HTM to htm.text.toString(),
                        Event.KUOTA to kuota.text.toString(),
                        Event.DATE to tgl.text.toString(),
                        Event.BY to by.text.toString(),
                        Event.DESC to desc.text.toString()
                    )
                    toast("Berhasil Tambah Event")
                }
            } catch (e: SQLiteConstraintException) {
                toast("Error ${e.message}")
            }
        }



    }
}
