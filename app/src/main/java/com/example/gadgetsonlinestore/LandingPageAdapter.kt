package com.example.gadgetsonlinestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gadgetsonlinestore.data.IntroSlide

class LandingPageAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<LandingPageAdapter.LandingPageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandingPageViewHolder {
        return LandingPageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: LandingPageViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    inner class LandingPageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }

    }


}