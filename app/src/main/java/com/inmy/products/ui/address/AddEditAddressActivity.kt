package com.inmy.products.ui.address

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R
import com.inmy.products.data.room.data.Address
import com.inmy.products.databinding.ActivityAddEditAddressBinding
import com.inmy.products.databinding.ActivityProductDetailBinding
import kotlinx.android.synthetic.main.activity_add_edit_address.*

class AddEditAddressActivity : AppCompatActivity() {
    companion object {
        fun newInstance() = AddEditAddressActivity()
        const val EXTRA_ADDRESS = "com.inmy.products.ui.address.listsql.ADDRESS"
    }
    private lateinit var addEditAddressViewModel: AddEditAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddEditAddressBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_add_edit_address)

        binding.lifecycleOwner = this

        addEditAddressViewModel =
            this.run { ViewModelProvider(this).get(AddEditAddressViewModel::class.java) }

        binding.addEditAddressViewModel = addEditAddressViewModel

        buttonSaveAddress.setOnClickListener {
            val addressIntent = Intent()
            val address = Address(name = editTextName.text.toString(),mobile = editTextMobile.text.toString(),
                pincode = editTextPincode.text.toString(), address = editTextAddress.text.toString(),
                city = editTextCity.text.toString(), state = editTextState.text.toString())
            addressIntent.putExtra(EXTRA_ADDRESS,address)
            setResult(Activity.RESULT_OK, addressIntent)

            finish()
        }
    }


}


