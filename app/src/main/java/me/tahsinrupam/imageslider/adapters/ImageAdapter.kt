package me.tahsinrupam.imageslider.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_image_page.view.*
import me.tahsinrupam.imageslider.R
import me.tahsinrupam.imageslider.models.PhotoSlide

/**
 * Created by Tahsin on 15-Oct-17.
 */


class ImageAdapter (private var mContext: Context, private val photoSlide : MutableList<PhotoSlide>?): PagerAdapter() {


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return photoSlide!!.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {

        val view : View = LayoutInflater.from(mContext).inflate(R.layout.single_image_page, container, false)
        Picasso.with(mContext)
                .load(photoSlide?.get(position)?.imageUrl)
                .into(view.imageView)
        view.textViewName.text = photoSlide?.get(position)?.name
        view.textViewLocation.text = photoSlide?.get(position)?.location
        view.textViewBio.text = photoSlide?.get(position)?.bio
        container?.addView(view, 0)
        return view
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view?.equals(`object`)!!
    }




}