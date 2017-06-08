package com.example.testtorch

import android.app.Activity
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.flashlightToggleButton).setOnClickListener {
            cameraManager.cameraIdList.forEach { cameraId -> cameraManager.setTorchMode(cameraId, true); }
        }
    }
}
