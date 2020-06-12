package com.uabc.edu.mx.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUsers()
    }

    // function for network call
    fun getUsers() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://api.github.com/search/users?q=xhendor"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->

                    var strResp = response.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                    var str_user: String = ""
                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        str_user = str_user + "\n" + jsonInner.get("login")

                    }
                    text!!.text = "response : $str_user "
                },
                Response.ErrorListener { text!!.text = "That didn't work!" })
        queue.add(stringReq)
    }
}
