package br.com.cardote.fichadetreino

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.content.Intent
import br.com.cardote.fichadetreino.helpers.PreferencesHelper


class SplashScreen : AppCompatActivity() {

    //Tempo que nosso splashscreen ficará visivel
    private val SPLASH_DISPLAY_LENGTH = 3500L

    private lateinit var ivLogo : ImageView
    private lateinit var preferencesHelper : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        preferencesHelper = PreferencesHelper(this)

        ivLogo = findViewById(R.id.splash)
        carregar()
    }

    private fun carregar() {
            val anim = AnimationUtils.loadAnimation(
                this,
                R.anim.animacao_splash
            )
            anim.reset()

            val iv = findViewById(R.id.splash) as ImageView

            iv.clearAnimation()
            iv.startAnimation(anim)

            Handler().postDelayed({
                if(preferencesHelper.stayConnected){
                    callActivity(MainActivity())
                } else {
                    callActivity(LoginActivity())
                }

                this@SplashScreen.finish()
            }, SPLASH_DISPLAY_LENGTH)

    }

    private fun callActivity(activity: AppCompatActivity){
        val intent = Intent(this@SplashScreen, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }
}
