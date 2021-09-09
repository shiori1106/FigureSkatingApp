package figure.skating.app

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SeasonDetailAdapter(private val season: String, context: Context): RecyclerView.Adapter<SeasonDetailAdapter.SeasonDetailViewHolder>() {

    var seasonDetailList = mutableListOf("World Championships","Grand Prix Final")
    private val seasonDetailAdapter by lazy {SeasonDetailAdapter(season, context)}
    //var seasonDetailList = mutableListOf<String>()

    // 新しいViewHolderインスタンスを生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonDetailAdapter.SeasonDetailViewHolder {
        return SeasonDetailAdapter.SeasonDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_season_detail, parent, false)
        )
    }

    // 要素とデータを紐づける
    override fun onBindViewHolder(holder: SeasonDetailAdapter.SeasonDetailViewHolder, position: Int) {
        val item_competition = seasonDetailList.get(position)
        holder.text_competition.text = item_competition.toString()


        // クリック時の処理
        holder.card_view_season_detail.setOnClickListener {
            val intent = Intent(it.context, ResultActivity::class.java)
            intent.putExtra("COMPETITION", holder.text_competition.text.toString())
            intent.putExtra("SEASON", season)
            it.context.startActivity(intent)
            Log.d("kotlintest",holder.text_competition.text.toString())
        }


        // 1つ前の画面から渡されたseasonを格納
        //val item_season = intent.getStringExtra("SEASON").toString()



        holder.image_webView.setOnClickListener {
            // seasonと、選択されたcompetitionからurlを抽出
            val resultURL = ResultData.searchURL(season, item_competition)!!.url

            Log.d("kotlintest",season + "&" + holder.text_competition.text.toString())
            Log.d("kotlintest",resultURL)
            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL",resultURL)
            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return seasonDetailList.size
    }

    class SeasonDetailViewHolder(view: View):RecyclerView.ViewHolder(view){

        // レイアウトファイルからそれぞれ代入
        val card_view_season_detail: CardView = view.findViewById(R.id.card_view_season_detail)
        val text_competition: TextView = view.findViewById(R.id.text_competition)
        val image_webView: ImageView = view.findViewById(R.id.image_webView)

    }

}