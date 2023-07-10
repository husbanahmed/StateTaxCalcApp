package com.example.statetaxcalcapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LocalFragmentViewModel : ViewModel() {
    var zipCodeLiveData: MutableLiveData<List<ZipCode>> = MutableLiveData()

    fun fetchData(){
        viewModelScope.launch {

            try {
                val response = RetrofitInstance.api.
                getZipCode("V9y59G2eSHy6YabFQiCM7A==E9tN00VLUkbrSDMP","91303")

                if(response.isSuccessful){
                    zipCodeLiveData.value = response.body()
                    Log.d(TAG,"This is the zipocde tax")
                }
            }catch (e: IOException){
                Log.d(TAG,"IOException, you might not have internet connection")

            }catch (e: HttpException){
                Log.d(TAG,"HttpException,unexpected launch")
            }

        }
    }
}

