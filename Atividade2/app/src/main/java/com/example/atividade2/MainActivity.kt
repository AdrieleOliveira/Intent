package com.example.atividade2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTexto.visibility = View.INVISIBLE;
        imgImage.visibility = View.INVISIBLE;

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if("text/plain" == intent.type){
                    handleSendText(intent)
                } else if(intent.type?.startsWith("image/") == true){
                    handleSendImage(intent)
                }
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            tvTexto.text = intent.getStringExtra(Intent.EXTRA_TEXT).toString();
            tvTexto.visibility = View.VISIBLE;
        }
    }


    private fun handleSendImage(intent: Intent){
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            imgImage.setImageURI(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri);
            imgImage.visibility = View.VISIBLE
        }
    }
}
