package br.com.cardote.fichadetreino.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import br.com.cardote.fichadetreino.R
import br.com.cardote.fichadetreino.adapters.TrainingAdapter
import br.com.cardote.fichadetreino.dialogs.AddTrainingDialog
import br.com.cardote.fichadetreino.dialogs.DeleteTrainingDialog
import br.com.cardote.fichadetreino.dialogs.EditTrainingDialog
import br.com.cardote.fichadetreino.models.Training
import br.com.cardote.fichadetreino.models.User
import br.com.cardote.fichadetreino.service.Training.TrainingWebClient
import kotlinx.android.synthetic.main.fragment_list.view.*


/**
 * A simple [BaseFragment] subclass.
 *
 */
class ListFragment : BaseFragment() {

    lateinit var v: View
    private lateinit var mAdapter: TrainingAdapter
    private lateinit var btnAdd : FloatingActionButton
    private val trainings: MutableList<Training> = mutableListOf()
    private lateinit var user : User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.getSerializable("user")?.let {
            user = it as User
        }

        v = inflater.inflate(R.layout.fragment_list, container, false)

        TrainingWebClient().list(user.user!!, {
            trainings.addAll(it)
            configureList()

        }, {
            Toast.makeText(this.context, getString(R.string.error_list), Toast.LENGTH_LONG).show()
        })

        btnAdd = v.findViewById(R.id.btnAdd)




        btnAdd.setOnClickListener {
            AddTrainingDialog(this.context!!, activity?.window?.decorView as ViewGroup, user)
                .show {
                    trainings.add(it)
                    configureList()
                }
        }


        return v
    }



    private fun configureList(){
        mAdapter = TrainingAdapter(trainings,
            { training: Training ->  deleteItemClicked(training)},
            { training: Training ->  editItemClicked(training)})
        v.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        v.recyclerView.adapter = mAdapter




    }

    private fun deleteItemClicked(trainingData: Training) {

        DeleteTrainingDialog(this.context!!, activity?.window?.decorView as ViewGroup, trainingData.id!!)
            .show {
                trainings.remove(it)
                configureList()
                Toast.makeText(context, getString(R.string.success_delete), Toast.LENGTH_SHORT).show()

            }
    }

    private fun editItemClicked(trainingData: Training) {

        EditTrainingDialog(this.context!!, activity?.window?.decorView as ViewGroup, trainingData)
            .show {
                trainings.clear()
                trainings.addAll(it)
                configureList()
                Toast.makeText(context, getString(R.string.success_edit), Toast.LENGTH_SHORT).show()

            }
    }




    override fun getTitulo() : Int {
        return R.string.title_list_fragment
    }


}

