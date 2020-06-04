package com.example.guesshero.fragments.title

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.guesshero.R
import com.example.guesshero.activities.HeroLearn
import com.example.guesshero.databinding.TitleFragmentBinding

// TODO: Rename parameter arguments, choose names that match

class TitleFragment : Fragment() {
    private lateinit var binding: TitleFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.title_fragment,container,false)
        binding.getGoing.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())

        }

        binding.learnHero.setOnClickListener{
            val intent = Intent(activity, HeroLearn::class.java)
            startActivity(intent)
        }




        return binding.root


    }



}