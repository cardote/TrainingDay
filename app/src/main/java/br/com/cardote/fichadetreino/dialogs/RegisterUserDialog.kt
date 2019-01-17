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
import br.com.cardote.fichadetreino.service.User.UserWebClient
import kotlinx.android.synthetic.main.new_training_dialog.view.*
import kotlinx.android.synthetic.main.new_user_dialog.view.*
import java.util.*

class RegisterUserDialog(private val context: Context,
                         private val viewGroup: ViewGroup) {


    fun show(created: (createdUser: User) -> Unit) {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.new_user_dialog,
                viewGroup, false)


        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.title_new_user))
            .setView(createdView)
            .setPositiveButton(context.getString(R.string.label_add)){ _, _ ->

                val username = createdView.etUsername.text.toString()
                val password = createdView.etPassword.text.toString()

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(context, context.getString(R.string.warning_add), Toast.LENGTH_SHORT).show()

                } else {
                    val user = User(username, password)

                    UserWebClient().register(user, {
                        created(it)
                    }, {
                        Log.d("PORRA", it.message)
                        Toast.makeText(context, context.getString(R.string.error_register), Toast.LENGTH_SHORT).show()
                    })
                }

            }
            .setNegativeButton(context.getString(R.string.label_cancel), null)
            .show()
    }

}