package br.com.cardote.fichadetreino.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cardote.fichadetreino.R
import br.com.cardote.fichadetreino.models.Training
import kotlinx.android.synthetic.main.item_list.view.*

class TrainingAdapter(private val trainingData: List<Training>,
                      private val deleteClickListener: (Training) -> Unit,
                      private val editClickListener: (Training) -> Unit): RecyclerView.Adapter<TrainingAdapter.ViewHolder>(){



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(training: Training,
                 deleteClickListener: (Training) -> Unit,
                 editClickListener: (Training) -> Unit){
            itemView.tvTitle.text = training.muscleGroup
            itemView.tvDay.text = training.day
            itemView.tvId.text = training.id
            itemView.tvUser.text = training.user

            itemView.ibDelete.setOnClickListener{deleteClickListener(training)}
            itemView.ibEdit.setOnClickListener {editClickListener(training)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int{
        return trainingData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trainingData[position], deleteClickListener, editClickListener)
    }

}