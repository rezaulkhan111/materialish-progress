package com.pnikosis.materialishprogress.sample

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pnikosis.materialishprogress.ProgressWheel

class MainActivity : AppCompatActivity() {
    private var progressWheel: ProgressWheel? = null
    private var progressWheelInterpolated: ProgressWheel? = null
    private var progressWheelLinear: ProgressWheel? = null
    private var interpolatedValue: TextView? = null
    private var linearValue: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAbout = findViewById<View>(R.id.button_about) as Button
        buttonAbout.setOnClickListener {
            val dialog = AlertDialog.Builder(this@MainActivity)
                .setTitle(R.string.about)
                .setMessage(R.string.about_text)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            dialog.show()
        }
        progressWheel = findViewById<View>(R.id.progress_wheel) as ProgressWheel
        progressWheelInterpolated = findViewById<View>(R.id.interpolated) as ProgressWheel
        progressWheelLinear = findViewById<View>(R.id.linear) as ProgressWheel
        interpolatedValue = findViewById<View>(R.id.interpolatedValue) as TextView
        linearValue = findViewById<View>(R.id.linearValue) as TextView
        val spinnerOptions = findViewById<View>(R.id.spinner_options) as Spinner
        spinnerOptions.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        progressWheelLinear!!.progress = 0.0f
                        progressWheelInterpolated!!.progress = 0.0f
                        progressWheelInterpolated!!.setCallback(object :
                            ProgressWheel.ProgressCallback {
                            override fun onProgressUpdate(progress: Float) {
                                if (progress == 0f) {
                                    progressWheelInterpolated!!.progress = 1.0f
                                } else if (progress == 1.0f) {
                                    progressWheelInterpolated!!.progress = 0.0f
                                }
                                interpolatedValue!!.text = String.format("%.2f", progress)
                            }
                        })

                        progressWheelLinear!!.setCallback(object :
                            ProgressWheel.ProgressCallback {
                            override fun onProgressUpdate(progress: Float) {
                                if (progress == 0f) {
                                    progressWheelLinear!!.progress = 1.0f
                                } else if (progress == 1.0f) {
                                    progressWheelLinear!!.progress = 0.0f
                                }
                                linearValue!!.text = String.format("%.2f", progress)
                            }
                        })
                    }
                    1 -> setProgress(0.0f)
                    2 -> setProgress(0.1f)
                    3 -> setProgress(0.25f)
                    4 -> setProgress(0.5f)
                    5 -> setProgress(0.75f)
                    6 -> setProgress(1.0f)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val defaultBarColor = progressWheel!!.getBarColor()
        val defaultWheelColor = progressWheel!!.getRimColor()
        val colorOptions = findViewById<View>(R.id.spinner_options_color) as Spinner
        colorOptions.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        progressWheel!!.setBarColor(defaultBarColor)
                        progressWheelInterpolated!!.setBarColor(defaultBarColor)
                        progressWheelLinear!!.setBarColor(defaultBarColor)
                    }
                    1 -> {
                        progressWheel!!.setBarColor(Color.RED)
                        progressWheelInterpolated!!.setBarColor(Color.RED)
                        progressWheelLinear!!.setBarColor(Color.RED)
                    }
                    2 -> {
                        progressWheel!!.setBarColor(Color.MAGENTA)
                        progressWheelInterpolated!!.setBarColor(Color.MAGENTA)
                        progressWheelLinear!!.setBarColor(Color.MAGENTA)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val wheelColorOptions = findViewById<View>(R.id.spinner_options_rim_color) as Spinner
        wheelColorOptions.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        progressWheel!!.setRimColor(defaultWheelColor)
                        progressWheelInterpolated!!.setRimColor(defaultWheelColor)
                        progressWheelLinear!!.setRimColor(defaultWheelColor)
                    }
                    1 -> {
                        progressWheel!!.setRimColor(Color.LTGRAY)
                        progressWheelInterpolated!!.setRimColor(Color.LTGRAY)
                        progressWheelLinear!!.setRimColor(Color.LTGRAY)
                    }
                    2 -> {
                        progressWheel!!.setRimColor(Color.GRAY)
                        progressWheelInterpolated!!.setRimColor(Color.GRAY)
                        progressWheelLinear!!.setRimColor(Color.GRAY)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setProgress(progress: Float) {
        progressWheelLinear!!.setCallback(object :
            ProgressWheel.ProgressCallback {
            override fun onProgressUpdate(progress: Float) {
                linearValue!!.text = String.format("%.2f", progress)
            }
        })
        progressWheelInterpolated!!.setCallback(object :
            ProgressWheel.ProgressCallback {
            override fun onProgressUpdate(progress: Float) {
                interpolatedValue!!.text = String.format("%.2f", progress)
            }
        })
        progressWheelLinear!!.progress = progress
        progressWheelInterpolated!!.progress = progress
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }
}