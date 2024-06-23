package com.rifkydelta.storyapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifkydelta.storyapp.R
import com.rifkydelta.storyapp.data.di.ViewModelFactory
import com.rifkydelta.storyapp.databinding.ActivityMainBinding
import com.rifkydelta.storyapp.ui.addStory.AddStoryActivity
import com.rifkydelta.storyapp.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var storyAdapter: ListStoryAdapter
    private var token = ""
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setupView()
        setupViewModel()
        setupAdapter()
        setupAction()
        setupUser()
        setupSwipeRefreshLayout()
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            // Aksi yang dilakukan saat refresh
            setupData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupAction() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }
    }

    private fun setupUser() {
        showLoading(true)
        mainViewModel.getSession().observe(this@MainActivity) { session ->
            token = session.token
            if (!session.isLogin) {
                moveActivity()
            } else {
                val userName = session.name
                val helloUserText = getString(R.string.username, userName)
                supportActionBar?.title = helloUserText
                setupData()
                showLoading(false)
                showToast()
            }
        }
    }

    private fun setupData() {
        mainViewModel.getListStories.observe(this@MainActivity) { pagingData ->
            storyAdapter.submitData(lifecycle, pagingData)
        }
    }

    private fun setupAdapter() {
        storyAdapter = ListStoryAdapter()
        binding.rvStories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = storyAdapter.withLoadStateFooter(
                footer = LoadingAdapter {
                    storyAdapter.retry()
                }
            )
        }
    }

    private fun setupView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.apply {
                shimmerLoading.visibility = View.VISIBLE
                shimmerLoading.startShimmer()
                rvStories.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                rvStories.visibility = View.VISIBLE
                shimmerLoading.stopShimmer()
                shimmerLoading.visibility = View.INVISIBLE
            }
        }
    }

    private fun showToast() {
        mainViewModel.toastText.observe(this@MainActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@MainActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity() {
        startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_logout -> {
                mainViewModel.logoutApp()
                true
            }
            R.id.btn_language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
