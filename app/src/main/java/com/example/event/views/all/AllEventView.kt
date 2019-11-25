package com.example.event.views.all

import com.example.event.model.Events

interface AllEventContract {
    interface View{
        fun setDataEvent(allEvent: List<Events>)
    }
    interface Presenter{
        fun getEvent()
    }
}