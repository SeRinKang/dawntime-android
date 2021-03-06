package com.peaktime.dawntime.Column

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.Network.ApplicationController
import com.peaktime.dawntime.Network.NetworkService
import com.peaktime.dawntime.R
import kotlinx.android.synthetic.main.fragment_column_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ColumnListFragment : Fragment(),View.OnClickListener {

    private var columnRecycler : RecyclerView? = null
    private var columnDatas : ArrayList<ColumnListData>? = null
    private var columnAdapter : ColumnListAdapter? = null

    private var networkSerivce : NetworkService? = null
    private var requestManager : RequestManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_column_list,container,false)

        networkSerivce = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)


//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu1_purple,"test1","test!"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu2_green,"test2","test@"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu3_violet,"test3","test#"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu4_blue,"test4","test$"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu1_purple,"test5","test%"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu2_green,"test6","test^"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu3_violet,"test7","test&"))
//        columnDatas!!.add(ColumnListData(R.drawable.view_peakillu4_blue,"test8","test*"))

        columnRecycler = v.findViewById(R.id.column_recycler_list)
        columnRecycler!!.layoutManager = LinearLayoutManager(activity)
        columnRecycler!!.addItemDecoration(RecyclerViewDecoration(15))
//        columnRecycler!!.adapter = columnAdapter

        getColumnList()


        v.column_list_back_btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.remove(this)
            fm.commit()
        }

        v.column_search_btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.column_list_container,ColumnSearchFragment())
            fm.addToBackStack(null)
            fm.commit()

        }

        return v
    }

    fun getColumnList(){
        val getContentList = networkSerivce!!.getColumnList()
        getContentList.enqueue(object : Callback<ColumnListResponse>{
            override fun onResponse(call: Call<ColumnListResponse>?, response: Response<ColumnListResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        columnDatas = response.body().result
                        columnAdapter = ColumnListAdapter(columnDatas,requestManager!!)
                        Log.e("aaaaaa",columnAdapter!!.itemCount.toString())
                        columnAdapter!!.setOnItemClickListener(this@ColumnListFragment)
                        columnRecycler!!.adapter = columnAdapter
                    }
                }
                else{
                    Log.i("statusaaaaa","fail")
                }
            }
            override fun onFailure(call: Call<ColumnListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }

        })
    }

    override fun onClick(v: View?) {
        var bundle = Bundle()
        var fragment = ColumnFragment()
        val fm = fragmentManager.beginTransaction()
        var position = columnRecycler!!.getChildLayoutPosition(v)
        var column_id = columnDatas!!.get(position).column_id

        bundle.putInt("column_id",column_id)
        fragment.arguments = bundle

        fm.replace(R.id.column_list_container,fragment)
        fm.addToBackStack(null)
        fm.commit()
    }



    class RecyclerViewDecoration(var divHeight : Int?) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.bottom = divHeight!!
        }
    }


}
