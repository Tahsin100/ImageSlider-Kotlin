package me.tahsinrupam.imageslider.adapters

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import me.tahsinrupam.imageslider.R

/**
 * Created by Tahsin on 15-Oct-17.
 */


class ImageAdapter (imageUrls : MutableList<String>?): PagerAdapter() {


    override fun instantiateItem(container: ViewGroup?, position: Int): Any {

        val myImageLayout : View = inflater.inflate(R.layout.single_image_page, container, false)
        val myImage = myImageLayout
                .findViewById(R.id.image) as ImageView
        myImage.setImageResource(images.get(position))
        container?.addView(myImageLayout, 0)
        return myImageLayout
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }
}