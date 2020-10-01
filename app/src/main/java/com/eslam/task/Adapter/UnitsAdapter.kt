package com.eslam.task.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.eslam.task.Model.UnitModel.Data
import androidx.recyclerview.widget.RecyclerView
import com.eslam.task.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_unit.view.*

class UnitsAdapter(
    private var unitList: ArrayList<Data>, val callbackInterface: CallbackInterface
) : RecyclerView.Adapter<UnitsAdapter.UnitViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UnitViewHolder {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_unit, viewGroup, false)
        return UnitViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return unitList.size
    }

    override fun onBindViewHolder(unitViewHolder: UnitViewHolder, itemIndex: Int) {
        val unit: Data = unitList.get(itemIndex)
        setPropertiesForUnitViewHolder(unitViewHolder, unit)

        unitViewHolder.cardItem.setOnClickListener {
            callbackInterface.moveNextPage(unit)
        }
    }

    private fun setPropertiesForUnitViewHolder(unitViewHolder: UnitViewHolder, unit: Data) {
        unitViewHolder.price.text = unit.price.toString()
        unitViewHolder.currency.text = unit.currency
        unitViewHolder.rateCount.text = unit.rate_count.toString()
        unitViewHolder.rate.rating = unit.rate_count.toFloat()

        Picasso.get()
            .load(unit.contract_image)
            .fit()
            .into(unitViewHolder.unitImage)
    }


    inner class UnitViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val cardItem: LinearLayout by lazy { view.cardItem }
        val unitImage: ImageView by lazy { view.unitImage }
        val price: TextView by lazy { view.price }
        val currency: TextView by lazy { view.currency }
        val rateCount: TextView by lazy { view.rateCount }
        val rate: RatingBar by lazy { view.rating }
    }

    interface CallbackInterface {
        fun moveNextPage(data: Data)
    }
}