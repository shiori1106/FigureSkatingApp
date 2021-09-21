package figure.skating.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.fragment3.*
import kotlinx.android.synthetic.main.fragment3.adView

class Fragment3: Fragment() {

    lateinit var mAdView : AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // admob用
        MobileAds.initialize(requireContext()){}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(error: LoadAdError) {
                Log.d("Ads_test3", error.toString())
            }
        }

        cardView_ISU1.setOnClickListener {
            webViewURL(it, getString(R.string.URL_ISU_statistics))
        }

        cardView_ISU2.setOnClickListener {
            webViewURL(it, getString(R.string.URL_ISU_results))
        }

        cardView_ISU3.setOnClickListener {
            webViewURL(it, getString(R.string.URL_ISU_calendar))
        }

        cardView_ISU4.setOnClickListener {
            webViewURL(it, getString(R.string.URL_ISU_bio))
        }

        cardView_JSF1.setOnClickListener {
            webViewURL(it, getString(R.string.URL_JSF_results))
        }

        cardView_JSF2.setOnClickListener {
            webViewURL(it, getString(R.string.URL_JSF_player))
        }

        cardView_other1.setOnClickListener {
            val intent = Intent(it.context, AboutAppActivity::class.java)
            it.context.startActivity(intent)
        }

        cardView_other2.setOnClickListener {
            // PlayStoreが無効の場合は、webに飛ぶようにする
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.URL_googleplay_market)))
                it.context.startActivity(intent)

            } catch (anfe: android.content.ActivityNotFoundException){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.URL_googleplay_url)))
                it.context.startActivity(intent)
            }

        }

        cardView_other3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.URL_twitter)))
            it.context.startActivity(intent)
        }

    }

    fun webViewURL(view: View, url: String){
        val intent = Intent(view.context, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        view.context.startActivity(intent)
    }
}