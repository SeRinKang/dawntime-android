package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by HYEON on 2018-01-05.
 */

class CommunityReplyAdapter(var dataList : ArrayList<CommunityReplyData>?) : RecyclerView.Adapter<CommunityReplyViewholder>() {
    private var getItemClick : View.OnLongClickListener? = null
//    멤버변수 private 이 클래스 내에서만 사용

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommunityReplyViewholder {
        //    어떤 뷰 홀더를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.community_reply_data, parent, false)
        mainView.setOnLongClickListener(getItemClick)
        return CommunityReplyViewholder(mainView)
    }
    //뷰의내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: CommunityReplyViewholder?, position: Int) {
        holder!!.replyTitle.setText(dataList!!.get(position).replyTitle)
        holder!!.replyDate.setText(dataList!!.get(position).replyDate)
        holder!!.replyContents.setText(dataList!!.get(position).replyContents)
    }

    override fun getItemCount(): Int = dataList!!.size
//    몇개인지 리턴 별다른 함수 없이 리턴 있을때랑 똑같은 역할 (즉, return dataList!!.size)

    fun setOnItemClickListener(l: View.OnLongClickListener) {
        getItemClick = l

    }
}