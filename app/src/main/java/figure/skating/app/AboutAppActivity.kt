package figure.skating.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_about_app.*

class AboutAppActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        // admob用
        MobileAds.initialize(this){}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // レイアウトのtoolbarをtoolbar要素を取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // アクションバーにツールバーをセット
        setSupportActionBar(toolbar)

        // アクションバーに戻るボタンをつける
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        // タイトルバーの設定
        supportActionBar?.setTitle("About App")

        button_mail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.gmail)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.gmail_subject))
            }
            startActivity(intent)
        }

        button_twitter.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.URL_twitter)))
            it.context.startActivity(intent)
        }
    }

    // 戻るボタンを押すと、アクティビティを終了させることで前の画面に戻る
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}