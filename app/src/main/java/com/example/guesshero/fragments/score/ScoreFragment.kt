package com.example.guesshero.fragments.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.guesshero.R
import com.example.guesshero.databinding.ScoreFragmentBinding
import java.lang.IllegalArgumentException

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ScoreFragmentBinding= DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )
        var viewModelFactory=ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()!!).score)
        var viewModel=ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        return binding.root
    }



}

class ScoreViewModel(fScore: Int) : ViewModel( ) {
    val _score=MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    init {
        _score.value=fScore

    }
}

class ScoreViewModelFactory(private val fScore: Int):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)){
            return ScoreViewModel(fScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}