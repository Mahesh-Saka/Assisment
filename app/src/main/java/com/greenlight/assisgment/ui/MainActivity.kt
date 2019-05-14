package com.greenlight.assisgment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.assisgment.R
import com.greenlight.assisgment.adapter.UserListAdapter
import com.greenlight.assisgment.callback.OnItemClickListener
import com.greenlight.assisgment.databinding.ActivityMainBinding
import com.greenlight.assisgment.entity.UserModel
import com.greenlight.assisgment.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {
    var userViewModel: UserViewModel? = null
    var activityMainBinding: ActivityMainBinding? = null
    var adapter: UserListAdapter? = null
    var action: Action = Action.NOACTION
    var position: Int = RecyclerView.NO_POSITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding!!.recycleView.layoutManager = LinearLayoutManager(this)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel!!.getAllUserList().observe(this@MainActivity, Observer<List<UserModel>> {
            if (adapter == null) {
                adapter = UserListAdapter(it as ArrayList<UserModel>, this)
                activityMainBinding!!.recycleView.adapter = adapter
            } else {
                when (action) {
                    Action.DELETED -> {
                        if (position > RecyclerView.NO_POSITION)
                            adapter!!.notifyItemRemove(position)
                    }
                    Action.NOACTION -> {
                        adapter!!.addAll(it as ArrayList<UserModel>)
                    }
                }
                action = Action.NOACTION
            }
        })
    }

    override fun onCardItemSelected(any: Any, position: Int) {
        userViewModel!!.run {
            delete(userModel = any as UserModel)
        }
        this.position = position
        action = Action.DELETED
    }

    enum class Action {
        DELETED,
        EDITED,
        NOACTION
    }

}
