package br.com.cardote.fichadetreino.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.cardote.fichadetreino.R

class FragmentViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){

    val meusFragments = ArrayList<Fragment>()
    val titulos = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return meusFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 ->  return titulos[position]
            else -> return titulos[position]
        }
    }

    override fun getCount(): Int {
        return meusFragments.size
    }

    fun adicionar(fragment : Fragment) {
        meusFragments.add(fragment)
    }

    fun adicionarTitulo(titulo: String) {
        titulos.add(titulo)
    }

}
