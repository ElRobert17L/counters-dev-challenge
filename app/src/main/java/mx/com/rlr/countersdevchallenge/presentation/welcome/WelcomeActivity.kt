package mx.com.rlr.countersdevchallenge.presentation.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.rlr.countersdevchallenge.R
import mx.com.rlr.countersdevchallenge.databinding.ActivityWelcomeBinding
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.navigateTo
import mx.com.rlr.countersdevchallenge.presentation.home.HomeActivity

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpAction()
    }

    private fun setUpAction() {
        binding.activityWelcomeBtn.setOnClickListener {
            navigateTo(HomeActivity::class.java, clearTop = true)
        }
    }

}