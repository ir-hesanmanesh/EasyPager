package ir.viewpager.pager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainPager : AppCompatActivity() {
    val itemslider = arrayOf(1, 2, 3, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pager)
       
    }
}
