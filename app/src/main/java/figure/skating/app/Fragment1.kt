package figure.skating.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.fragment1.*
import java.util.*

class Fragment1: Fragment() {

    // RecyclerViewの表示のために必要となるAdapterクラス
    private val seasonAdapter by lazy{SeasonAdapter(requireContext())}
    private lateinit var mRealm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // レイアウトを確定
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycleViewの初期化を行う
        recyclerView1.apply{
            adapter = seasonAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Realmの設定
        Realm.init(requireContext())

        // csvからインポート
        //val config = RealmConfiguration.Builder().assetFile("sampleData.realm").build()
        //Realm.setDefaultConfiguration(config)

        mRealm = Realm.getDefaultInstance()

        // Realmデータベースから、シーズンのみ重複なしで取得
        /*val seasonRealmResults = mRealm.where(ResultData::class.java)
            .distinct("season")
            .findAll()
            .sort("season", Sort.DESCENDING)

        Log.d("kotlintest",seasonRealmResults[0]!!.name)
        Log.d("kotlintest",seasonRealmResults[0]!!.season)
*/
        // シーズンのデータを全てseasonRealmListに格納
        val seasonRealmResults = mRealm.where(ResultData::class.java).findAll()
        val seasonRealmList = mutableListOf<String>()
        for (i in 0..seasonRealmResults.size-1){
            seasonRealmList.add(seasonRealmResults[i]!!.season)
        }

        // 重複を削除し、並び替え
        val seasonRealmList_sorted = seasonRealmList.distinct().sortedDescending().toMutableList()

        seasonAdapter.seasonList = seasonRealmList_sorted

        // realmの中身を表示
        for (j in 0..seasonRealmResults.size-1){
            Log.d("kotlintest_realm",seasonRealmResults[j]!!.season + seasonRealmResults[j]!!.competition )
        }

        //seasonAdapter.seasonList = mRealm.copyFromRealm(seasonRealmResults)
    //Log.d("kotlintest",seasonList.toString())

        /*// データ削除

        mRealm.executeTransaction { realm ->
            realm.where(ResultData::class.java)
                .findAll()
                .deleteAllFromRealm()
        }*/


        /*// サンプルデータ追加
        mRealm.executeTransaction {
            //val addData = it.createObject(ResultData::class.java, UUID.randomUUID().toString())
            val addData = it.createObject(ResultData::class.java,1)
            //addData.id = 1
            addData.type = "SP"
            addData.rank = 1
            addData.name = "Yuzuru Hanyu"
            addData.country = "JPN"
            addData.score = 100.45
            addData.startDate = "2017/3/30"
            addData.competition = "World Championships"
            addData.url = "http://www.isuresults.com/results/season2122/jgpsvk2021/"
            addData.season = "2020-2021"
            addData.gender = "M"

        }*/

        // これいらない？
        /*val addData = ResultData()
        mRealm.beginTransaction()
        mRealm.insert(addData)
        mRealm.commitTransaction()

        mRealm.close()
        */


    }


    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}