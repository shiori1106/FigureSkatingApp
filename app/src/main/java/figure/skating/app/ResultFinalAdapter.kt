package figure.skating.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ResultFinalAdapter(private val context: Context): RecyclerView.Adapter<ResultFinalAdapter.ResultFinalViewHolder>()  {

    var resultFinalList = mutableListOf<ResultData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultFinalAdapter.ResultFinalViewHolder {
        return  ResultFinalAdapter.ResultFinalViewHolder(
                LayoutInflater.from(context).inflate(R.layout.recycler_result_final, parent, false)
            )
        }

    override fun onBindViewHolder(holder: ResultFinalAdapter.ResultFinalViewHolder, position: Int) {
        val item = resultFinalList.get(position)

        holder.apply {
            text_rank.text = item.rank.toString() + "."
            text_name.text = item.name
            text_country.text = "(" + item.country + ")"
            text_score.text = String.format("%,.2f", item.score) //item.score.toString()
        }
    }

    override fun getItemCount(): Int {
        return resultFinalList.size
    }

    class ResultFinalViewHolder(view: View):RecyclerView.ViewHolder(view){

        // レイアウトファイルからそれぞれ代入
        val card_view_result: CardView = view.findViewById(R.id.card_view_result)
        val text_rank: TextView = view.findViewById(R.id.text_rank)
        val text_name: TextView = view.findViewById(R.id.text_name)
        val text_country: TextView = view.findViewById(R.id.text_country)
        val text_score: TextView = view.findViewById(R.id.text_score)
    }

}