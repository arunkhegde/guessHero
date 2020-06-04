package com.example.guesshero.activities.adapters

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.guesshero.R
import com.example.guesshero.activities.model.Heroes
import kotlinx.android.synthetic.main.list_item.view.*

class HeroesAdapters(val context: Context, private val heroes:List<Heroes>): RecyclerView.Adapter<HeroesAdapters.MyViewHolder>() {


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var currentHobby: Heroes?=null
        var currentPosition:Int=0


        fun setData(hero: Heroes, pos:Int){
            hero.let {
                itemView.name_film.text="${it.film} : ${it.hero_name} (${it.nick_name})"
                this.currentHobby=it
                this.currentPosition=pos

            }

        }
        init {
            itemView.setOnClickListener{
                currentHobby?.let{
                    val message: String = "Films of ${it.hero_name}"
                    val intent = Intent()
                    intent.action = Intent.ACTION_WEB_SEARCH
                    intent.putExtra(SearchManager.QUERY, message)
                    context.startActivity(intent)

                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby=heroes[position]
        holder.setData(hobby,position)
    }
}