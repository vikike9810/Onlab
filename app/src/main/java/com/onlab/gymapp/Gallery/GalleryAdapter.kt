package com.onlab.gymapp.Gallery

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.onlab.gymapp.R
import android.widget.Gallery

import com.google.firebase.storage.FirebaseStorage
import android.graphics.Bitmap



class GalleryAdapter : BaseAdapter {
    lateinit var timage:ImageView
    var mImageIds = ArrayList<Bitmap>()

   lateinit var context:Context
    var storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference

    constructor(context: Context, items: ArrayList<Bitmap>){
        this.context=context
        mImageIds.addAll(items)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val i = ImageView(context)

        i.setImageBitmap(mImageIds[position])
        //i.setImageResource(mImageIds[position].generationId)
        i.layoutParams = Gallery.LayoutParams(200, 200)

        i.scaleType = ImageView.ScaleType.FIT_XY

        return i
    }

    override fun getCount(): Int {
        return mImageIds.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


  /*  fun getImages(act: GalleryActivity){
        var count= listOf<Int>(1,2,3,4,5,6,7,8,9,10,11)
        for(i in count){

            var pathReference = storage.getReferenceFromUrl("gs://onlab-gymapp.appspot.com/gallery/"+i+".jpg")
            pathReference.getBytes(1024 * 1024).addOnSuccessListener {
                var bitimage= BitmapFactory.decodeByteArray(it, 0, it.size)
                mImageIds.add(bitimage)
                if(i==1){
                    act.getImageview().setImageBitmap(mImageIds[0])
                }
            }.addOnFailureListener {
                Toast.makeText(act,it.message, Toast.LENGTH_LONG).show()
            }

        }

    }*/

}