package figure.skating.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_app.*
import kotlinx.android.synthetic.main.fragment3.*

class AboutAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        button_mail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.gmail)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.gmail_subject))
            }
            startActivity(intent)
        }

        button_twitter.setOnClickListener {
            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL", getString(R.string.URL_twitter))
            startActivity(intent)
        }
    }
}