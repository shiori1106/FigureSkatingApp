package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private val viewPagerAdapter by lazy { ViewPageAdapter(this) }

    // SeasonDetailActivityからCOMPETITION、SEASONをそれぞれ受け取るための変数を初期化
    var competitonFromSeasonDetail = ""
    var seasonFromSeasonDetail = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // レイアウトのtoolbarをtoolbar要素を取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // アクションバーにツールバーをセット
        setSupportActionBar(toolbar)

        // アクションバーに戻るボタンをつける
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)


        // SeasonDetailActivityからCOMPETITION、SEASONをそれぞれ受け取る
        competitonFromSeasonDetail = intent.getStringExtra("COMPETITION")!!
        seasonFromSeasonDetail = intent.getStringExtra("SEASON")!!

        // タイトルバーの設定
        //title = competitonFromSeasonDetail + " / " + seasonFromSeasonDetail
        supportActionBar?.setTitle(competitonFromSeasonDetail + " / " + seasonFromSeasonDetail)

        // ViewPager2の初期化
        viewPager2.apply {
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 横にスライド
            offscreenPageLimit = viewPagerAdapter.itemCount // 画面数
        }

        // TabLayoutの初期化
        // TabLayoutとViewPager2を紐づける
        // TabLayoutのTextを指定する
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    //tab.setCustomView(R.layout.tab_1)
                    tab.setText(R.string.tab1_title)
                    tab.setIcon(R.drawable.ic_outline_circle_24)
                }

                1 -> {
                    //tab.setCustomView(R.layout.tab_2)
                    tab.setText(R.string.tab2_title)
                    tab.setIcon(R.drawable.ic_outline_circle_24)
                }

                2 -> {
                    //tab.setCustomView(R.layout.tab_3)
                    tab.setText(R.string.tab3_title)
                    tab.setIcon(R.drawable.ic_outline_circle_24)
                }

                3 -> {
                    //tab.setCustomView(R.layout.tab_4)
                    tab.setText(R.string.tab4_title)
                    tab.setIcon(R.drawable.ic_baseline_circle_24)
                }

                4 -> {
                    //tab.setCustomView(R.layout.tab_5)
                    tab.setText(R.string.tab5_title)
                    tab.setIcon(R.drawable.ic_baseline_circle_24)
                }

                5 -> {
                    //tab.setCustomView(R.layout.tab_6)
                    tab.setText(R.string.tab6_title)
                    tab.setIcon(R.drawable.ic_baseline_circle_24)
                }
            }
        }.attach()

        // 上タブを選択したときの線の色を変える
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {

                tabLayout.setSelectedTabIndicatorColor(
                        ContextCompat.getColor(
                                applicationContext,
                                when (tab?.position) {
                                    0, 1, 2 -> R.color.fresh
                                    else -> R.color.sunshine
                                }
                        )
                )
            }
        })
    }

    // 戻るボタンを押すと、アクティビティを終了させることで前の画面に戻る
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}