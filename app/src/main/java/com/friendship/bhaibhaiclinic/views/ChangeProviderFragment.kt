package com.friendship.bhaibhaiclinic.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.friendship.bhaibhaiclinic.R
import com.friendship.bhaibhaiclinic.base.Constant
import com.friendship.bhaibhaiclinic.base.Constant.ACTIVE
import com.friendship.bhaibhaiclinic.base.Constant.INACTIVE
import com.friendship.bhaibhaiclinic.base.Constant.TAG
import com.friendship.bhaibhaiclinic.base.LoadingDialog
import com.friendship.bhaibhaiclinic.databinding.FragmentChangeProviderBinding
import com.friendship.bhaibhaiclinic.model.ProviderItem
import com.friendship.bhaibhaiclinic.model.ProviderListResponse
import com.friendship.bhaibhaiclinic.model.SingleProviderResponse
import com.friendship.bhaibhaiclinic.networking.DataState
import com.friendship.bhaibhaiclinic.util.Helper
import com.friendship.bhaibhaiclinic.util.NetworkUtil
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@AndroidEntryPoint
class ChangeProviderFragment : Fragment() {


    private  var data : ProviderItem?= null
    private lateinit var binding: FragmentChangeProviderBinding
    private val viewModel : ProviderViewModel by viewModels()
    private lateinit var loadingDialog: LoadingDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChangeProviderBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        setUpData()

        observeUpdateData()

        return binding.root
    }

    private fun observeUpdateData() {
        viewModel.updateProvider.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    if (NetworkUtil.isValidResponse(it)) {
                        // get data
                        val body = it.value.body()?.string()
                        Log.e(Constant.TAG, body.toString())
                        goBack()
                    }
                    loadingDialog.dismiss()
                }

                is DataState.Loading -> {
                    loadingDialog.show()

                }

                is DataState.Error -> {
                    loadingDialog.dismiss()

                }

            }
        }

        viewModel.createProvider.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    if (NetworkUtil.isValidResponse(it)) {
                        // get data
                        val body = it.value.body()?.string()
                        Log.e(Constant.TAG, body.toString())
                        goBack()
                    }
                    loadingDialog.dismiss()
                }

                is DataState.Loading -> {
                    loadingDialog.show()

                }

                is DataState.Error -> {
                    loadingDialog.dismiss()

                }

            }
        }
    }

    private fun setClicks() {


        binding.apply {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, resources.getStringArray(R.array.gender))
            autoGender.setAdapter(adapter)

            imageView.setOnClickListener {
                goBack()
            }
            submitArea.setOnClickListener {
               submitData()
            }

            swOnOff.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    data?.status = ACTIVE
                } else data?.status = INACTIVE
            }


        }
    }

    private fun submitData() {
        binding.apply {
            val name = layoutName.editText?.text.toString().trim()
            val mail = layoutEmail.editText?.text.toString().trim()
            val gender = layoutGender.editText?.text.toString().trim()
            val status = layoutStatus.editText?.text.toString().trim()

            if(!Helper.isValidEmail(mail)) {
                return
            }
            if(name.length<5) {
                return
            }

            Log.d(TAG, "$name $mail $gender $status")

            data = ProviderItem(mail,gender,data?.id,name,status)

        }
        if(!isCreate){
            submitUpdate()

        }
        else{
            submitCreate()
        }
    }

    private fun submitCreate() {
        viewModel.createProvider(requireContext(),getRequestBody())
    }

    private fun submitUpdate() {
        viewModel.updateProvider(requireContext(),idStr,getRequestBody())
    }

    private var idStr = ""
    private fun setDataToView() {
        binding.apply {
            data?.apply {
                layoutName.editText?.setText(name)
                layoutEmail.editText?.setText(email)
                layoutGender.editText?.setText(gender)
                layoutStatus.editText?.setText(status)
                if(!isCreate){
                    idStr = id.toString()
                }
                swOnOff.isChecked = status == ACTIVE
            }
            if(!isCreate){
                submitArea.text = requireContext().getString(R.string.update)
                txtDynamic.text = requireContext().getString(R.string.update)
            }
        }
        setClicks()
    }
    private fun goBack() {
        if (parentFragmentManager.backStackEntryCount == 0) activity?.finish()
        else findNavController().popBackStack()
    }

    private var isCreate : Boolean = true


    private fun setUpData() {
        arguments?.let {
            if (arguments?.containsKey(Constant.UPDATE) == true) {
                data = Helper.getSerializable(
                    arguments,
                    Constant.UPDATE,
                    ProviderItem::class.java
                )
                isCreate = false
            }
            if (arguments?.containsKey(Constant.CREATE) == true) {
                // Do something..
                data = Helper.getSerializable(
                    arguments,
                    Constant.CREATE,
                    ProviderItem::class.java
                )
                isCreate = true
            }
            setDataToView()
        }
    }


    private fun getRequestBody(): RequestBody {
        val jsonObject = JSONObject().apply {
            put("name", data?.name)
            put("email", data?.email)
            put("gender", data?.gender)
            put("status", data?.status)
        }
        return jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
    }

}