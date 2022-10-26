package com.example.gadgetsonlinestore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.gadgetsonlinestore.data.IntroSlide
import kotlinx.android.synthetic.main.fragment_landing_page_screen.*


class LandingPageScreen : Fragment() {

    private val landingPageAdapter = LandingPageAdapter(
        listOf(
            IntroSlide(
                "LAPTOPS",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                R.drawable.image_landing_page1
            ),
            IntroSlide(
                "SMARTPHONES",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                R.drawable.image_landing_page2
            ),
            IntroSlide(
                "MONITORS",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                R.drawable.image_landing_page3
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing_page_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        landingPageScreenViewPager.adapter = landingPageAdapter

        //Page Indicator
        pageIndicator()
        pageCurrentIndicator(0)
        landingPageScreenViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                pageCurrentIndicator(position)
            }
        })

        //Button to navigate on next page until Login Activity
        nextBtn.setOnClickListener{
            if (landingPageScreenViewPager.currentItem + 1 < landingPageAdapter.itemCount) {
                landingPageScreenViewPager.currentItem += 1
            } else {
                Intent(context?.applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        //Button to navigate on Login Activity
        skipBtn.setOnClickListener {
            Intent(context?.applicationContext, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun pageIndicator() {
        val indicators = arrayOfNulls<ImageView>(landingPageAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(context?.applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        context.applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }

    }

    private fun pageCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it.applicationContext,
                            R.drawable.indicator_active
                        )
                    }
                )
            } else {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it.applicationContext,
                            R.drawable.indicator_inactive
                        )
                    }
                )
            }
        }
    }
}