package br.com.cardote.fichadetreino.service.Training

import android.util.Log
import br.com.cardote.fichadetreino.models.Training
import br.com.cardote.fichadetreino.service.RetrofitInitializer
import br.com.cardote.fichadetreino.service.callback


class TrainingWebClient {
    fun list(user : String, success: (trainings: List<Training>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {

        val call = RetrofitInitializer().trainingService().list(user)


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

    fun insert(training: Training, success: (trainings: Training) -> Unit,
               failure: (throwable: Throwable) -> Unit){

        val call = RetrofitInitializer().trainingService().insert(training)

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

    fun delete(training: Training, success: (training: Training) -> Unit,
               failure: (throwable: Throwable) -> Unit){

        val call = RetrofitInitializer().trainingService().delete(training.id!!)

        call.enqueue(callback({ response ->
            response?.body()?.let {
                Log.d("PORRA", it.toString())
                success(it)
            }
        }, { throwable ->
            throwable?.let {

                failure(it)
            }
        }))

    }

    fun update(training: Training, success: (trainings: List<Training>) -> Unit,
               failure: (throwable: Throwable) -> Unit){

        val call = RetrofitInitializer().trainingService().update(training)

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