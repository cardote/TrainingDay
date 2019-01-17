package br.com.cardote.fichadetreino.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import br.com.cardote.fichadetreino.R
import br.com.cardote.fichadetreino.adapters.CustomSpinnerAdapter
import br.com.cardote.fichadetreino.models.Training
import br.com.cardote.fichadetreino.service.Training.TrainingWebClient
import kotlinx.android.synthetic.main.new_training_dialog.view.*
import java.util.*

class DeleteTrainingDialog(private val context: Context,
                           private val viewGroup: ViewGroup,
                           private val id: String) {


    fun show(deleted: (training: Training) -> Unit) {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.delete_training_dialog,
                viewGroup, false)


        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.title_delete_training))
            .setView(createdView)
            .setPositiveButton(context.getString(R.string.label_delete)){ _, _ ->


                    val training = Training(id, null, null, null)

                    TrainingWebClient().delete(training, {
                        deleted(it)
                    }, {
                        Toast.makeText(context, context.getString(R.string.error_delete), Toast.LENGTH_SHORT).show()
                    })
            }
            .setNegativeButton(context.getString(R.string.label_cancel), null)
            .show()
    }

}