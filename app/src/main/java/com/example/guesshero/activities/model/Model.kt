package com.example.guesshero.activities.model

data class Heroes (val film:String,val hero_name:String,val nick_name:String)

object Supplier {
    val list_hero = listOf(
        Heroes("Yajamana","Vishnuvardhan","Sahasasimha"),
        Heroes("GandhadhaGudi","Rajakumar","Mutturaj"),
        Heroes("Baby","Akshay","Khiladi"),
        Heroes("2.0","Rajanikanth","thaliva"),
        Heroes("Bala","Ayushman","Ayu"),
        Heroes("Uri","Vicky","Kaushal"),
        Heroes("Tanhaji","Ajay","Singham"),
        Heroes("MungaruMale","Ganesh","GoldenStar"),
        Heroes("KGF","Yash","RockingStar"),
        Heroes("AvengersEndgame","Robert","Tony"),
        Heroes("Inception","Leonardo","LennyD"),
        Heroes("PiratesOfCarribean","Johnny","Jack"),
        Heroes("Paramanu","John","Abram"),
        Heroes("Race3","Salman","Sallu"),
        Heroes("KabirSingh","Shahid","Shak")


    )
}