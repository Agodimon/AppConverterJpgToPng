package com.bignerdranch.android.appconverterjpgtopng.presenter

import android.net.Uri
import android.view.View
import com.bignerdranch.android.appconverterjpgtopng.model.converter.ConverterRepository
import com.bignerdranch.android.appconverterjpgtopng.view.ConverterView

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class ConverterPresenter(
    private val repository: ConverterRepository,
) : MvpPresenter<ConverterView>() {
    private val disposable = CompositeDisposable()

    fun selectImage(view: View) {
        viewState.pickImage()
    }

    fun convertImage(uri: Uri?, scheduler: Scheduler) {
        disposable.add(
            uri?.let {
                repository.convert(it)
                    .subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        viewState::showSuccess,
                        viewState::showError
                    )
            }
        )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}