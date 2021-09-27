package figure.skating.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment4.*

class Fragment4: Fragment()  {

    private lateinit var mRealm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Realmの設定
        Realm.init(requireContext())
        mRealm = Realm.getDefaultInstance()

        // 選手名を全てskaterRealmListに格納
        val skaterRealmResults = mRealm.where(ResultData::class.java)
            .findAll()
        val skaterRealmList = mutableListOf<String>()
        for (i in 0..skaterRealmResults.size-1){

            // 両側の空白を削除
            var addName = skaterRealmResults[i]!!.name.trim().replace(" "," ")

            //2文字目が大文字の場合は、姓名を逆にする
            if (addName[1].isUpperCase()){
                val addNameList = addName.split(" ")
                addName = addName.replace(addNameList[0],"").trim() + " " + addNameList[0]
            }

            // FSRとOARはRUSに変換
            var addCountry = skaterRealmResults[i]!!.country
            if ((addCountry == "FSR")||(addCountry == "OAR")){
                addCountry = "RUS"
            }
            skaterRealmList.add(addName + "  (" + addCountry + ")")
        }

        // 重複を削除し、並び替え
        val list = skaterRealmList.distinct().sorted().toMutableList()

        val skaterAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        listView.adapter = skaterAdapter

        val filter = arrayListOf<String>()
        val skateResult = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, filter)

        // リストビューの項目をタップしたときの処理
        listView.setOnItemClickListener { parent, view, position, id ->
            val itemTextView: TextView = view.findViewById(android.R.id.text1)
            //Toast.makeText(requireContext(),itemTextView.toString(), Toast.LENGTH_SHORT).show()

            // 名前と国名を分離する
            val positionIdx = itemTextView.text.indexOf("(")

            // 名前のみ
            val itemTextView_name = itemTextView.text.substring(0, positionIdx).trim()

            // 国のみ
            val itemTextView_country = itemTextView.text.toString().replace(itemTextView_name, "").trim()

            // ResultSkaterActivityに遷移
            val intent = Intent(context, ResultSkaterActivity::class.java)
            intent.putExtra("NAME", itemTextView_name)
            intent.putExtra("COUNTRY", itemTextView_country)
            startActivity(intent)

            Toast.makeText(requireContext(),itemTextView_name + " " + itemTextView_country, Toast.LENGTH_SHORT).show()
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            // 検索文字が変更されたときのリスナー
            override fun onQueryTextChange(newText: String?): Boolean {

                // 検索時には大文字小文字を区別しない
                val regex = Regex(newText.toString(), RegexOption.IGNORE_CASE)
                skateResult.clear()

                // 部分一致しているものを探す
                for (item in list.indices){
                    if (regex.containsMatchIn(list[item])){
                        filter.add(list[item])
                    }
                }
                listView.adapter = skateResult
                skaterAdapter.notifyDataSetChanged()
                return true
            }

            // 検索実行のときのリスナー
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }


}