package ir.viewpager.easypager

import android.os.Bundle
import android.text.style.EasyEditSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_pager.*


class MainPager : AppCompatActivity() {
    val itemslider = arrayOf(1, 2, 3, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pager)

    }
}