package com.example.event.views.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.event.R
import com.example.event.adapter.AllEventAdapter
import com.example.event.model.DB
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import com.example.event.views.user.eventsaya.EventSayaActivity
import com.example.event.views.user.favorite.FavoriteActivity
import com.example.event.views.user.terdaftar.TerdaftarActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_user, container, false)

        val terdaftar = fragmentView.findViewById<LinearLayout>(R.id.user_terdaftar)
        val eventSaya = fragmentView.findViewById<LinearLayout>(R.id.user_eventSaya)
        val topup      = fragmentView.findViewById<LinearLayout>(R.id.user_topUp)
        val favorite  = fragmentView.findViewById<LinearLayout>(R.id.user_favorite)
        val setting   = fragmentView.findViewById<LinearLayout>(R.id.user_settingApp)
        val info      = fragmentView.findViewById<LinearLayout>(R.id.user_infoApp)
        val bantuan   = fragmentView.findViewById<LinearLayout>(R.id.user_bantuan)
        val cs        = fragmentView.findViewById<LinearLayout>(R.id.user_cs)


        terdaftar.setOnClickListener {
            startActivity<TerdaftarActivity>()
        }

        eventSaya.setOnClickListener {
            startActivity<EventSayaActivity>()
        }

        topup.setOnClickListener {
            toast("Edit Profile")
        }

        favorite.setOnClickListener {
            startActivity<FavoriteActivity>()
        }

        setting.setOnClickListener {
            toast("Setting App")
        }

        info.setOnClickListener {
            toast("Info App beta Kelompok 4 ")
        }

        bantuan.setOnClickListener {
            toast("Bantuan App")
        }

        cs.setOnClickListener {
            toast("Customer Service App")
        }

        return fragmentView
    }

//    private fun showTerdaftarSubject() {
//        context?.DB?.use {
//            val result =  (Event.TERDAFTAR_EVENT)
//
//            val point = result.parseList(classParser<Event>())
//        }
//    }
}
