package com.example.guesshero.fragments.game

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.guesshero.R
import com.example.guesshero.activities.model.Heroes
import com.example.guesshero.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding:GameFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding=DataBindingUtil.inflate(inflater,R.layout.game_fragment, container, false)
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        viewModel.show_hint.observe(viewLifecycleOwner,Observer{
            if (it==true) {
                binding.nicknameText.visibility = View.VISIBLE
            }
            else{
                binding.nicknameText.visibility=View.GONE
            }
            binding.heroName.clearFocus()
            val imm=activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView()!!.windowToken,0)
        })

        viewModel.check.observe(viewLifecycleOwner, Observer {
            (it==true)
                onCorrect()
        })

        viewModel.gameFinish.observe(viewLifecycleOwner, Observer {
            if(it==true)
                gameFinish()
        })

        return binding.root

    }
     fun onCorrect(){
         if(viewModel.check.value==true) {
             if (binding.heroName.text.toString()
                     .toLowerCase() == viewModel.current_data_hero.value?.hero_name?.toLowerCase()) {
                 viewModel.incrementScore()

             }
             else{
                 Toast.makeText(activity,"GameOver",Toast.LENGTH_SHORT).show()
                 gameFinish()
             }
             binding.heroName.text=null

             binding.heroName.clearFocus()
             val imm=activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
             imm.hideSoftInputFromWindow(requireView()!!.windowToken,0)

         }

    }
    fun gameFinish()
    {

        var action=GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value!!)
      //  action.score= viewModel.score.value?:0
        findNavController().navigate(action)
    }


}


class GameViewModel:ViewModel(){
    // The current hero
    private val _hero = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _hero
    //Check if the entered name is required hero
    private val _check=MutableLiveData<Boolean>()
    val check :LiveData<Boolean>
    get() = _check
    //true if hint is pressed
    private val _show_hint=MutableLiveData<Boolean>()
    val show_hint:LiveData<Boolean>
    get() = _show_hint

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _gameFinish=MutableLiveData<Boolean>()
    val gameFinish:LiveData<Boolean>
    get() = _gameFinish

    //countdown time
    private var _currentTime=MutableLiveData<Long>()
    val currentTime:LiveData<Long>
        get() = _currentTime

    //string version of time
    val currentTimeString=Transformations.map(currentTime){
        DateUtils.formatElapsedTime(it)
    }

    private  var timer: CountDownTimer
    companion object{
        //countdown time interval
        private const val ONE_SECOND=1000L
        //Total time for the game
        private const val COUNTDOWN_TIME=20000L
    }

    //DataClass
    data class Hero (val film:String,val hero_name:String,val nick_name:String)

    private val _current_data_hero=MutableLiveData<Hero>()
    val current_data_hero:LiveData<Hero>
    get() = _current_data_hero

    //lets give them no of characters in name
    val instruction=Transformations.map(current_data_hero){
        "Guess here:has ${it.hero_name.length} chars"
    }

    //list of heroes
    private lateinit var hero_list: MutableList<Hero>

    private fun resetList(){
        hero_list=mutableListOf<Hero>(
            Hero("Yajamana","Vishnuvardhan","Sahasasimha"),
            Hero("GandhadhaGudi","Rajakumar","Mutturaj"),
            Hero("Baby","Akshay","Khiladi"),
            Hero("2.0","Rajanikanth","thaliva"),
            Hero("Bala","Ayushman","Ayu"),
            Hero("Uri","Vicky","Kaushal"),
            Hero("Tanhaji","Ajay","Singham"),
            Hero("MungaruMale","Ganesh","GoldenStar"),
            Hero("KGF","Yash","RockingStar"),
            Hero("AvengersEndgame","Robert","Tony"),
            Hero("Inception","Leonardo","LennyD"),
            Hero("PiratesOfCarribean","Johnny","Jack"),
            Hero("Paramanu","John","Abram"),
            Hero("Race3","Salman","Sallu"),
            Hero("KabirSingh","Shahid","Shak")
        )
        hero_list.shuffle()

    }


    private fun nextHero(){
        if (!hero_list.isEmpty())
        {
            _current_data_hero.value=hero_list.removeAt(0)
            _show_hint.value=false

        }
        else{
            resetList()
        }
        timer.start()
    }
    private fun makeHintTrue(){
        _show_hint.value=true
    }
    fun incrementScore(){
        _score.value=_score.value?.plus(1)
        _check.value=false
    }
    fun onHint(){
        makeHintTrue()
    }

    fun onSubmit()
    {
        _check.value=true
        nextHero()
    }
    fun onEnd()
    {
        _check.value=true
    }

    init {
        Log.i("creation","lol")
        _score.value=0
        _check.value=false
        _gameFinish.value=false
        timer=object:CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                //_currentTime.value= DONE
                onSubmit()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value=millisUntilFinished/ ONE_SECOND
            }
        }

        resetList()
        nextHero()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("destroy","destroyed")
    }
}