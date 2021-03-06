package com.peaktime.dawntime.Network

import com.peaktime.dawntime.Column.ColumnListResponse
import com.peaktime.dawntime.Column.ColumnResponse
import com.peaktime.dawntime.Community.*
import com.peaktime.dawntime.Home.HomeResponse
import com.peaktime.dawntime.MyPage.*
import com.peaktime.dawntime.Shop.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by LEESANGYUN on 2018-01-06.
 */
interface NetworkService {
    //쪽지함
    @GET("message/list")
    fun getMessageBoxList(
            @Header("user_token") user_token: String)
            : Call<MessageBoxResponse>
    //찜 목록
    @GET("mypage/shopLikeList")
    fun getBasketList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>
    //내가 쓴글
    @GET("mypage/mypost")
    fun getMypostList(
            @Header("user_token") user_token: String)
            : Call<MyPageMypostResponse>
    //내가 쓴 댓글
    @GET("mypage/mycomment")
    fun getMycommentList(
            @Header("user_token") user_token: String)
            : Call<MyPageMycommentResponse>
    //스크랩
    @GET("mypage/boardScrapList")
    fun getScrapList(
            @Header("user_token") user_token: String)
            : Call<CommunityResponse>

    //커뮤니티 리스트
    @GET("board/dateList")
    fun getCommunityList(
            @Header("user_token") user_token: String)
            : Call<CommunityResponse>

    //커뮤니티 리스트(홈 인기순)
    @GET("board/bestList")
    fun getCommunityBestList(
            @Header("user_token") user_token: String)
            : Call<CommunityResponse>

    //커뮤니티 상세보기
    @GET("board/list/{board_id}")
    fun getCommunityDetail(
            @Header("user_token") user_token: String,
            @Path("board_id") board_id: Int)
            : Call<CommunityDetailResponse>

    //좋아요
    @PUT("board/like/{board_id}")
    fun communityLike(
            @Header("user_token") user_token: String,
            @Path("board_id") board_id: Int)
            : Call<CommunityLikeResponse>

    //스크랩
    @FormUrlEncoded
    @PUT("board/scrap")
    fun communityScrap(
            @Header("user_token") user_token: String,
            @Field("board_id") board_id: Int)
            : Call<CommunityLikeResponse>

    //댓글쓰기
    @FormUrlEncoded
    @POST("comment/write")
    fun replyWrite(
            @Header("user_token") user_token: String,
            @Field("board_id") board_id: Int,
            @Field("com_parent") com_parent: Int,
            @Field("com_content") com_content: String)
            : Call<ReplyWriteResponse>

    //로그인
    @FormUrlEncoded
    @POST("home/signin")
    fun signIn(
            @Field("user_email") user_email: String,
            @Field("user_uid") user_uid: String
    ): Call<SignInResponse>

    //게시글 작성
    @Multipart
    @POST("board/write")
    fun boardWrite(
            @Header("user_token") user_token: String,
            @Part("board_title") board_title: RequestBody,
            @Part("board_content") board_content: RequestBody,
            @Part("board_tag") board_tag: RequestBody,
            @Part image: ArrayList<MultipartBody.Part>?
    ): Call<CommunityWriteResponse>

    //내가 쓴글 보기
    @FormUrlEncoded
    @GET("/mypage/mypost")
    fun myWritten(
            @Header("user_token") user_token: String
    ): Call<MyWrittenResponse>
    //쇼핑몰 best 리스트 조회
    @GET("shop/best")
    fun getShopBestList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>

    //쇼핑몰 best 리스트 조회
    @GET("shop/New")
    fun getShopNewList(
            @Header("user_token") user_token: String)
            : Call<ShopBestResponse>
    //쇼핑몰 category 리스트 조회
    @GET("shop/category/{goods_category}/{order}")
    fun getShopCategoryList(
            @Header("user_token") user_token: String,
            @Path("goods_category") goods_category:String,
            @Path("order") order: Int)
            : Call<ShopBestResponse>
    //쇼핑몰 brand 리스트 조회
    @GET("shop/brand/{goods_brand}/{order}")
    fun getShopBrandList(
            @Header("user_token") user_token: String,
            @Path("goods_brand") goods_brand:String,
            @Path("order") order: Int)
            : Call<ShopBestResponse>

    //인기검색어, 최근검색어불러오기
    @GET("shop/keyword")
    fun getKeywordList(
            @Header("user_token") user_token: String)
            : Call<ShopKeywordResponse>

