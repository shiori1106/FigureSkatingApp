package figure.skating.app

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SeasonAdapter(private val context: Context): RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    //var seasonList = mutableListOf("2021-2022","2020-2021","2019-2020")
    var seasonList = mutableListOf<String>()
    private val seasonAdapter by lazy {SeasonAdapter(context)}

    // 新しいViewHolderインスタンスを生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_season, parent, false)
        )
    }

    // 要素とデータを紐づける
    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val item = seasonList.get(position)
        holder.text_season.text = item

        // クリック時の処理
        holder.card_view_season.setOnClickListener {
            //val context = holder.view.context
            val intent = Intent(context, SeasonDetailActivity::class.java)
            intent.putExtra("SEASON", holder.text_season.text.toString())
            context.startActivity(intent)
            Log.d("kotlintest",holder.text_season.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    class SeasonViewHolder(view: View):RecyclerView.ViewHolder(view){

        // レイアウトファイルからそれぞれ代入
        val card_view_season: CardView = view.findViewById(R.id.card_view_season)
        val text_season: TextView = view.findViewById(R.id.text_season)

    }

}