package figure.skating.app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CompetitionAdapter(private val context: Context): RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>() {

    var competitionList = mutableListOf<String>()

    // 新しいViewHolderインスタンスを生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionAdapter.CompetitionViewHolder {
        return CompetitionAdapter.CompetitionViewHolder(
                LayoutInflater.from(context).inflate(R.layout.recycler_competition, parent, false)
        )
    }

    // 要素とデータを紐づける
    override fun onBindViewHolder(holder: CompetitionAdapter.CompetitionViewHolder, position: Int) {
        val item = competitionList.get(position)
        holder.text_competition.text = item

        // クリック時の処理
        holder.card_view_competition.setOnClickListener {
            //val context = holder.view.context
            val intent = Intent(context, CompetitionDetailActivity::class.java)
            intent.putExtra("COMPETITION", holder.text_competition.text.toString())
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return competitionList.size
    }

    class CompetitionViewHolder(view: View):RecyclerView.ViewHolder(view){

        // レイアウトファイルからそれぞれ代入
        val card_view_competition: CardView = view.findViewById(R.id.card_view_competition)
        val text_competition: TextView = view.findViewById(R.id.text_competition)

    }
}