    //최근검색어삭제
    @DELETE("shop/keyword/delete/{user_keyword}")
    fun deleteKeyword(
            @Header("user_token") user_token: String,
            @Path("user_keyword") user_keyword: String
    ) : Call<KeywordDeleteResponse>


    //샵 상품검색
    @POST("shop/search/{order}")
    fun shopSearch(
            @Header("user_token") user_token: String,
            @Path("order") order: Int,
            @Body shopSearchRequest : ShopSearchRequest
    ) : Call<ShopBestResponse>

    //쇼핑몰 detail 상품 상세 조회
    @GET("shop/detail/{goods_id}")
    fun getShopDetailList(
            @Header("user_token") user_token:String,
            @Path("goods_id") goods_id: Int)
            : Call<ShopDetailResponse>

    //상품 찜,찜 해제
    @PUT("shop/like/{goods_id}")
    fun putShopLike(
            @Header("user_token") user_token:String,
            @Path("goods_id") goods_id: Int
    ) : Call<ShopLikeResponse>



    //홈 화면
    @GET("home")
    fun getHome(@Header("user_token") user_token: String)
            : Call<HomeResponse>

    //홈 화면 파라미터 없는 버전
    @GET("home")
    fun getHome()
            : Call<HomeResponse>

    //칼럼 리스트
    @GET("column/list")
    fun getColumnList() : Call<ColumnListResponse>

    //칼럼 검색
    @FormUrlEncoded
    @POST("column/search")
    fun getColumnSearch(@Field("column_title") column_title : String)
                : Call<ColumnListResponse>

    //칼럼 상세보기
    @GET("column/detail/{column_id}")
    fun getColumn(
        @Path("column_id") column_id : Int)
            : Call<ColumnResponse>

    //커뮤니티 태그검색
    @FormUrlEncoded
    @POST("board/tagList")
    fun tagSearch(
            @Header("user_token") user_token: String,
            @Field("tag") tag: ArrayList<String>)
            : Call<TagSearchResponse>

    //게시글을 통한 쪽지보내기
    @FormUrlEncoded
    @POST("message/boardWrite")
    fun communityMessage(
            @Header("user_token") user_token: String,
            @Field("board_id") board_id: Int,
            @Field("msg_content") msg_content: String
    ): Call<CommunityLikeResponse>

    //마이페이지에서 쪽지 상세보기
    @GET("message/room/{room_id}")
    fun getMessgaeDetailList(
            @Header("user_token") user_token: String,
            @Path("room_id") room_id: Int
    ): Call<MypageMessageDetailResponse>

    //쪽지 상세보기에서 쪽지보내기
    @FormUrlEncoded
    @POST("message/write")
    fun sendMessageDetail(
            @Header("user_token") user_token: String,
            @Field("room_id") room_id: Int,
            @Field("msg_content") msg_content: String
    ): Call<CommunityLikeResponse>//response 재사용

    //게시물 수정
    @Multipart
    @POST("board/modify")
    fun modifyDetail(
            @Header("user_token") user_token: String,
            @Part("board_title") board_title: RequestBody,
            @Part("board_content") board_content: RequestBody,
            @Part("board_tag") board_tag: RequestBody,
            @Part("board_id") board_id: RequestBody,
            @Part image: ArrayList<MultipartBody.Part>?
    ): Call<CommunityWriteResponse>//response 재사용

    //게시글 삭제
    @HTTP(method = "DELETE", path = "board/delete", hasBody = true)
    fun boardDelete(
            @Header("user_token") user_token: String,
            @Body instance: CommunityDeleteInstance)
            : Call<CommunityDeleteResponse>

    //댓글 삭제
    @HTTP(method = "DELETE", path = "comment/delete", hasBody = true)
    fun replyDelete(
            @Header("user_token") user_token: String,
            @Body instance: CommunityReplyDeleteInstance)
            : Call<CommunityDeleteResponse2>

    //게시물 검색
    @FormUrlEncoded
    @POST("board/search")
    fun communitySearch(
            @Header("user_token") user_token: String,
            @Field("search_word") search_word: ArrayList<String>
    ): Call<CommunitySearchResponse>

    //회원탈퇴
    @HTTP(method = "DELETE", path = "mypage/signout", hasBody = true)
    fun signOut(
            @Header("user_token") user_token: String)
            : Call<MyPageSignOutResponse>//response 재사용

}