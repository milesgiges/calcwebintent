package com.faustine.calcwebintent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class IntentActivity : AppCompatActivity() {
    lateinit var Buttonsms:Button
    lateinit var Buttoncall:Button
    lateinit var Buttonmpesa:Button
    lateinit var Buttonemail:Button
    lateinit var Buttonshare:Button
    lateinit var Buttoncamera:Button
    lateinit var Buttondial:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        Buttonsms=findViewById(R.id.Btn_sms)
        Buttoncall=findViewById(R.id.Btn_call)
        Buttonmpesa=findViewById(R.id.Btn_mpesa)
        Buttonemail=findViewById(R.id.Btn_email)
        Buttonshare=findViewById(R.id.Btn_share)
        Buttoncamera=findViewById(R.id.Btn_camera)
        Buttondial=findViewById(R.id.Btn_dial)

        Buttonsms.setOnClickListener {
            val uri=Uri.parse("smsto:0703784545")
            val intent=Intent(Intent.ACTION_SENDTO,uri)
            intent.putExtra("Hello", "How is today's weather")
            startActivity(intent)
        }
        Buttoncall.setOnClickListener {
            val intent=Intent(Intent.ACTION_CALL, Uri.parse(("tel:" +254703784545)))
            if (ContextCompat.checkSelfPermission(
                    this@IntentActivity,
                    Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(
                    this@IntentActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            }else{
                startActivity(intent)
            }
        }
        Buttonmpesa.setOnClickListener {
            val simToolkitlaunch=applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolkitlaunch?.let { startActivity(it) }
        }
        Buttonemail.setOnClickListener {
            val emailIntent=Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "milesgiges@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        Buttonshare.setOnClickListener {
            val shareIntent=Intent(Intent.ACTION_SEND)
            shareIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey download this app!")
            startActivity(shareIntent)
        }
        Buttoncamera.setOnClickListener {
            val takepictureIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(takepictureIntent, 1)
        }
        Buttondial.setOnClickListener {
            val phone="+254703784545"
            val intent=Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }


    }
}