package com.example.event.model

val android.content.Context.DB: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)