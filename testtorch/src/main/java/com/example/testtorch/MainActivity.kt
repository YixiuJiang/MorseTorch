package com.example.testtorch

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Toast


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        var lightQuantity = 0f
        var accurateQuantity = 0
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

        val listener: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                lightQuantity = event.values[0]
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                accurateQuantity = p1
            }

        }

        sensorManager.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_UI);
    }
}
