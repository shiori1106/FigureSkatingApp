package figure.skating.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultSkaterAdapter(private val context: Context): RecyclerView.Adapter<ResultSkaterAdapter.ResultSkaterViewHolder>(){

    var skaterList = mutableListOf<SkaterData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultSkaterAdapter.ResultSkaterViewHolder {
        return ResultSkaterAdapter.ResultSkaterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_skater, parent, false))
    }

    override fun onBindViewHolder(
        holder: ResultSkaterAdapter.ResultSkaterViewHolder,
        position: Int
    ) {
        val item = skaterList.get(position)

        holder.apply {
            text_competition_short.text = "/ " + item.skater_competition_short
            text_season.text = item.skater_season
            text_sp_score.text = "-SP:" + String.format("%,.2f", item.sp_score)
            text_sp_rank.text = " (" + item.sp_rank + ")"
            if(item.fs_score == 0.0){
                text_fs_score.text = ""
                text_fs_rank.text = "-FS:--"
                text_final_score.text = ""
                text_final_rank.text = "　…　--"
            } else {
                text_fs_score.text = "-FS:" + String.format("%,.2f", item.fs_score)
                text_fs_rank.text = " (" + item.fs_rank + ")"
                text_final_score.text = "　…　" + String.format("%,.2f", item.final_score)
                //text_final_score.text = "-FINAL:" + String.format("%,.2f", item.final_score)
                text_final_rank.text = " (" + item.final_rank + ")"
            }

        }
    }

    override fun getItemCount(): Int {
        return skaterList.size
    }

    class ResultSkaterViewHolder(view: View):RecyclerView.ViewHolder(view){
        // レイアウトファイルからそれぞれ代入
        val text_competition_short: TextView = view.findViewById(R.id.text_competition_short)
        val text_season: TextView = view.findViewById(R.id.text_season)
        val text_sp_score: TextView = view.findViewById(R.id.text_sp_score)
        val text_sp_rank: TextView = view.findViewById(R.id.text_sp_rank)
        val text_fs_score: TextView = view.findViewById(R.id.text_fs_score)
        val text_fs_rank: TextView = view.findViewById(R.id.text_fs_rank)
        val text_final_score: TextView = view.findViewById(R.id.text_final_score)
        val text_final_rank: TextView = view.findViewById(R.id.text_final_rank)
    }


}