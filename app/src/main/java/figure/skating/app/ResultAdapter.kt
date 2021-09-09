package figure.skating.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter(private val context: Context): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>()  {

    var resultList = mutableListOf<ResultData>()
    //var resultList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ResultViewHolder {
        return ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_result, parent, false))
    }

    override fun onBindViewHolder(holder: ResultAdapter.ResultViewHolder, position: Int) {
        val item = resultList.get(position)

        holder.apply {
            text_rank.text = item.rank.toString() + "."
            text_name.text = item.name
            text_country.text = "(" + item.country + ")"
            text_score.text = item.score.toString()
        }
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    class ResultViewHolder(view: View):RecyclerView.ViewHolder(view){

        // レイアウトファイルからそれぞれ代入
        val card_view_result: CardView = view.findViewById(R.id.card_view_result)
        val text_rank: TextView = view.findViewById(R.id.text_rank)
        val text_name: TextView = view.findViewById(R.id.text_name)
        val text_country: TextView = view.findViewById(R.id.text_country)
        val text_score: TextView = view.findViewById(R.id.text_score)
    }
}