package com.friendship.bhaibhaiclinic.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.base.Constant.ACTIVE
import com.friendship.bhaibhaiclinic.databinding.LayoutProviderListItemBinding
import com.friendship.bhaibhaiclinic.model.ProviderItem
import com.friendship.bhaibhaiclinic.util.DiffCallback
import com.friendship.bhaibhaiclinic.util.Helper


class ProviderItemAdapter(
    private val mContext: Context,
    private var prvItem: List<ProviderItem>,
    private val listener: OnCardClickListener
) : RecyclerView.Adapter<ProviderItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutProviderListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutProviderListItemBinding.inflate(
                LayoutInflater.from(mContext),
                parent,
                false
            )
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<ProviderItem>) {
        val diffCallback = DiffCallback(prvItem, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        prvItem = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemInfo = prvItem[position]

        holder.binding.apply {

            itemInfo.apply {
                txtName.text = name
                txtEmail.text = email
                txtGender.text = gender
                txtFirstLetter.text = name?.first().toString()

                /**
                 * customize views..
                 */
                if (status == ACTIVE) {
                    setTextViewDrawableColor(txtFirstLetter,getColor(mContext))
                } else {
                    layoutRoot.alpha = .7f
                }

                imgEdit.setOnClickListener{
                    listener.onClick(data = itemInfo)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return prvItem.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface OnCardClickListener {
        fun onClick(data: ProviderItem)

    }


    private fun getColor(context: Context) : Int{

        val newPos = currentCodePos
        currentCodePos++
        if(currentCodePos == 4){
            currentCodePos = 0
        }
        return Helper.getColors(context)[newPos]


    }


    private var currentCodePos = 0

    private fun setTextViewDrawableColor(textView: TextView, color: Int) {
       textView.backgroundTintList = ColorStateList.valueOf(color)
    }
}