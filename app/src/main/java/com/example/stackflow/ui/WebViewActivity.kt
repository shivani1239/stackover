package com.example.stackflow.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.stackflow.R
import com.example.stackflow.core.KEY_stack__URL

class WebViewActivity : AppCompatActivity() {

    @BindView(R.id.webview)
    lateinit var webView: WebView

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        ButterKnife.bind(this)
        url = intent.getStringExtra(KEY_stack__URL)!!

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true

        webView.settings.setSupportZoom(true)
        webView.settings.domStorageEnabled = true
        webView.loadUrl(url)
    }


    @OnClick(R.id.iv_back)
    fun onBack() {
        finish()
    }
}