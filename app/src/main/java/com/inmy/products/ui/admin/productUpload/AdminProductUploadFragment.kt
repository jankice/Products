package com.inmy.products.ui.admin.productUpload

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.inmy.products.*
import com.inmy.products.data.room.data.Product
import com.inmy.products.data.room.data.ProductRepository
import com.inmy.products.databinding.FragmentAdminProductUploadBinding
import com.inmy.products.ui.dialogs.DefaultAlertDialog
import com.inmy.products.ui.dialogs.ImageSelectionDialog
import kotlinx.android.synthetic.main.dialog_alert_default.view.*
import kotlinx.android.synthetic.main.dialog_select_image.view.*
import kotlinx.android.synthetic.main.fragment_admin_product_upload.*


class AdminProductUploadFragment : Fragment() , View.OnClickListener{

    private lateinit var adminProductUploadViewModel: AdminProductUploadViewModel

    private lateinit var productRepository: ProductRepository

    private lateinit var imageUri: String
    private lateinit var imageType: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAdminProductUploadBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_admin_product_upload,
                container,
                false
            )

        adminProductUploadViewModel = activity?.run {
            ViewModelProvider(this)[AdminProductUploadViewModel::class.java]
        } ?: throw Exception("Invalid Activity")


        binding.adminProductUploadViewModel = adminProductUploadViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productRepository = (activity?.application as Products).repositoryProducts

        imageButtonUploadProductImage.setOnClickListener(this)
        buttonProductSubmit.setOnClickListener(this)
        imageButtonDeleteProductImage.setOnClickListener(this)
        addProductImage.setOnClickListener(this)
        addColorOption.setOnClickListener(this)
        productImageInfo.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageButtonUploadProductImage -> {
                imageType = "Default"
                if(!setupPermissions()){
                    openImageSelectionDialog()
                }
            }
            R.id.buttonProductSubmit -> {

                if (adminProductUploadViewModel.checkProductDetailValidations()) {
                    val product = Product(
                        P_image = imageUri,
                        P_name = editTextProductItemName.text.toString(),
                        P_detail = editTextProductItemDetail.text.toString(),
                        P_category = editTextProductItemCategory.text.toString(),
                        P_subCategory = editTextProductItemSubCategory.text.toString(),
                        P_price = Integer.parseInt(editTextProductItemPriceDetail.text.toString()),
                        P_length = Integer.parseInt(
                            productLength.text.toString()
                        ),
                        P_width = Integer.parseInt(productHeight.text.toString()),
                        P_height = Integer.parseInt(
                            productHeight.text.toString()
                        ),
                        P_images = "0"
                    )

                    try {

                        adminProductUploadViewModel.insert(product, productRepository)
                        openSuccessDialog()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Snackbar.make(
                            v,
                            "Something went wrong, please try again later.",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Action", null)
                            .show()
                    }


                } else {
                    Snackbar.make(
                        v,
                        "Please enter correct information for product.",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null)
                        .show()
                }


            }
            R.id.imageButtonDeleteProductImage -> {
                imageViewUploadProductImage.setImageURI(null)
                imageButtonUploadProductImage.visibility = View.VISIBLE
                imageButtonDeleteProductImage.visibility = View.GONE
            }

            R.id.addProductImage -> {
                imageType = "Multiple"
                if(!setupPermissions()){
                    openImageSelectionDialog()
                }
            }
            R.id.addColorOption -> {

            }
            R.id.productImageInfo -> {
                Snackbar.make(v, "Add default photo for selected Product.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
        }
    }

    private fun openSuccessDialog() {
        val dialog = DefaultAlertDialog(requireContext(), "")
        dialog.mDialogView.textViewDefaultText.text = "Product detail submitted successfully. " +
                "Please goto product list if you wish to live product to product store."
        dialog.mDialogView.buttonOK.setOnClickListener{
            dialog.mAlertDialog.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQ_IMAGE_FROM_GALLARY){
            val uri = data?.data
            if(imageType.equals("Default")){
                imageViewUploadProductImage.setImageURI(uri)
                imageButtonUploadProductImage.visibility = View.GONE
                imageButtonDeleteProductImage.visibility = View.VISIBLE
            }else{
                val imageView = adminProductUploadViewModel.addImageViewForImage(requireContext())
                imageView.setImageURI(uri)
            }

            imageUri = uri?.let { convertToBase64(requireContext(), it) }.toString()
            Log.d("image", "" + imageUri)
        } else if(resultCode == RESULT_OK && requestCode  == REQ_IMAGE_FROM_CAMERA){

        }
    }

    fun openImageSelectionDialog() {
        val imageDialog = ImageSelectionDialog(requireContext())

        imageDialog.mDialogView.selectFromGallery.setOnClickListener{
            imageDialog.mAlertDialog.dismiss()
            openGalleryForImage()

        }

        imageDialog.mDialogView.selectFromCamera.setOnClickListener{

        }

    }
    fun openGalleryForImage() {
        val intent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.type = "image/*"

        startActivityForResult(intent, REQ_IMAGE_FROM_GALLARY)
    }
    fun getImageFromCamera(){

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            999 -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("TAG", "Permission has been denied by user")
                } else {
                    Log.i("TAG", "Permission has been granted by user")
                    openImageSelectionDialog()
                }
            }
        }
    }
    var permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private fun setupPermissions() : Boolean{
        var result = 0
        var listPermissionsNeeded: ArrayList<String> = ArrayList()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(requireActivity(), p)
            if (result !== PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(),
                listPermissionsNeeded.toTypedArray(), 999);
            return false;
        }
        return true;
    }

}