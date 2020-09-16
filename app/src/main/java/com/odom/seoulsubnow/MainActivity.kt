package com.odom.seoulsubnow

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.INTERNET
    )

    val REQUEST_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 인터넷 연결 안되어있으면
        // 알림 후 종료
        if(!checkInternetConnection()){
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("인터넷 연결 확인 ")
                .setPositiveButton("확인") { _, _ ->
                    finish()
                    exitProcess(0)
                }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        setContentView(R.layout.activity_main)

        //권한 요청
        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)

        // 설정 화면
        settingButton.setOnClickListener {
            startActivity(Intent(this, settingActivity::class.java))
        }
    }

    // 인터넷 연결 확인
    fun checkInternetConnection() : Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null)
            return true

        return false
    }


    // 권한 있나 체크
    fun hasPermissions() : Boolean{
        for(permisison in PERMISSIONS)
            if(ActivityCompat.checkSelfPermission(this, permisison) != PackageManager.PERMISSION_GRANTED)
                return false

        return true
    }


    // 맵뷰의 라이프사이클 함수 호출
    override fun onResume() {
        super.onResume()

    }
    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    // 서울 열린 데이터 광장 발급 키
    val API_KEY = "62576e4b746a6968313237496f745165"

    // JSONArray에서 원소의 속성으로 검색
    fun JSONArray.findByChildProperty(propertyName : String, value : String) : JSONObject?{
        for(i in 0 until length()){
            val obj = getJSONObject(i)
            if(value == obj.getString(propertyName))
                return obj
        }
        return null
    }

    // 앱이 활성화될때마다 데이터를 읽어옴
    override fun onStart() {
        super.onStart()

        // 인터넷 연결이 있을시에만 AsyncTask 실행★
        if(checkInternetConnection()){
            Log.d("TAG", "task EXECUTE")
        }
    }

    // 앱이 비활성화될때마다 백그라운드 작업취소
    override fun onStop() {
        super.onStop()
    }

}