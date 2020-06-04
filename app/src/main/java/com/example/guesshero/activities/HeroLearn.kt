package com.example.guesshero.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guesshero.R
import com.example.guesshero.activities.adapters.HeroesAdapters
import com.example.guesshero.activities.model.Supplier
import kotlinx.android.synthetic.main.activity_hero_learn.*

class HeroLearn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_learn)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val layoutManager= LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL

        recyclerView.layoutManager=layoutManager

        val adapter= HeroesAdapters(
            this,
            Supplier.list_hero
        )
        recyclerView.adapter=adapter
    }
}