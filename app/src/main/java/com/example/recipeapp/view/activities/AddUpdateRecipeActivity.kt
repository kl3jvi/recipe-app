package com.example.recipeapp.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityAddUpdateRecipeBinding
import com.example.recipeapp.databinding.CustomDialogBinding

class AddUpdateRecipeActivity : AppCompatActivity(), View.OnClickListener {

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
        binding.tvCamera.setOnClickListener{
            Toast.makeText(this, "Camera Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.tvGallery.setOnClickListener{
            Toast.makeText(this, "Gallery Clicked", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}