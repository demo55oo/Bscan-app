package com.example.api

import com.example.api.stats.beptransferscan
import retrofit2.Response

class repositary {
    suspend fun gettokens(n:String,type : String,query:Int,appid : Int,ii : Int,op : Int,sort:String,appkey:String): Response<beptransferscan> {
        return retrofitinstance.api.gettokens(n,type,query,appid,ii,op,sort,appkey)
    }suspend fun getbnbprice(): Response<bnbprice> {
        return retrofitinstance.api.getbnbprice()
    }
    suspend fun getrrealtokens(n:String,type : Int,query:Int,sort:String): Response<tokens> {
        return retrofitinstance.api.getrealtokens(n,type,query,sort)
    }
}
