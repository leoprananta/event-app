package com.example.event.views.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.R.array.*
import com.example.event.adapter.LastEventAdapter
import com.example.event.model.DB
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
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
class HomeFragment : Fragment() {

    private var events : MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        val musik = fragmentView.findViewById<LinearLayout>(R.id.home_musik)

        musik.setOnClickListener {
            toast("Kategori Musik")
        }

        showTopSubject()
        initData()

        recyclerView = fragmentView.findViewById(R.id.home_recyclerView_event)
        recyclerView.layoutManager = LinearLayoutManager(fragmentView.context, LinearLayout.HORIZONTAL, false)
        recyclerView.adapter = LastEventAdapter(fragmentView.context, events) {
            startActivity<DetailActivity>("data" to it)
        }

        return fragmentView
    }

    private fun showTopSubject() {
        context?.DB?.use {
            val result = select(Event.TOP_EVENT)

            val top = result.parseList(classParser<Event>())

            events.addAll(top)
        }
    }

    private fun initData(){

        val name = arrayListOf("Konser Musik", "Seminar Gizi", "Vapers Meetup", "Papercom Meetup", "Pengajian")
        val image = arrayListOf("https://www.patinews.com/wp-content/uploads/2019/10/Saksikan-20-November-di-Pati.jpg","https://1.bp.blogspot.com/-2TY7VuDqfG0/XTQ27kPQ46I/AAAAAAAABkY/h8ltWHZ52-IaotQTNY8qCBA_FR60IeI4gCLcBGAs/s1600/uin%2Bsemarnag%2B321.jpg","https://scontent-yyz1-1.cdninstagram.com/vp/375e8dacfc342878c2cc3669475d3c4c/5DD8FA1A/t51.2885-15/sh0.08/e35/s640x640/67111887_129788148256078_4679386709361797111_n.jpg?_nc_ht=scontent-yyz1-1.cdninstagram.com", "https://www.dicoding.com/images/original/event/papercom_meetup_let_s_start_the_journey_mc_260318191634.png", "https://1.bp.blogspot.com/-C3xgEsUOwSQ/Wecz5hk317I/AAAAAAAAMVQ/jMRi5bo3r58gf7-L78Sn7SkWUaF5Lu84ACLcBGAs/s1600/Pengajian%2BHUT%2B70%2BPR%2BSUkun%2BGrup.jpg")
        val location = arrayListOf("Pekalongan", "Ngaliyan", "Kudus", "Kudus", "Kudus")
        val htm = arrayListOf("80k", "75k", "Free", "Free", "Free")
        val kuota = arrayListOf("Unlimited", "70 Orang", "40 Orang", "20 Orang", "Unlimited")
        val date = arrayListOf("31 Oktober 2019", "11 November 2019", "19 Desember 2019", "02 November 2019", "31 Desember 2019")
        val by = arrayListOf("Komunitas Ambyar Pekalongan", "HMJ Gizi UIN Walisongo", "Kudus Vapers Club", "Papercom Indonesia", "Sukun Group")
        val desc = arrayListOf("Event by Komunitas Ambyar Pekalongan", "Event by HMJ Gizi UIN Walisongo", "Event by Kudus Vapers Club", "Event by Papercom Indonesia", "Event by Sukun Group")

//        val name = resources.getStringArray(event_name)
//        val image = resources.getStringArray(event_poster)
//        val location = resources.getStringArray(event_location)
//        val htm = resources.getStringArray(event_htm)
//        val kuota = resources.getStringArray(event_kuota)
//        val date = resources.getStringArray(event_date)
//        val by = resources.getStringArray(event_by)
//        val desc = resources.getStringArray(event_desc)

        for(i in name.indices ){
            events.add(
                Event(name[i],
                image[i],
                    location[i],
                    htm[i],
                    kuota[i],
                    date[i],
                    by[i],
                    desc[i]))
        }
    }
}
