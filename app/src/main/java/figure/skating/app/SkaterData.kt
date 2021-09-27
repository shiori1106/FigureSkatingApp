package figure.skating.app

import java.io.Serializable

class SkaterData(val skater_competition: String,
                 val skater_competition_short: String,
                 val skater_season: String,
                 val sp_score: Double,
                 val sp_rank: Int,
                 val fs_score: Double,
                 val fs_rank: Int,
                 val final_score: Double,
                 val final_rank:Int):Serializable