package br.com.cardote.fichadetreino

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import br.com.cardote.fichadetreino.dialogs.RegisterUserDialog
import br.com.cardote.fichadetreino.helpers.PreferencesHelper
import br.com.cardote.fichadetreino.models.User
import br.com.cardote.fichadetreino.service.User.UserWebClient
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var btnSignin: Button
    private lateinit var btnLogin: Button
    private lateinit var cbStay: CheckBox
    private lateinit var preferencesHelper : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencesHelper = PreferencesHelper(this)
        stay()
        signin()
        login()

    }

    private fun signin(){
        btnSignin = btSignin

        btnSignin.setOnClickListener {
            RegisterUserDialog(this, window.decorView as ViewGroup)
                .show {
                    Toast.makeText(this@LoginActivity, getString(R.string.success_register), Toast.LENGTH_LONG).show()

                    callMainActivity(it)

                    this@LoginActivity.finish()
                }
        }
    }

    private fun login(){
        btnLogin = btLogin

        btnLogin.setOnClickListener {
            val username = etUser.text.toString()
            val password = etPassword.text.toString()


            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, getString(R.string.warning_add), Toast.LENGTH_LONG).show()
            } else{
                val user = User(username, password)
                UserWebClient().login(user, {
                    if(it){
                        callMainActivity(user)
                        this@LoginActivity.finish()
                    } else {
                        Toast.makeText(this@LoginActivity, getString(R.string.warning_user), Toast.LENGTH_LONG).show()
                    }
                }, {
                    Toast.makeText(this@LoginActivity, getString(R.string.warning_user), Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun stay(){
        cbStay = cbStayid



        cbStay.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                preferencesHelper.stayConnected = true

            }
        }
    }

    private fun callMainActivity(user: User){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        preferencesHelper.user = user.user
        startActivity(intent)
    }
}
