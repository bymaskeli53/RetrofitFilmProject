package com.example.retrofitfilmproject

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.retrofitfilmproject.databinding.FragmentDetailBinding

class DetailsFragment : Fragment(R.layout.fragment_detail) {

   private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        binding.apply {
            textViewDirectorDetail.text = args.model.director
            textViewTitleDetail.text = args.model.title
        }
    }
    // pop up to
}