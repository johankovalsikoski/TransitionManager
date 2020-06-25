package johan.kovalsikoski.transitionmanager

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        editTextSearch.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                searchState()
            }
        }

        textCancel.setOnClickListener {
            editTextSearch.clearFocus()
            it.requestFocus()

            hideKeyboard()

            initialState()
        }

    }

    private fun searchState() {
        textTitle.apply {
            visibility = INVISIBLE
        }

        textSubtitle.apply {
            visibility = INVISIBLE
        }

        ConstraintSet().let {
            it.load(applicationContext, R.layout.activity_second_scene_2)

            TransitionManager.beginDelayedTransition(parent_container)
            it.applyTo(parent_container)

        }
    }

    private fun initialState() {
        ConstraintSet().let { cs ->
            cs.load(applicationContext, R.layout.activity_second)

            TransitionManager.beginDelayedTransition(parent_container)
            cs.applyTo(parent_container)
        }

        textTitle.apply {
            visibility = VISIBLE
        }

        textSubtitle.apply {
            visibility = VISIBLE
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextSearch.windowToken, 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // Create and use your own animation
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom)
    }
}
