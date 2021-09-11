package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_season_detail.*
import kotlinx.android.synthetic.main.fragment1.*

class SeasonDetailActivity : AppCompatActivity() {

    // Fragment1からSEASONを受け取る変数に初期値を入れておく
    //var seasonFromFragment1: String = "2030-2031"

    // RecyclerViewの表示のために必要となるAdapterクラス
    //private val seasonDetailAdapter by lazy{SeasonDetailAdapter(seasonFromFragment1!!,this)}
    private lateinit var mRealm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_detail)

        // Fragment1からSEASONを受け取る
        val seasonFromFragment1 = intent.getStringExtra("SEASON")

        // タイトルバーの設定
        title = seasonFromFragment1

        // RecyclerViewの表示のために必要となるAdapterクラス
        val seasonDetailAdapter by lazy{SeasonDetailAdapter(seasonFromFragment1!!,this)}



        // recycleViewの初期化を行う
        recyclerView1_2.apply{
            //adapter = seasonDetailAdapter(seasonFromFragment1, context)
            adapter = seasonDetailAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(context)
        }

        // Realmの設定
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()

        // 表示させるリストを作成
        val seasonDetailRealmList = mutableListOf<String>()


        // オリンピックが含まれるか
        if (ResultData.findBy("Olympic", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("Olympic Games")
        }

        // 世界選手権が含まれるか
        if (ResultData.findBy("WORLD", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("World Championships")
        }

        // 世界ジュニア選手権が含まれるか
        if (ResultData.findBy("Jr WORLD", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("World Junior Championships")
        }

        // グランプリファイナルが含まれるか
        if (ResultData.findBy("GPF", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("Grand Prix Final")
        }

        // ジュニアグランプリファイナルが含まれるか
        if (ResultData.findBy("JGPF", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("Junior Grand Prix Final")
        }

        // 欧州選手権が含まれるか
        if (ResultData.findBy("EURO", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("European Championships")
        }

        // 四大陸選手権が含まれるか
        if (ResultData.findBy("4CC", seasonFromFragment1!!) != null ){
            seasonDetailRealmList.add("Four Continents Championships")
        }

        seasonDetailAdapter.seasonDetailList = seasonDetailRealmList
        Log.d("kotlintest",seasonDetailAdapter.seasonDetailList.size.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}