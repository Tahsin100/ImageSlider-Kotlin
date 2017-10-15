package me.tahsinrupam.imageslider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import me.tahsinrupam.imageslider.adapters.ImageAdapter
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val url = "https://api.unsplash.com/photos/?client_id=231e96aa0adac9fe977c7e025674c14548b0327f187c38b1c1fd616277d1b946"
    private var listUrl : MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listUrl = mutableListOf()
        loadNetworkData()
    }


    fun loadNetworkData() {
        val requestQueue = Volley.newRequestQueue(this) as RequestQueue
        val request = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener<JSONArray> { response ->

                    val count: Int = response.length()
                    listUrl?.clear()
                    repeat(count) { i ->
                    val imageUrl = response.getJSONObject(i).getJSONObject("urls").getString("regular")
                        listUrl?.add(imageUrl)
                        val imageAdapter = ImageAdapter(listUrl)
                        viewPager.adapter = imageAdapter
                        indicator.setViewPager(viewPager)
                }


                },
                Response.ErrorListener {
                    Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                }
        )
        requestQueue.add(request)


    }

}
