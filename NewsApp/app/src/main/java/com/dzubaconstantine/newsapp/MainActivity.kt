package com.dzubaconstantine.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(this)
        OkHttpClient().newCall(
            Request.Builder().url(
                "http://newsapi.org/v2/top-headlines?" +
                        "country=ru&" +
                        "apiKey=7470f1b0c9af44be9a038c0066714c99"
            ).build()
        )
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    val articles = JSONObject(response.body()!!.string()).getJSONArray("articles")
                    for (i in 0 until articles.length()) {
                        val article = articles.getJSONObject(i)
                        (recyclerView.adapter as NewsAdapter).articles.add(
                            Article(
                                article.getString("title"),
                                article.getString("description"),
                                article.getString("urlToImage"),
                                article.getString("url")
                            )
                        )
                        runOnUiThread {
                            (recyclerView.adapter as NewsAdapter).notifyDataSetChanged()
                        }
                    }
                }
            })
    }
}