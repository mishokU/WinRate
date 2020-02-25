package com.example.winrate.domain.convertors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.winrate.R
import com.example.winrate.domain.convertors.RefactorHeroNames.Companion.getUrlSkill
import com.example.winrate.domain.helpers.skillPath
import com.squareup.picasso.Picasso


enum class Skills{ first, second, third, fourth, fifth, ulty }

@BindingAdapter("firstSkill")
fun bindFirstSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.first)).placeholder(R.color.colorDark).into(imageView)
}

@BindingAdapter("secondSkill")
fun bindSecondSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.second)).placeholder(R.color.colorDark).into(imageView)
}

@BindingAdapter("thirdSkill")
fun bindThirdSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.third)).placeholder(R.color.colorDark).into(imageView)
}

@BindingAdapter("fourthSkill")
fun bindFourthSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.fourth)).placeholder(R.color.colorDark).into(imageView)
}

@BindingAdapter("fifthSkill")
fun bindFifthSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.fifth)).placeholder(R.color.colorDark).into(imageView)
}

@BindingAdapter("UltySkill")
fun bindUltimateSkill(imageView: ImageView, heroName: String){
    Picasso.get().load(skillPath + getUrlSkill(imageView,heroName, Skills.ulty)).placeholder(R.color.colorDark).into(imageView)
}

