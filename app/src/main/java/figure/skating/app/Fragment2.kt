package figure.skating.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment2.*

class Fragment2: Fragment() {

    // RecyclerViewの表示のために必要となるAdapterクラス
    private val competitionAdapter by lazy{CompetitionAdapter(requireContext())}
    private lateinit var mRealm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycleViewの初期化を行う
        recyclerView2.apply{
            adapter = competitionAdapter

            // 一列ずつ表示
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Realmの設定
        Realm.init(requireContext())
        mRealm = Realm.getDefaultInstance()

        // 表示させるリストを作成
        val competitionList = mutableListOf("Olympic Games","World Championships", "World Junior Championships", "Grand Prix Final", "Junior Grand Prix Final", "European Championships", "Four Continents Championships")
        competitionAdapter.competitionList = competitionList

    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}