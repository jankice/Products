package com.inmy.products.ui.admin.productUpload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R


class AdminProductUploadFragment : Fragment() {

    private lateinit var adminProductUploadViewModel: AdminProductUploadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adminProductUploadViewModel =
            ViewModelProvider(this).get(AdminProductUploadViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_admin_product_upload, container, false)


        return root
    }
}