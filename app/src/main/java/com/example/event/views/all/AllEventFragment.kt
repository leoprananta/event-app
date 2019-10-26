package com.example.event.views.all


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.example.event.adapter.AllEventAdapter
import com.example.event.model.DB
import com.example.event.model.Event
import com.example.event.views.detail.DetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CityFragment : Fragment() {

    private var events : MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_all_event, container, false)

        initData()

        recyclerView = fragmentView.findViewById(R.id.all_recyclerView_event)
        recyclerView.layoutManager = GridLayoutManager(fragmentView.context, 2)
        adapter = AllEventAdapter(fragmentView.context, events) {
            startActivity<DetailActivity>("data" to it)
        }
        recyclerView.adapter = adapter

        showTerdaftarSubject()

        recyclerView.isNestedScrollingEnabled = false
        return fragmentView
    }

    private fun showTerdaftarSubject() {
        context?.DB?.use {
            val result = select(Event.TAMBAH_EVENT)

            val terdaftar = result.parseList(classParser<Event>())

            events.addAll(terdaftar)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.event_name)
        val image = resources.getStringArray(R.array.event_poster)
        val location = resources.getStringArray(R.array.event_location)
        val htm = resources.getStringArray(R.array.event_htm)
        val kuota = resources.getStringArray(R.array.event_kuota)
        val date = resources.getStringArray(R.array.event_date)
        val by = resources.getStringArray(R.array.event_by)
        val desc = resources.getStringArray(R.array.event_desc)

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
