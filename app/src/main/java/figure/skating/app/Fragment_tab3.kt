package figure.skating.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_tab2.*
import kotlinx.android.synthetic.main.fragment_tab2.recyclerView_tab2
import kotlinx.android.synthetic.main.fragment_tab3.*

class Fragment_tab3: Fragment() {

    lateinit var resultActivity: ResultActivity

    // RecyclerViewの表示のために必要となるAdapterクラス
    private val resultFinalAdapter by lazy{ResultFinalAdapter(requireContext())}

    private lateinit var mRealm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // recycleViewの初期化を行う
        recyclerView_tab3.apply{
            adapter = resultFinalAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Realmの設定
        Realm.init(requireContext())
        mRealm = Realm.getDefaultInstance()


        // ResultActivityで受け取った引数を使用する
        resultActivity = activity as ResultActivity

        val dataRealmResults = mRealm.where(ResultData::class.java)
            .equalTo(ResultData::gender.name, "M")
            .equalTo(ResultData::type.name,"FINAL")
            .equalTo(ResultData::season.name, resultActivity.seasonFromSeasonDetail)
            .equalTo(ResultData::competition_short.name, resultActivity.competitonFromSeasonDetail)
            .findAll()
            .sort("rank", Sort.ASCENDING)

        // 上記の結果をresultListにセットする
        resultFinalAdapter.resultFinalList = mRealm.copyFromRealm(dataRealmResults)

    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

}