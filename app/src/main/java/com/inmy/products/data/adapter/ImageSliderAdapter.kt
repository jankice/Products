package com.inmy.products.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.inmy.products.R
import kotlinx.android.synthetic.main.item_image_slider.view.*


class ImageSliderAdapter(private val context: Context, productImageUrls: ArrayList<String>) : PagerAdapter() {


    private var inflater: LayoutInflater? = null
    private val images = productImageUrls
  //  private val images = arrayOf(R.drawable.badge_background, R.drawable.badge_background)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {

        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.item_image_slider, null)
       // view.imageView_slide.setImageResource(images[position])
        Glide.with(context).load(images.get(position)).into(view.imageView_slide)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}