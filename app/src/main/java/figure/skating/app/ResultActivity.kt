package figure.skating.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private val viewPagerAdapter by lazy {ViewPageAdapter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // SeasonDetailActivityからCOMPETITION、SEASONをそれぞれ受け取る
        val competitonFromSeasonDetail = intent.getStringExtra("COMPETITION")
        val seasonFromSeasonDetail = intent.getStringExtra("SEASON")

        // タイトルバーの設定
        title = competitonFromSeasonDetail  + " / " + seasonFromSeasonDetail

        // ViewPager2の初期化
        viewPager2.apply{
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL // 横にスライド
            offscreenPageLimit = viewPagerAdapter.itemCount // 画面数
        }

        // TabLayoutの初期化
        // TabLayoutとViewPager2を紐づける
        // TabLayoutのTextを指定する
        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            when(position){
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
    }
}