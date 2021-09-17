package figure.skating.app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_result.*

class ResultAdapter(private val context: Context): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>()  {

    var resultList = mutableListOf<ResultData>()
    //var resultList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ResultViewHolder {
        //return ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_result, parent, false))
        return when (viewType) {

            // MENのときのレイアウト
            MEN -> ResultViewHolder(
                LayoutInflater.from(context).inflate(R.layout.recycler_result, parent, false))

            // WOMENのときのレイアウト
            else -> ResultViewHolder(
                LayoutInflater.from(context).inflate(R.layout.recycler_result_women, parent, false))
        }


    }

    // MEN/WOMENによってレイアウトを変更するため、ViewTypeを定める
    override fun getItemViewType(position: Int): Int {
        return if(resultList[position].gender == "M") MEN else WOMEN
    }

    override fun onBindViewHolder(holder: ResultAdapter.ResultViewHolder, position: Int) {
        val item = resultList.get(position)

        holder.apply {
            text_rank.text = item.rank.toString() + "."
            text_name.text = item.name
            text_country.text = "(" + item.country + ")"
            text_score.text = String.format("%,.2f", item.score) //item.score.toString()
        }

        // クリック時の処理
        holder.image_video.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEARCH)
            intent.setPackage("com.google.android.youtube")
            intent.putExtra("query",item.name + " " + item.competition + " " + item.type)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.context.startActivity(intent)
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
        val image_video: ImageView = view.findViewById(R.id.image_video)
    }

    companion object{
        // Viewの種類を数字と紐づけ
        private const val MEN = 0 // MENのとき
        private const val WOMEN = 1 // WOMENのとき
    }
}