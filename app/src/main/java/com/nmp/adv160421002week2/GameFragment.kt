package com.nmp.adv160421002week2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.nmp.adv160421002week2.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"
        }

        var score = requireArguments().getInt("score", 0)
        val random = Random()
        val number = random.nextInt(20).toString()
        val number2 = random.nextInt(20).toString()

        binding.txtNum1.text = number
        binding.txtNum2.text = number2

        val hasil = (number.toInt() + number2.toInt()).toString()
        binding.btnSubAns.setOnClickListener {
            if (hasil == binding.txtAnswer.text.toString()) {
                score++
                Log.e("score", score.toString())
                val action = GameFragmentDirections.actionGameFragmentSelf(score.toString())
                Navigation.findNavController(it).navigate(action)
            } else {
                Log.e("score", score.toString())
                val action = MainFragmentDirections.actionGameFragment(score.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}