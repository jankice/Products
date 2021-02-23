package com.inmy.products.ui.admin.productUpload

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R
import com.inmy.products.REQ_IMAGE_FROM_CAMERA
import com.inmy.products.REQ_IMAGE_FROM_GALLARY
import com.inmy.products.data.model.Dimen
import com.inmy.products.data.model.ProductRequestModel
import com.inmy.products.databinding.FragmentAdminProductUploadBinding
import com.inmy.products.ui.admin.ImageDialog
import kotlinx.android.synthetic.main.dialog_select_image.view.*
import kotlinx.android.synthetic.main.fragment_admin_product_upload.*
import kotlinx.android.synthetic.main.fragment_admin_product_upload.view.*


class AdminProductUploadFragment : Fragment() , View.OnClickListener{

    private lateinit var adminProductUploadViewModel: AdminProductUploadViewModel
    private lateinit var imageUri: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAdminProductUploadBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_admin_product_upload, container, false)

        adminProductUploadViewModel = activity?.run {
            ViewModelProvider(this)[AdminProductUploadViewModel::class.java]
        } ?: throw Exception("Invalid Activity")


        binding.adminProductUploadViewModel = adminProductUploadViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageButtonUploadProduct.setOnClickListener(this)
        buttonProductSubmit.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageButtonUploadProduct -> {
               openImageSelectionDialog()
            }
            R.id.buttonProductSubmit ->{

                val length  =  Integer.parseInt(productLength.text.toString())
                val width = Integer.parseInt(productHeight.text.toString())
                val height = Integer.parseInt(productHeight.text.toString())

                val productRequestModel = ProductRequestModel(imageUri,editTextProductItemName.text.toString(),editTextProductItemDetail.text.toString(),"",
                    Dimen(length,width,height),editTextProductItemCategory.text.toString(),editTextProductItemSubCategory.text.toString(),
                    Integer.parseInt(editTextProductItemPriceDetail.text.toString()))

                adminProductUploadViewModel.submitProduct(productRequestModel)
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQ_IMAGE_FROM_GALLARY){
            var uri = imageViewUploadProduct.setImageURI(data?.data)
            imageUri = uri.toString()
        } else if(resultCode == RESULT_OK && requestCode  == REQ_IMAGE_FROM_CAMERA){

        }
    }

    fun openImageSelectionDialog() {
        val imageDialog = ImageDialog(requireContext())

        imageDialog.mDialogView.selectFromGallery.setOnClickListener{
            imageDialog.mAlertDialog.dismiss()
            openGalleryForImage()

        }

        imageDialog.mDialogView.selectFromCamera.setOnClickListener{

        }

    }
    fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        startActivityForResult(intent, REQ_IMAGE_FROM_GALLARY)
    }
    fun getImageFromCamera(){

    }
}