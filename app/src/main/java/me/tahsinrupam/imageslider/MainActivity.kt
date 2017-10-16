package me.tahsinrupam.imageslider

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import me.tahsinrupam.imageslider.adapters.ImageAdapter
import me.tahsinrupam.imageslider.models.PhotoSlide
import org.json.JSONArray
import java.util.*

class MainActivity : AppCompatActivity() {

    private val url = "https://api.unsplash.com/photos/?client_id=231e96aa0adac9fe977c7e025674c14548b0327f187c38b1c1fd616277d1b946"
    private var listUrl : MutableList<PhotoSlide>? = null
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUrl = mutableListOf()
        loadNetworkData()
    }

    private fun setUpAutoSlide() {
        val handler = Handler()
        val update = Runnable {
            if (currentPage == listUrl?.size) {
                currentPage = 0
            }
            Log.d("CurP->", currentPage.toString())
            viewPager.setCurrentItem(currentPage++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 2500, 2500)
    }


    fun loadNetworkData() {
        val requestQueue = Volley.newRequestQueue(this) as RequestQueue
        val request = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener<JSONArray> { response ->
                    progressBar.visibility = View.GONE
                    val count: Int = response.length()
                    listUrl?.clear()
                    repeat(count) { i ->
                    val imageUrl = response.getJSONObject(i).getJSONObject("urls").getString("regular")
                        val name = response.getJSONObject(i).getJSONObject("user").getString("name")
                        val location = response.getJSONObject(i).getJSONObject("user").getString("location")
                        val bio = response.getJSONObject(i).getJSONObject("user").getString("bio")
                        listUrl?.add(PhotoSlide(imageUrl, name, location, bio ))
                        val imageAdapter = ImageAdapter(this, listUrl)
                        viewPager.adapter = imageAdapter
                        tabLayout.setupWithViewPager(viewPager)

                    }
                    setUpAutoSlide()
                },
                Response.ErrorListener {
                    Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                }
        )
        requestQueue.add(request)


    }

}
