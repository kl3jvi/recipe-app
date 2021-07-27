package com.example.recipeapp.view.activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityAddUpdateRecipeBinding
import com.example.recipeapp.databinding.CustomDialogBinding
import pub.devrel.easypermissions.EasyPermissions


class AddUpdateRecipeActivity : AppCompatActivity(), View.OnClickListener,
    EasyPermissions.PermissionCallbacks {

    private val REQUEST_CODE: Int = 123
    private lateinit var mBinding: ActivityAddUpdateRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddUpdateRecipeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.ivAddDishImage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_add_dish_image -> {
                    customImageSelectionDialog()
                    return
                }
            }
        }
    }

    private fun customImageSelectionDialog() {
        val dialog = Dialog(this)
        val binding: CustomDialogBinding =
            CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.tvCamera.setOnClickListener {
            checkCameraPermission()
            dialog.dismiss()
        }
        binding.tvGallery.setOnClickListener {
            checkStoragePermission()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        AlertDialog.Builder(this)
            .setMessage(
                "It looks like you haven't given permissions required for " +
                        "this feature. It can be enabled under settings"
            )
            .setPositiveButton("GO TO SETTINGS") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }.setNegativeButton("No", null)
            .show()
    }


    private fun checkCameraPermission() {
        if (hasPermission()) {
            // Have permission, do the thing!
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) { // its always null
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
            println("ckemiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii")

        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_camera),
                REQUEST_CODE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }
    }

    private fun checkStoragePermission() {
        if (hasPermission()) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Gallery things", Toast.LENGTH_LONG).show()
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_camera),
                REQUEST_CODE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }
    }

    private fun hasPermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.CAMERA
        ) && EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE){
                data?.extras?.let {
                    val thumbnail: Bitmap = data.extras!!.get("data") as Bitmap
                    mBinding.ivDishImage.setImageBitmap(thumbnail)
                }
            }
        }
    }


    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
