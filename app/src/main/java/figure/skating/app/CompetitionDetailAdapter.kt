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

class CompetitionDetailAdapter(private val competition: String, context: Context) : RecyclerView.Adapter<CompetitionDetailAdapter.CompetitionDetailViewHolder>() {

    var competitionDetailList = mutableListOf<String>()
    private val competitionDetailAdapter by lazy { CompetitionDetailAdapter(competition, context) }

    // 新しいViewHolderインスタンスを生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionDetailAdapter.CompetitionDetailViewHolder {
        return CompetitionDetailAdapter.CompetitionDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_competition_detail, parent, false)
        )
    }

    // 要素とデータを紐づける
    override fun onBindViewHolder(holder: CompetitionDetailAdapter.CompetitionDetailViewHolder, position: Int) {
        val item_season = competitionDetailList.get(position)
        holder.text_season.text = item_season.toString()

        // competitionの略称に変換
        var competition_short = ""
        when (competition){
            "Olympic Games" -> {
                competition_short = "Olympic"
            }

            "World Championships" -> {
                competition_short = "WORLD"
            }

            "World Junior Championships" -> {
                competition_short = "Jr WORLD"
            }

            "Grand Prix Final" -> {
                competition_short = "GPF"
            }

            "Junior Grand Prix Final" -> {
                competition_short = "JGPF"
            }

            "European Championships" -> {
                competition_short = "EURO"
            }

            "Four Continents Championships" -> {
                competition_short = "4CC"
            }
        }

        // リストをクリック時の処理
        holder.card_view_competition_detail.setOnClickListener {
            val intent = Intent(it.context, ResultActivity::class.java)
            intent.putExtra("COMPETITION", competition_short)
            intent.putExtra("SEASON", item_season)
            it.context.startActivity(intent)
        }

        // リザルトページへ遷移
        holder.image_webView.setOnClickListener {
            // seasonと、選択されたcompetitionからurlを抽出
            val resultURL = ResultData.searchURL(item_season, competition)!!.url

            val intent = Intent(it.context, WebViewActivity::class.java)
            intent.putExtra("URL", resultURL)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return competitionDetailList.size
    }

    class CompetitionDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // レイアウトファイルからそれぞれ代入
        val card_view_competition_detail: CardView = view.findViewById(R.id.card_view_competition_detail)
        val text_season: TextView = view.findViewById(R.id.text_season)
        val image_webView: ImageView = view.findViewById(R.id.image_webView)

    }

}