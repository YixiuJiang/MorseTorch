package com.charliejiang.morsetorch

import android.content.Context
import android.hardware.camera2.CameraManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this@MainActivity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.flashlightToggleButton).setOnClickListener {
            val cameraManage =  context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            cameraManage.cameraIdList.forEach { cameraId-> cameraManage.setTorchMode(cameraId, true); }

        }
    }


}
