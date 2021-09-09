package figure.skating.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment_tab1.*

class Fragment_tab1: Fragment(){

    // RecyclerViewの表示のために必要となるAdapterクラス
    private val resultAdapter by lazy{ResultAdapter(requireContext())}

    private lateinit var mRealm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycleViewの初期化を行う
        recyclerView_tab1.apply{
            adapter = resultAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Realmの設定
        Realm.init(requireContext())
        mRealm = Realm.getDefaultInstance()

        // 実際には絞り込む必要あり（type=SP, season, comptetionで絞り込む必要あり）
        // season, competitonはどうやって渡される？（resultactivityにはあり）
        resultAdapter.resultList = mRealm.where(ResultData::class.java).findAll()

    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}