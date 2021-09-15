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
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
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
        mRealm = Realm.getDefaultInstance()

        // Realmのデータを全て消す
        deleteAll()

        // CSVのデータをRealmに格納
        readCSV("resultData.csv")


        // シーズンのデータを全てseasonRealmListに格納
        val seasonRealmResults = mRealm.where(ResultData::class.java).findAll()
        val seasonRealmList = mutableListOf<String>()
        for (i in 0..seasonRealmResults.size-1){
            seasonRealmList.add(seasonRealmResults[i]!!.season)
        }

        // 重複を削除し、並び替え
        val seasonRealmList_sorted = seasonRealmList.distinct().sortedDescending().toMutableList()

        seasonAdapter.seasonList = seasonRealmList_sorted

        }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }


    fun deleteAll() {
        mRealm.executeTransaction { realm ->
            realm.where(ResultData::class.java)
                    .findAll()
                    .deleteAllFromRealm()
        }
    }

    fun readCSV(filename: String){
        try {
            val file = resources.assets.open(filename)
            val fileReader = BufferedReader(InputStreamReader(file))
            var i: Int = 0
            fileReader.forEachLine {
                if (it.isNotBlank()){
                    if (i == 0){
                        // 1行目だけ別の配列に読み取る
                        // column = it.split(",").toTypedArray()
                    } else {
                        // 2行目以降
                        var line = it.split(",").toTypedArray()

                        mRealm.executeTransaction {
                            val addData = it.createObject(ResultData::class.java, UUID.randomUUID().toString())
                            addData.type = line[0]
                            addData.rank = line[1].toInt()
                            addData.name = line[2].replace("?"," ")
                            addData.country = line[3].trim().replace("?","").replace(" ","")
                            addData.score = line[4].toDouble()
                            addData.startDate = line[5]
                            addData.competition = line[6]
                            addData.competition_short = line[7]
                            addData.url = line[8]
                            addData.season = line[9]
                            addData.gender = line[10]
                        }

                    }

                }
                i++;
            }
        } catch (e: IOException){
            // 例外処理
            print(e)
        }
    }
}