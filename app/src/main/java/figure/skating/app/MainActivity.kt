package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 下タブの設定
        val navController = findNavController(R.id.bottom_navigation_view_fragment)
        setupWithNavController(bottom_navigation_view, navController)

        // Realmの設定
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()

        // Realmのデータを全て消す
        deleteAll()

        // CSVのデータをRealmに格納
        readCSV("resultData.csv")

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
                            addData.name = line[2]
                            addData.country = line[3]
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