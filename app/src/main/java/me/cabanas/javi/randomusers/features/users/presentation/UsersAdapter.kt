package me.cabanas.javi.randomusers.features.users.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user_male.view.*
import me.cabanas.javi.randomusers.R
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.framework.GlideApp
import kotlin.properties.Delegates

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    var itemClick: (UserEntity) -> Unit = { _ -> }

    var users: List<UserEntity> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size


    override fun getItemViewType(position: Int): Int {
        return if (users[position].gender.toLowerCase() == "male") {
            MALE
        } else {
            FEMALE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return if (viewType == MALE) {
            UserViewHolder.MaleViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_male, parent, false))
        } else {
            UserViewHolder.FemaleViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_female, parent, false))
        }

    }


    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.bind(users[position], itemClick)
    }


    sealed class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bind(user: UserEntity, clickListener: (UserEntity) -> Unit) {
            with(user) {
                itemView.name.text = name.first + " " + name.last
                itemView.phone.text = phone
                itemView.email.text = email
                GlideApp.with(itemView)
                        .load(picture.thumbnail)
                        .placeholder(R.mipmap.ic_launcher)
                        .centerInside()
                        .into(itemView.avatar)
                itemView.setOnClickListener { clickListener(this) }
            }
        }

        class MaleViewHolder(itemView: View) : UserViewHolder(itemView)
        class FemaleViewHolder(itemView: View) : UserViewHolder(itemView)
    }

    companion object {
        const val FEMALE = 1
        const val MALE = 0
    }
}
