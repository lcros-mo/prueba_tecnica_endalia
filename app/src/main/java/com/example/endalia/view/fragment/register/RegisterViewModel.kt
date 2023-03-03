package com.example.endalia.view.fragment.register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.endalia.R
import com.example.endalia.controllers.AppDatabase
import com.example.endalia.models.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    private lateinit var _database: AppDatabase
    private val _userEmail = MutableLiveData<String>()
    private val _userPass = MutableLiveData<String>()
    private val _registerSuccess = MutableLiveData<RegisterState>()
    private val errorMessage = MutableLiveData<String>()



    enum class RegisterState {
        EmailError,
        PasswordError,
        ConfirmPasswordError,
        BothError,
        None,
        Success,
        UserAlreadyExistsError
    }

    val registerSuccess: LiveData<RegisterState> = _registerSuccess

    fun configViewmodel(context: Context) {
        _database = setupDb(context)
    }

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserPass(password: String) {
        _userPass.value = password
    }

    suspend fun performRegister() {
        var empleado: Employee? = null
        if (!fieldsCorrect()) {
            return
        }
        runBlocking {
            withContext(Dispatchers.IO) {
                empleado = _database.empleadoDao().findEmployeeByEmail(_userEmail.value!!)
            }
        }
        if (empleado != null) {
            _registerSuccess.value = RegisterState.UserAlreadyExistsError
            return
        }
        val newEmploy = Employee(
            email = _userEmail.value!!,
            password = _userPass.value!!,
            name = "Charles",
            surname = "Smith",
            phone = "666666666",
            job = "Intern",
            photo = R.drawable.employee_portrait_1
        )
        runBlocking {
            withContext(Dispatchers.IO) {
                _database.empleadoDao().insert(newEmploy)
            }
        }
        withContext(Dispatchers.Main) {
            _registerSuccess.value = RegisterState.Success
        }
    }

    private fun setupDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "endalia-db"
        ).build()
    }

    private fun fieldsCorrect(): Boolean {
        if (_userEmail.value == null || _userPass.value == null) {
            this._registerSuccess.value = RegisterState.BothError
            return false
        }
        if (!isValidEmail(_userEmail.value!!)) {
            this._registerSuccess.value = RegisterState.EmailError
            return false
        }
        if (!isValidPassword(_userPass.value!!)) {
            this._registerSuccess.value = RegisterState.PasswordError
            return false
        }
        this._registerSuccess.value = RegisterState.Success
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()
//        if (!isValidPassword(_userPass)) {
//            errorMessage.postValue("Contraseña demasiado corta")
//        }
        return passwordPattern.matches(password)
    }
}