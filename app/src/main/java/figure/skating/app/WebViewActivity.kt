package figure.skating.app

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // レイアウトのtoolbarをtoolbar要素を取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // アクションバーにツールバーをセット
        setSupportActionBar(toolbar)

        // アクションバーに戻るボタンをつける
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)

        // タイトルバーのアプリ名を非表示
        supportActionBar?.setDisplayShowTitleEnabled(false)

        /*// アクションバーのタイトルを設定
        val url_now = webView.url
        val title_now = webView.title
        supportActionBar?.setTitle(title_now)
        supportActionBar?.setSubtitle(url_now)*/

        // リンク先もWebView内で遷移させる
        webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean{
                return false
            }
        }

        // Javascriptを有効にする
        webView.settings.javaScriptEnabled = true

        // URLにアクセス
        webView.loadUrl(intent.getStringExtra("URL").toString())
    }

    // 左下の戻るボタンで、ブラウザバックをさせる
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView!!.canGoBack()) {
            this.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    // 左上の戻るボタンを押すと、アクティビティを終了させることで前の画面に戻る
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    // いらない？
    companion object{
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, url: String){
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL, url))
        }
    }

    // 右上のメニューを作成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_menu, menu)
        return true
    }

    // 右上のメニューが押されたとき
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val url_now = webView.url

        return when(id){
            R.id.web1 -> {
                // URLをコピー
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("textLabel", url_now)
                clipboard.setPrimaryClip(clip)
                true
            }
            R.id.web2 -> {
                // ブラウザで開く
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url_now))
                startActivity(intent)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}