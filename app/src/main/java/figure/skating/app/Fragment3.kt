package figure.skating.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment3.*

class Fragment3: Fragment() {

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

        cardView_ISU1.setOnClickListener {
            val URL1 = getString(R.string.URL_ISU_statistics)
            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL", URL1)
            it.context.startActivity(intent)
        }

        cardView_ISU2.setOnClickListener {
            val URL2 = getString(R.string.URL_ISU_results)
            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL", URL2)
            it.context.startActivity(intent)
        }

        cardView_ISU3.setOnClickListener {
            val URL3 = getString(R.string.URL_ISU_calendar)
            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL", URL3)
            it.context.startActivity(intent)
        }

        /*button_youtube.setOnClickListener {
            //val intent = Intent(context, YoutubeViewActivity::class.java)
            val intent = Intent(Intent.ACTION_SEARCH)
            intent.setPackage("com.google.android.youtube")
            intent.putExtra("query","hanyu")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }*/
    }

}