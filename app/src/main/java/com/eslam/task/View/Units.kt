package com.eslam.task.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eslam.task.Adapter.UnitsAdapter
import com.eslam.task.Model.UnitModel.Data
import com.eslam.task.Model.UnitModel.UnitModel
import com.eslam.task.Network.Apis
import com.eslam.task.Network.Service
import com.eslam.task.R
import com.eslam.task.Utils.InternetConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_units.*
import retrofit2.Retrofit

import kotlin.collections.ArrayList

class Units : AppCompatActivity() , UnitsAdapter.CallbackInterface  {

    private lateinit var unitAdapter: UnitsAdapter
    private lateinit var unitList: ArrayList<Data>
    private var recyleUnits: RecyclerView? = null
    private var layoutProgress: LinearLayout? = null
    private var layoutView: LinearLayout? = null

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_units)

        initView()

        if (InternetConnection().isNetworkAvailable(this)) {
            getUnits()
        } else {
            progress!!.visibility = View.GONE
            Toast.makeText(this, R.string.checkInternetConnection, Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        recyleUnits = this.recycleUnits
        layoutProgress = this.progress
        layoutView = this.view
    }

    private fun getUnits() {
        val retrofit: Retrofit = Service().generateRetrofitBuilder()
        val api: Apis = retrofit.create(Apis::class.java)

        //CompositeDisposable is needed to avoid memory leaks
        compositeDisposable = CompositeDisposable()
        //Add all RxJava disposables to a CompositeDisposable//
        compositeDisposable.add(
            api.getData("1", "10")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(unitModel: UnitModel) {
        if (unitModel.status == 200) {
            unitList = unitModel.response.data

            // set View
            unitAdapter = UnitsAdapter(unitList,this)
            recyleUnits!!.setHasFixedSize(true)
            recyleUnits!!.layoutManager = GridLayoutManager(this, 2)
            recyleUnits!!.itemAnimator = DefaultItemAnimator()
            recyleUnits!!.adapter = unitAdapter

            progress!!.visibility = View.GONE
            view!!.visibility = View.VISIBLE
        } else {
            Toast.makeText(this, unitModel.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    override fun moveNextPage(data: Data) {
        val intent = Intent(this, UnitDetails::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }
}