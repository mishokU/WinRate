package com.example.winrate.domain.convertors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.winrate.R
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.domain.helpers.imgPath
import com.example.winrate.domain.models.HeroRoles
import com.example.winrate.domain.models.SingleRole
import com.example.winrate.ui.adapters.HeroFullRolesAdapter
import com.example.winrate.ui.adapters.HeroRolesAdapter
import com.example.winrate.ui.adapters.HeroesAdapter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<HeroProperty>?){
    val adapter = recyclerView.adapter as HeroesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listRolesData")
fun bindRolesRecyclerView(recyclerView: RecyclerView, data: List<HeroRoles>?){
    val adapter = recyclerView.adapter as HeroRolesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listFullRolesData")
fun bindFullRolesRecyclerView(recyclerView: RecyclerView, data: List<SingleRole>?){
    val adapter = recyclerView.adapter as HeroFullRolesAdapter
    adapter.submitList(data)
}



@BindingAdapter("heroesApiStatus")
fun bindStatus(statusImageView: ImageView, status: HeroApiStatus){
    when(status){
        HeroApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HeroApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_img)
        }
        HeroApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    Picasso.get().load(imgPath + imgUrl).placeholder(R.drawable.loading_animation).into(imgView)
}

@BindingAdapter("primaryAttributeSwitcher")
fun bindPrimaryAttribute(imgView: ImageView, attribute: String?){
    when(attribute){
        "int" -> imgView.setImageResource(R.drawable.intelligence_attribute_symbol)
        "agi" -> imgView.setImageResource(R.drawable.agility_attribute_symbol)
        "str" -> imgView.setImageResource(R.drawable.strength_attribute_symbol)
        else -> imgView.setImageResource(R.drawable.loading_animation)
    }
}

@BindingAdapter("rolesBuilder")
fun bindRoles(textView: TextView, roles: List<String>){
    val rolesBuilder = StringBuilder()
    for(role in roles){
        rolesBuilder.append(role).append(" ")
    }
    if(rolesBuilder.isNotEmpty()){
       textView.text = rolesBuilder
    } else {
        textView.text = "No roles"
    }
}

@BindingAdapter("textViewDouble")
fun bindDouble(textView: TextView, number: Double){
    textView.text = number.toString()
}

@BindingAdapter("textViewBool")
fun bindBoolean(textView: TextView, number: Boolean){
    if(number){
        textView.text = number.toString()
    } else {
        textView.text = number.toString()
    }
}

@BindingAdapter("bindRole")
fun bindRoles(imageView: ImageView, role: String){
    when(role){
        "Disabler" -> imageView.setBackgroundResource(R.drawable.disabler)
        "Escape" -> imageView.setBackgroundResource(R.drawable.escaper)
        "Jungler" -> imageView.setBackgroundResource(R.drawable.jungler)
        "Durable" -> imageView.setBackgroundResource(R.drawable.durable)
        "Nuker" -> imageView.setBackgroundResource(R.drawable.nuker)
        "Pusher" -> imageView.setBackgroundResource(R.drawable.pusher)
        "Initiator" -> imageView.setBackgroundResource(R.drawable.initiator)
        "Carry" -> imageView.setBackgroundResource(R.drawable.carry)
        "Support" -> imageView.setBackgroundResource(R.drawable.support)
    }
}

@BindingAdapter("showTextHelper")
fun bindHelperText(view: View, help: String){
    view.setOnClickListener {
        Snackbar.make(view, help, Snackbar.LENGTH_SHORT).show()
    }
}
