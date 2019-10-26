package com.example.event.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Event.db", null, 1) {

    companion object {
        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper{
            if (instance == null){
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Event.FAVORITE_EVENT, true,
            Event.NAME to TEXT,
            Event.POSTER to TEXT,
            Event.LOCATION to TEXT,
            Event.HTM to TEXT,
            Event.KUOTA to TEXT,
            Event.DATE to TEXT,
            Event.BY to TEXT,
            Event.DESC to TEXT
            )

        db.createTable(Event.TERDAFTAR_EVENT, true,
            Event.NAME to TEXT,
            Event.POSTER to TEXT,
            Event.LOCATION to TEXT,
            Event.HTM to TEXT,
            Event.KUOTA to TEXT,
            Event.DATE to TEXT,
            Event.BY to TEXT,
            Event.DESC to TEXT
        )

        db.createTable(Event.TAMBAH_EVENT, true,
            Event.NAME to TEXT,
            Event.POSTER to TEXT,
            Event.LOCATION to TEXT,
            Event.HTM to TEXT,
            Event.KUOTA to TEXT,
            Event.DATE to TEXT,
            Event.BY to TEXT,
            Event.DESC to TEXT)

        db.createTable(Event.TOP_EVENT, true,
            Event.NAME to TEXT,
            Event.POSTER to TEXT,
            Event.LOCATION to TEXT,
            Event.HTM to TEXT,
            Event.KUOTA to TEXT,
            Event.DATE to TEXT,
            Event.BY to TEXT,
            Event.DESC to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(Event.FAVORITE_EVENT, true)
        db.dropTable(Event.TERDAFTAR_EVENT, true)
        db.dropTable(Event.TAMBAH_EVENT, true)
        db.dropTable(Event.TOP_EVENT, true)
    }
}