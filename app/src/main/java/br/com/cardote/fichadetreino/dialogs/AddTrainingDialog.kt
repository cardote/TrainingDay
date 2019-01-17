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
import br.com.cardote.fichadetreino.models.User
import br.com.cardote.fichadetreino.service.Training.TrainingWebClient
import kotlinx.android.synthetic.main.new_training_dialog.view.*
import java.util.*

class AddTrainingDialog(private val context: Context,
                        private val viewGroup: ViewGroup,
                        private val user: User) {

    private lateinit var spDay: Spinner
    private lateinit var selectedDay: String

    fun show(created: (createdTrainings: Training) -> Unit) {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.new_training_dialog,
                viewGroup, false)

        spDay = createdView.spDay

        initGeneroSpinner(spDay)

        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.title_new_training))
            .setView(createdView)
            .setPositiveButton(context.getString(R.string.label_add)){ _, _ ->

                val muscleGroup = createdView.etMuscleGroup.text.toString()
                //val description = createdView.spDay.selectedItem.toString()

                if(muscleGroup.isEmpty()){
                    Toast.makeText(context, context.getString(R.string.warning_add), Toast.LENGTH_SHORT).show()

                } else {
                    val training = Training(null, user.user, muscleGroup, selectedDay)

                    TrainingWebClient().insert(training, {
                        created(it)
                    }, {
                        Toast.makeText(context, context.getString(R.string.error_save), Toast.LENGTH_SHORT).show()
                    })
                }

            }
            .setNegativeButton(context.getString(R.string.label_cancel), null)
            .show()
    }

    private fun initGeneroSpinner(spinner: Spinner) {

        val days = ArrayList<String>(Arrays.asList(*context.resources.getStringArray(R.array.days)))
        val customSpinnerAdapter = CustomSpinnerAdapter(context, days)
        spinner.adapter = customSpinnerAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedDay = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}