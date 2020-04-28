package id.lite.meet

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import id.lite.litemeet.Coba
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            webview.visibility =View.VISIBLE
            btn.visibility  = View.GONE
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    998
                )

            } else {
                loadVideo()
            }


        }
    }

    fun loadVideo(){
        var intent = Intent(Intent.ACTION_VIEW)
        intent.component = ComponentName(application.applicationContext, Coba::class.java)
        intent.putExtra("id","2zGqL7ySAU9J1FnF")
        intent.putExtra("name","wiliarko berhasil")
        startActivity(intent)
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            999 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadVideo()
                } else {
                    Toast.makeText(this,"s",Toast.LENGTH_LONG).show()
//                    finish()
                }
                return
            }
            998 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadVideo()
                } else {
                    Toast.makeText(this,"s",Toast.LENGTH_LONG).show()
//                    finish()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

}
