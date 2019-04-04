package com.onlab.gymapp.Training
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.today_training.view.*

class TrainingAdapter(val items : ArrayList<Training>, val context: Context)
    : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.today_training, p0, false))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.rv_durability?.text = items.get(p1).duration_in_min.toString()
        p0.rv_kcal?.text = items.get(p1).kcal.toString()
        when(items.get(p1).type) {
            Training_Type.Kardio-> p0.rv_im?.setImageResource(R.drawable.run)
            Training_Type.Nyujtas-> p0.rv_im?.setImageResource(R.drawable.yoga)
            Training_Type.Sulyzos_edzes-> p0.rv_im?.setImageResource(R.drawable.body)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val rv_durability = view.rv_dur
    val rv_kcal = view.rv_kcal
    val rv_im=view.training_im
}