package com.isavanzados.retrofitrxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.isavanzados.retrofitrxjava.Adapter.PostAdapter
import com.isavanzados.retrofitrxjava.Model.Post
import com.isavanzados.retrofitrxjava.RetrofitAPI.API
import com.isavanzados.retrofitrxjava.RetrofitAPI.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.github.ybq.android.spinkit.style.Wave
import android.os.Handler
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonAPI: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wave = Wave()
        wave.color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        spin_spin.setIndeterminateDrawable(wave)

        val retrofit = RetrofitClient.instance
        jsonAPI = retrofit.create(API::class.java)

        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)

        fetchData()

    }

    private fun fetchData(){

        val observable: Observable<List<Post>> = jsonAPI.getPosts()

        /*observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posts->displayData(posts) }*/

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Post>>{
                override fun onComplete() {
                    Handler().postDelayed({
                        spin_spin.visibility = View.GONE
                    }, 500)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Post>) {
                    Handler().postDelayed({
                        displayData(t)
                    }, 500)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

    }

    private fun displayData(posts: List<Post>?){

            val adapter = PostAdapter(this,posts!!)
            recycler_posts.adapter = adapter

        /*Handler().postDelayed({
            val adapter = PostAdapter(this,posts!!)
            recycler_posts.adapter = adapter
            spin_spin.visibility = View.GONE
        }, 500)*/

    }

}
