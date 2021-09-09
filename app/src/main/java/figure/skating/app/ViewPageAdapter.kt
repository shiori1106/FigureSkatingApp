package figure.skating.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    val fragments = listOf(Fragment_tab1(),Fragment_tab2(), Fragment_tab3(),
                            Fragment_tab4(),Fragment_tab5(), Fragment_tab6())

    // ViewPager2が何ページあるか
    override fun getItemCount(): Int {
        return fragments.size
    }

    // 引数で受け取ったpositionのページのFragmentを返す
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}