package com.nublikaska.exercises.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.navigation.nested.NestedFragmentNavigator
import com.nublikaska.exercises.modules.avatars.data.UserRepository
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private val nestedFragmentNavigator by lazy {

        NestedFragmentNavigator(nav_host_fragment)
    }

    private val userTestRepository: UserRepository by lazy { UserRepositoryImpl(sharedPrefs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        setContentView(R.layout.activity_main)
        setupNavigators()

        setStartGraph()
    }

    private fun setStartGraph() {

        val graphResId = when (userTestRepository.userName.isEmpty()) {

            true -> R.navigation.nav_auth
            else -> R.navigation.nav_main
        }

        navController.setGraph(graphResId)
    }

    private fun setupNavigators() {

        navController.navigatorProvider.apply {

            addNavigator(nestedFragmentNavigator)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {

            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
