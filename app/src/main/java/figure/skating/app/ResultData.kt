package figure.skating.app

import io.realm.Realm
import io.realm.RealmObject
import io.realm.Sort
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where
import java.io.Serializable
import java.util.*

open class ResultData: RealmObject(), Serializable {
    @PrimaryKey
    //var id: String = UUID.randomUUID().toString()
    var id: Int = 0
    var type: String = "" // SP, FS, FINAL
    var rank: Int = 0
    var name: String = ""
    var country: String = ""
    var score: Double = 0.00
    var startDate: String = ""
    var competition: String = ""
    var url: String = ""
    var season: String = ""
    var gender: String = ""

    companion object{

        // 全件取得
        fun findAll(): List<ResultData> =
            Realm.getDefaultInstance().use{ realm ->
                realm.where(ResultData::class.java)
                    .findAll().let{
                        realm.copyFromRealm(it)
                    }
            }

        // シーズンを重複なしで取得
        fun seasonResult(): List<ResultData> =
            Realm.getDefaultInstance().use{ realm ->
                realm.where(ResultData::class.java)
                    .distinct("season")
                    .sort("season", Sort.DESCENDING)
                    .findAll().let{
                        realm.copyFromRealm(it)
                    }
            }

        // 試合を重複なしで取得
        fun competitionResult(): List<ResultData> =
            Realm.getDefaultInstance().use{ realm ->
                realm.where(ResultData::class.java)
                    .distinct("competition")
                    .sort("startDate", Sort.ASCENDING)
                    .findAll().let{
                        realm.copyFromRealm(it)
                    }
            }

        fun searchURL(season: String, competition: String): ResultData? =
            Realm.getDefaultInstance().use{ realm ->
                realm.where(ResultData::class.java)
                    .equalTo(ResultData::season.name, season)
                    .equalTo(ResultData::competition.name, competition)
                    .findFirst()?.let{
                        realm.copyFromRealm(it)
                    }
            }

    }
}