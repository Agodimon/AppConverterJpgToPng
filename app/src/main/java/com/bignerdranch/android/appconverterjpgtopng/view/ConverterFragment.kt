package com.bignerdranch.android.appconverterjpgtopng.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.appconverterjpgtopng.R
import com.bignerdranch.android.appconverterjpgtopng.databinding.FragmentConverterBinding
import com.bignerdranch.android.appconverterjpgtopng.model.converter.ConverterRepositoryImpl
import com.bignerdranch.android.appconverterjpgtopng.presenter.ConverterPresenter
import com.bignerdranch.android.appconverterjpgtopng.unity.toast
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ConverterFragment : MvpAppCompatFragment(R.layout.fragment_converter), ConverterView {

    private val binding by viewBinding(FragmentConverterBinding::bind)

    private val presenter: ConverterPresenter by moxyPresenter {
        val repository = ConverterRepositoryImpl(requireContext())
        ConverterPresenter(repository = repository)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Uri? = result.data?.data
                presenter.convertImage(data)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.converterButton.setOnClickListener { presenter.onConvertClick() }
    }

    override fun showSuccess(path: String) = toast(path)

    override fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    override fun showError(msg: Int) = toast(msg)

    companion object {
        fun newInstance(): Fragment = ConverterFragment()
    }
}