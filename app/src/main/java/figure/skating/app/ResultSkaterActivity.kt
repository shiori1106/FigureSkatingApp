package figure.skating.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_result_skater.*

class ResultSkaterActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_skater)

        // admob用
        MobileAds.initialize(this){}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        // レイアウトのtoolbarをtoolbar要素を取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // アクションバーにツールバーをセット
        setSupportActionBar(toolbar)

        // アクションバーに戻るボタンをつける
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)


        // Fragment4からNAME, COUNTRYを受け取る
        val nameFromFragment4 = intent.getStringExtra("NAME")
        val countryFromFragment4 = intent.getStringExtra("COUNTRY")

        // タイトルバーの設定
        supportActionBar?.setTitle(nameFromFragment4 + " " + countryFromFragment4)

        // RecyclerViewの表示のために必要となるAdapterクラス
        val resultSkaterAdapter by lazy {ResultSkaterAdapter(this)}


        // recycleViewの初期化を行う
        recyclerView_skater.apply{
            adapter = resultSkaterAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(context)
        }

        // Realmの設定
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()

        // 名前を姓名に分割
        val nameSplit = nameFromFragment4!!.split(' ')

        // 大きさが4のリストを作成して、分割した姓名を格納する
        var nameSearch = listOf<String>("","","","")
        when (nameSplit.size){
            2 -> nameSearch = listOf(nameSplit[0], nameSplit[1], "","")
            3 -> nameSearch = listOf(nameSplit[0], nameSplit[1], nameSplit[2],"")
            else -> nameSearch = listOf(nameSplit[0], nameSplit[1], nameSplit[2], nameSplit[3])
        }

        // 選手のSPデータを格納
        val skaterRealmResults_sp = mRealm.where(ResultData::class.java)
            .contains(ResultData::name.name, nameSearch[0])
            .contains(ResultData::name.name, nameSearch[1])
            .contains(ResultData::name.name, nameSearch[2])
            .contains(ResultData::name.name, nameSearch[3])
            .equalTo(ResultData::type.name, "SP")
            .findAll()
            .sort("startDate", Sort.ASCENDING)

        val skaterRealmList = mutableListOf<SkaterData>()
        for (i in 0..skaterRealmResults_sp.size-1){

            // FSの結果を格納
            val skaterRealmResults_fs = mRealm.where(ResultData::class.java)
                .contains(ResultData::name.name, nameSearch[0])
                .contains(ResultData::name.name, nameSearch[1])
                .contains(ResultData::name.name, nameSearch[2])
                .contains(ResultData::name.name, nameSearch[3])
                .equalTo(ResultData::type.name, "FS")
                .equalTo(ResultData::competition.name, skaterRealmResults_sp[i]!!.competition)
                .findFirst()

            // FSがない場合は0を格納
            var skaterRealmResults_fs_score = 0.0
            if ((skaterRealmResults_fs != null) && (skaterRealmResults_fs.score != null)){
                skaterRealmResults_fs_score = mRealm.copyFromRealm(skaterRealmResults_fs)!!.score
            }

            var skaterRealmResults_fs_rank = 0
            if ((skaterRealmResults_fs != null) && (skaterRealmResults_fs.rank != null)){
                skaterRealmResults_fs_rank = mRealm.copyFromRealm(skaterRealmResults_fs)!!.rank
            }


            // FINALの結果を格納
            val skaterRealmResults_final = mRealm.where(ResultData::class.java)
                .contains(ResultData::name.name, nameSearch[0])
                .contains(ResultData::name.name, nameSearch[1])
                .contains(ResultData::name.name, nameSearch[2])
                .contains(ResultData::name.name, nameSearch[3])
                .equalTo(ResultData::type.name, "FINAL")
                .equalTo(ResultData::competition.name, skaterRealmResults_sp[i]!!.competition)
                .findFirst()

            // FINALがない場合は0を格納
            var skaterRealmResults_final_score = 0.0
            if ((skaterRealmResults_final != null) && (skaterRealmResults_final.score != null)){
                skaterRealmResults_final_score = mRealm.copyFromRealm(skaterRealmResults_final)!!.score
            }

            var skaterRealmResults_final_rank = 0
            if ((skaterRealmResults_final != null) && (skaterRealmResults_final.rank != null)){
                skaterRealmResults_final_rank = mRealm.copyFromRealm(skaterRealmResults_final)!!.rank
            }
            //val skaterRealmResults_final_score = mRealm.copyFromRealm(skaterRealmResults_final)!!.score?:0.0
            //val skaterRealmResults_final_rank = mRealm.copyFromRealm(skaterRealmResults_final)!!.rank ?:0

            skaterRealmList.add(SkaterData(skaterRealmResults_sp[i]!!.competition,
                                            skaterRealmResults_sp[i]!!.competition_short,
                                            skaterRealmResults_sp[i]!!.season,
                                            skaterRealmResults_sp[i]!!.score,
                                            skaterRealmResults_sp[i]!!.rank,
                                            skaterRealmResults_fs_score,
                                            skaterRealmResults_fs_rank,
                                            skaterRealmResults_final_score,
                                            skaterRealmResults_final_rank))
        }

        resultSkaterAdapter.skaterList = skaterRealmList
    }

    // 戻るボタンを押すと、アクティビティを終了させることで前の画面に戻る
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}