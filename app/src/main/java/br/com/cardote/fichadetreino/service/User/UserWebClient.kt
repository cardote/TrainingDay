package br.com.cardote.fichadetreino.service.User

import br.com.cardote.fichadetreino.models.User
import br.com.cardote.fichadetreino.service.RetrofitInitializer
import br.com.cardote.fichadetreino.service.callback

class UserWebClient {
    fun login(user: User, success: (response: Boolean) -> Unit,
              failure: (throwable: Throwable) -> Unit) {

        val call = RetrofitInitializer().userService().login(user.user!!,user.password!!)


        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun register(user: User, success: (response: User) -> Unit,
                 failure: (throwable: Throwable) -> Unit) {

        val call = RetrofitInitializer().userService().register(user)


        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }


}