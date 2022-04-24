package com.example.api

import com.example.api.bnbtoken.tokenblnce
import com.example.api.stats.beptransferscan
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface mainapi {
  @GET("/api?module=account\n"+"&action=tokentx")
  suspend fun gettokens(
      @Query("contractaddress") l: String,
      @Query("address") q: String,
          @Query("page") Search: Int,
          @Query("offset") app_id:Int,
           @Query("startblock") ee: Int,
           @Query("endblock") io: Int,
           @Query("sort") ww: String,
          @Query("apikey") appkey: String): Response<beptransferscan>

  @GET("/api\n" + "?module=stats\n" + "&action=bnbprice\n" + "&apikey=RSV8C5U2MT5KX6XYGBMBVUEIP1T9DFRCXJ")
  suspend fun getbnbprice(): Response<bnbprice>
@GET("/api?module=account&action=addresstokenbalance")
suspend fun getrealtokens(
    @Query("address") q: String,
    @Query("page") Search: Int,
    @Query("offset") app_id:Int,
    @Query("apikey") d:String
):Response<tokens>
}