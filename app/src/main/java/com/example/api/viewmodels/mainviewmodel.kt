package com.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.bnbprice
import com.example.api.repositary
import com.example.api.stats.beptransferscan
import com.example.api.tokens
import kotlinx.coroutines.launch
import retrofit2.Response

class mainviewmodel(private val repository: repositary):ViewModel()
{
    val myresponce :MutableLiveData<Response<beptransferscan>> = MutableLiveData()
    val myresponce2 :MutableLiveData<Response<bnbprice>> = MutableLiveData()
    val myresponce3 :MutableLiveData<Response<tokens>> = MutableLiveData()
    fun gettokens(n:String,type : String,query:Int,appid : Int,ii : Int,UY : Int,sort:String,appkey:String){
        viewModelScope.launch {
            val responce = repository.gettokens(n,type,query,appid,ii,UY,sort,appkey)
               myresponce.value = responce
        }
    }
  fun getpost2(){
        viewModelScope.launch{
            val response:Response<bnbprice> = repository.getbnbprice()
            myresponce2.value = response
        }
    }
    fun getrealtokens(n:String,query:Int,appid : Int,appkey:String){
        viewModelScope.launch {
            val responce = repository.getrrealtokens(n,query,appid,appkey)
            myresponce3.value = responce
        }
    }

}