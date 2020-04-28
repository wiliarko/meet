package id.lite.litemeet

import android.content.Context
import android.widget.Toast

class Toas{
    companion object Builder{
        fun i(c: Context,s:String){
            Toast.makeText(c,s,Toast.LENGTH_LONG).show()
        }
    }
}