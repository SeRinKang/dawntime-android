package com.peaktime.dawntime

import com.peaktime.dawntime.MyPage.MessageBoxResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
interface NetworkService {
    //쪽지함
    @GET("message/list/{user_email}")
    fun getMessageBoxList(
            @Path("user_email") user_email: String)
            : Call<MessageBoxResponse>

    //커뮤니티 리스트
    @FormUrlEncoded
    @POST("board/dateList")
    fun getCommunityList(@Field("user_id") user_id: Int)
            : Call<CommunityResponse>

    //커뮤니티 상세보기

    @POST("board/list/{board_id}")
    fun getCommunityDetail(
            @Path("board_id") board_id: Int,
            @Body instance: CommunityDetailInstance)
            : Call<CommunityDetailResponse>

}