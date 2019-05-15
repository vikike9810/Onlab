package com.onlab.gymapp.Gallery


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.activity_gallery.*
import android.widget.ImageView
import android.widget.AdapterView
import android.widget.Toast

import com.google.firebase.storage.FirebaseStorage



class GalleryActivity : AppCompatActivity() {

    var selectedImage: ImageView? = null
    lateinit var galladapter:GalleryAdapter
    var storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference
    var images=ArrayList<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        getImages()
      // galladapter.getImages(this)
    }
    fun getImageview():ImageView{
        return imageView
    }

    fun getImages(){
        var count= listOf<Int>(1,2,3,4,5,6,7,8,9,10,11)
        for(i in count){

            var pathReference = storage.getReferenceFromUrl("gs://onlab-gymapp.appspot.com/gallery/"+i+".jpg")
            pathReference.getBytes(1024 * 1024).addOnSuccessListener {
                var bitimage= BitmapFactory.decodeByteArray(it, 0, it.size)
                images.add(bitimage)

                if(i==11) {
                    image_progressBar.visibility=View.GONE
                    imageLay.visibility=View.VISIBLE
                    galladapter=GalleryAdapter(this,images)
                    gallery.setSpacing(5)
                    selectedImage = imageView
                    gallery.adapter = galladapter
                    selectedImage!!.setImageBitmap(galladapter.mImageIds[4])
                    gallery.setSelection(4)

                    gallery.onItemClickListener = object : AdapterView.OnItemClickListener {
                        override fun onItemClick(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                            // show the selected Image
                            selectedImage!!.setImageBitmap(galladapter.mImageIds[position])
                        }
                    }
                }

            }.addOnFailureListener {
                Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
            }

        }

    }

    fun nextimage(v:View){
    var num=gallery.selectedItemPosition
        if( num<10) {
            selectedImage!!.setImageBitmap(galladapter.mImageIds[num + 1])
            gallery.setSelection(num + 1)
        }
    }

    fun previmage(v:View){
        var num=gallery.selectedItemPosition
        if(num>0) {
            selectedImage!!.setImageBitmap(galladapter.mImageIds[num -1])
            gallery.setSelection(num -1)
        }
    }


}
