package com.example.testtorch

import android.app.Activity
import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Toast


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.flashlightToggleButton).setOnClickListener {
            cameraManager.cameraIdList.forEach { cameraId ->
                val cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId)
                val flashAvailable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                if (flashAvailable)
                    cameraManager.setTorchMode(cameraId, true)
                else
                    Toast.makeText(this@MainActivity, "Flash not available", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
