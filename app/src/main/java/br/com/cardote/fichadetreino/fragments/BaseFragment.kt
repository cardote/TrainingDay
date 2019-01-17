package br.com.cardote.fichadetreino.fragments

import android.support.annotation.StringRes
import android.support.v4.app.Fragment

open abstract class BaseFragment : Fragment() {
    @StringRes
    abstract fun getTitulo(): Int
}