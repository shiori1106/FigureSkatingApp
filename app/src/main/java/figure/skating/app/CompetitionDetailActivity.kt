package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_competition_detail.*
import kotlinx.android.synthetic.main.activity_season_detail.*

class CompetitionDetailActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competition_detail)

        // レイアウトのtoolbarをtoolbar要素を取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // アクションバーにツールバーをセット
        setSupportActionBar(toolbar)

        // アクションバーに戻るボタンをつける
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)


        // Fragment2からCOMPETITIONを受け取る
        val competitionFromFragment2 = intent.getStringExtra("COMPETITION")

        // COPMETITIONから略称に変換
        var competition_short = ""

        when (competitionFromFragment2){
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

        // タイトルバーの設定
        //title = competitionFromFragment2
        supportActionBar?.setTitle(competitionFromFragment2)

        // RecyclerViewの表示のために必要となるAdapterクラス
        val competitionDetailAdapter by lazy{CompetitionDetailAdapter(competitionFromFragment2!!,this)}


        // recycleViewの初期化を行う
        recyclerView2_2.apply{
            adapter = competitionDetailAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(context)
        }

        // Realmの設定
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()

        // シーズンのデータを全てseasonRealmListに格納
        val seasonRealmResults = mRealm.where(ResultData::class.java)
            .equalTo(ResultData::competition_short.name, competition_short)
            .findAll()
        val seasonRealmList = mutableListOf<String>()
        for (i in 0..seasonRealmResults.size-1){
            seasonRealmList.add(seasonRealmResults[i]!!.season)
        }

        // 重複を削除し、並び替え
        val seasonRealmList_sorted = seasonRealmList.distinct().sortedDescending().toMutableList()

        competitionDetailAdapter.competitionDetailList = seasonRealmList_sorted

    }

    // 戻るボタンを押すと、アクティビティを終了させることで前の画面に戻る
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}