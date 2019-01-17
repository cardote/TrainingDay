package br.com.cardote.fichadetreino

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.cardote.fichadetreino.adapters.FragmentViewPagerAdapter
import br.com.cardote.fichadetreino.fragments.AddTrainingFragment
import br.com.cardote.fichadetreino.fragments.ListFragment
import br.com.cardote.fichadetreino.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: FragmentViewPagerAdapter

    private val fragments = listOf(ListFragment(), AddTrainingFragment())
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        user = intent.extras.get("user") as User


        setupToolbar()
        setupViewPager()


    }

    private fun setupToolbar(){
        val actionBar = supportActionBar

        actionBar!!.title = getString(R.string.app_name)
        actionBar.elevation = 4.0F

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    private fun setupViewPager() {
        viewPager.offscreenPageLimit = fragments.size

        adapter = FragmentViewPagerAdapter(supportFragmentManager)

        addFragments(user)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewPager.setCurrentItem(position, false)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun addFragments(user: User) {
        val args = Bundle()
        args.putSerializable("user", user)
        for (fragment in fragments) {
            fragment.arguments = args
            adapter.adicionar(fragment)
            adapter.adicionarTitulo(getString(fragment.getTitulo()))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_exit -> {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                this@MainActivity.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
