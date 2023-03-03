package com.minfo.shazam

import android.media.AudioRecord
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

//import com.shazam.shazamkit.AudioSampleRateInHz
//import com.shazam.shazamkit.ShazamKit

import com.shazam.shazamkit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var currentSession: StreamingSession? = null
    private var audioRecord: AudioRecord? = null
    private var recordingThread: Thread? = null
    private var isRecording = false
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var catalog: Catalog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.myButton)
        val tv = findViewById<TextView>(R.id.textView)
        btn.text = "Haha"
        var nb = 1
        btn.setOnClickListener {
            nb++
            btn.text = "Haha you clickd"
            tv.text = nb.toString()+" Clicks"

        }

        var developerToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiIsImtpZCI6IjQyODQ0MzIzTUsifQ.eyJleHAiOjE2OTE1MjAwMjQsImlzcyI6IjVCU05ZWVlINEsiLCJ0eXAiOiJKV1QifQ.GJrFb0Y0jfdosQz31EO31yuzQlh-tcIlZfsVX0Dm03c8-U60p7xzbNQtAoB81ep6SCZYscVdYUNamKCjdWPMOg"

        val tokenProvider = DeveloperTokenProvider {
            DeveloperToken(developerToken)
        }

        catalog = ShazamKit.createShazamCatalog(tokenProvider)


        val catalog = (ShazamKit.createShazamCatalog(tokenProvider) as ShazamKitResult.Success<*>).data
        val currentSession = (
                ShazamKit.createStreamingSession(
            catalog,
            AudioSampleRateInHz.SAMPLE_RATE_48000,
            readBufferSize
        ) as Success).data

        coroutineScope.launch {
            // record audio and flow it to the StreamingSession
            recordingFlow().collect { audioChunk ->
                currentSession?.matchStream(
                    audioChunk.buffer,
                    audioChunk.meaningfulLengthInBytes,
                    audioChunk.timestamp
                )
            }
        }




    }
}