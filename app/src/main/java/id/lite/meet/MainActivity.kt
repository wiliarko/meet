package id.lite.meet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                998)

        }else{
            permisioncamera()
        }
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.MICROPHONE)
//            != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.RECORD_AUDIO),
//                996)
//
//        }else{
//            permisioncamera()
//        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MODIFY_AUDIO_SETTINGS)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                997)

        }else{
            permisioncamera()
        }
        permisioncamera()

    }

    fun permisioncamera(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                999)

        } else {
            loadmeet()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            999 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadmeet()
                } else {
                    Toast.makeText(this,"s",Toast.LENGTH_LONG).show()
//                    finish()
                }
                return
            }
            998 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    permisioncamera()
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

    fun loadmeet(){

//        val url = "https://meet2.litebig.com/bigbluebutton/api/join?meetingID=2&fullName=tester&password=m1&redirect=true&joinViaHtml5=false&checksum=538a19708c203bf22a3232c1f6331a7ff9d0e7f1"
//        val builder = CustomTabsIntent.Builder()
//        builder.setShowTitle(true)
//        val customTabsIntent = builder.build()
//
//        customTabsIntent.launchUrl(this, Uri.parse(url))

        webview.loadUrl("http://192.168.43.5/meet/public/room/cekjoin/2zGqL7ySAU9J1FnF/a")
//        webview.loadUrl("https://www.youtube.com/watch?v=C9_59BIo5XU")
        val settings = webview.settings
        settings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");

        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)


        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true


        // Zoom web view text
//        settings.textZoom = 125


        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowFileAccess = true

        // WebView settings
        webview.fitsSystemWindows = true
        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)

//        webview.webViewClient = object: WebViewClient(){
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
//                // Page loading started
//                // Do something
////                somethingtoast("Page loading.")loading
////
////                // Enable disable back forward button
////                button_back.isEnabled = web_view.canGoBack()
////                button_forward.isEnabled = web_view.canGoForward()
////            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                // Page loading finished
//                // Display the loaded page title in a toast message
////                toast("Page loaded: ${view.title}")
////
////                // Enable disable back forward button
////                button_back.isEnabled = web_view.canGoBack()
////                button_forward.isEnabled = web_view.canGoForward()
//            }
//        }

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }

//            override fun onLoadResource(view: WebView, url: String){
//                if (url.startsWith("https://meet2.litebig.com/")) {
//                    val builder = CustomTabsIntent.Builder()
//                    val customTabsIntent = builder.build()
//                    customTabsIntent.launchUrl(this@MainActivity, Uri.parse(url))
//                }
//            }
        }

        // Set web view chrome client
        webview.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar.progress = newProgress
            }
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }


    }
}
