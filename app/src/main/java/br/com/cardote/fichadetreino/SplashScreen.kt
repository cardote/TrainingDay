package br.com.cardote.fichadetreino

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.ImageView
import android.content.Intent




class SplashScreen : AppCompatActivity() {

    //Tempo que nosso splashscreen ficará visivel
    private val SPLASH_DISPLAY_LENGTH = 3500L

    private lateinit var ivLogo : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

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
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)

            this@SplashScreen.finish()
        }, SPLASH_DISPLAY_LENGTH)

    }
}
