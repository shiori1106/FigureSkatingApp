package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_season_detail.*
import kotlinx.android.synthetic.main.fragment1.*

class SeasonDetailActivity : AppCompatActivity() {

    // RecyclerViewの表示のために必要となるAdapterクラス
    //private val seasonDetailAdapter by lazy{SeasonDetailAdapter(season,this)}
    private lateinit var mRealm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_detail)

        // Fragment1からSEASONを受け取る
        val seasonFromFragment1 = intent.getStringExtra("SEASON")

        // タイトルバーの設定
        title = seasonFromFragment1


        // recycleViewの初期化を行う
        recyclerView1_2.apply{
            //adapter = seasonDetailAdapter(seasonFromFragment1, context)
            adapter = SeasonDetailAdapter(seasonFromFragment1!!, context)

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(context)
        }

        // Realmの設定
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}