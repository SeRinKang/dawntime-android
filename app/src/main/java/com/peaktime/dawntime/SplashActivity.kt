package com.peaktime.dawntime

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget


class SplashActivity : AppCompatActivity() {

    val MODE_CHECK = "check"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var splashGIF = findViewById<ImageView>(R.id.splash_image)
        var gifImage = GlideDrawableImageViewTarget(splashGIF)
        Glide.with(this).load(R.drawable.dawntime_splash).into(gifImage)

        var mNetworkState = getNetworkInfo()//인터넷 연결 검사
        if (mNetworkState != null && mNetworkState.isConnected) {
            if (mNetworkState.type == ConnectivityManager.TYPE_WIFI) {
//                Toast.makeText(this,"와이파이",Toast.LENGTH_LONG).show()
            } else if (mNetworkState.type == ConnectivityManager.TYPE_MOBILE) {
//                Toast.makeText(this,"모바일",Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "네트워크 연결을 확인해주세요", Toast.LENGTH_LONG).show()
            moveTaskToBack(true)
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }


        val handler = Handler()
        handler.postDelayed({

            if (SharedPreferInstance.getInstance(this).getPreferBoolean("LOCK")!!) {
                var intent = Intent(applicationContext, LockActivity::class.java)
                intent.putExtra("MODE", MODE_CHECK)
                startActivity(intent)
            } else {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            finish()
        }, 2600)

    }

    fun getNetworkInfo(): NetworkInfo {
        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo
    }

}
