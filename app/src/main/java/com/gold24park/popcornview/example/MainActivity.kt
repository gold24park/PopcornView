package com.gold24park.popcornview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.gold24park.popcornview.example.databinding.ActivityMainBinding
import com.gold24park.popcornview.popcornview.PopcornShape

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var amount = 0
    private var interval = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekbarAmount.min = 1
        binding.seekbarAmount.max = 100

        binding.seekbarAmount.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvAmount.text = p1.toString()
                amount = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        binding.seekbarInterval.min = 0
        binding.seekbarInterval.max = 300

        binding.seekbarInterval.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvInterval.text = p1.toString()
                interval = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        binding.seekbarAmount.progress = 40

        binding.btnStart.setOnClickListener {
            val shape = PopcornShape.DrawableShape(
                size = 100F,
                drawable = ContextCompat.getDrawable(this, R.drawable.popcorn)!!,
            )
//            val shape = PopcornShape.TextShape(
//                size = 100F,
//                text = "\uD83C\uDF7F",
//                textColor = ContextCompat.getColor(this, R.color.yellow)
//            )
//            val shape = PopcornShape.TextShape(
//                size = 100F,
//                text = "Pop!",
//                textColor = ContextCompat.getColor(this, R.color.yellow)
//            )
            binding.popcornView.start(shape, amount, interval.toLong())
        }
    }
}