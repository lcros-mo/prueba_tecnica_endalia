package com.example.endalia.view.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData


import com.example.endalia.R
import com.example.endalia.view.fragment.login.LoginFragment
import com.example.endalia.view.fragment.login.LoginViewModel
import com.example.endalia.view.fragment.userList.UserListFragment
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private val button: Button? by lazy { view?.findViewById(R.id.registerButton) }
    private val emailInput: TextInputEditText? by lazy { view?.findViewById(R.id.userEmail) }
    private val passInput: TextInputEditText? by lazy { view?.findViewById(R.id.userPass) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.configViewmodel(requireContext())
        observeViewModel()
        setListeners()

    }

    private fun setListeners() {
        button?.setOnClickListener {
            lifecycleScope.launch {
                viewModel.performRegister()
            }
        }

        emailInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _userEmail
            viewModel.setUserEmail(text.toString())
        }

        passInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _userPass
            viewModel.setUserPass(text.toString())
        }
    }

//    private fun fieldsCorrect(): Boolean {
//
//        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]{8,}\$".toRegex()
//
//        if (emailInput.value == null || _userPass.value == null) {
//            this._loginSuccess.value = LoginViewModel.LoginState.BothError
//            return false
//        }
//
//        if (!passwordRegex.matches(_userPass.value!!)) {
//            this._loginSuccess.value = LoginViewModel.LoginState.PasswordError
//            return false
//        }
//
//        this._loginSuccess.value = LoginViewModel.LoginState.None
//        return true
//    }

    private fun observeViewModel() {
        viewModel.registerSuccess.observe(viewLifecycleOwner) {
            when (it) {
                RegisterViewModel.RegisterState.EmailError -> {
                    // Show error on email field
                }
                RegisterViewModel.RegisterState.PasswordError -> {
                    // Show error on pass field
                }
                RegisterViewModel.RegisterState.ConfirmPasswordError -> {
                    // Show error on confirm pass field
                }
                RegisterViewModel.RegisterState.BothError -> {
                    // Show error on both fields
                }
                RegisterViewModel.RegisterState.None -> {
                    // Hide error on all fields
                }
                RegisterViewModel.RegisterState.UserAlreadyExistsError -> {
                    // Show user already exists error
                }
                RegisterViewModel.RegisterState.Success -> {
                    // Registration successful
                    parentFragmentManager.beginTransaction().replace(R.id.main_frame, LoginFragment()).commit()
                }
            }
        }
    }

}