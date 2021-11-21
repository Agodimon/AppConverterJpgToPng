package com.bignerdranch.android.appconverterjpgtopng.presenter

import android.net.Uri
import android.view.View
import com.bignerdranch.android.appconverterjpgtopng.R
import com.bignerdranch.android.appconverterjpgtopng.model.converter.ConverterRepository
import com.bignerdranch.android.appconverterjpgtopng.view.ConverterView

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ConverterPresenter(
    private val repository: ConverterRepository
) : MvpPresenter<ConverterView>() {

    private val disposable = CompositeDisposable()

    fun onConvertClick() {
        viewState.pickImage()
    }

    fun convertImage(uri: Uri?) {
        uri ?: return

        disposable.add(
            repository.convert(uri)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { viewState.showSuccess(it) },
                    { viewState.showError(R.string.error) }
                )
        )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}