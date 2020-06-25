package johan.kovalsikoski.transitionmanager

import android.content.Intent
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scene1 = Scene.getSceneForLayout(
            root_container,
            R.layout.activity_main_scene_1,
            applicationContext
        )

        val scene2 = Scene.getSceneForLayout(
            root_container,
            R.layout.activity_main_scene_2,
            applicationContext
        )

        val scene3 = Scene.getSceneForLayout(
            root_container,
            R.layout.activity_main_scene_3,
            applicationContext
        )

        var animCtrl = 0
        root_container.setOnClickListener {
            animCtrl++
            when (animCtrl) {
                1 -> {
                    val transitionFade = Fade()
                    transitionFade.duration = 5000
                    TransitionManager.go(scene1, transitionFade)
                }
                2 -> {
                    val transitionExplode = Explode()
                    transitionExplode.duration = 5000
                    transitionExplode.propagation = CircularPropagation()
                    TransitionManager.go(scene2, transitionExplode)
                }
                else -> {
                    val transitionSlide = Slide(Gravity.END)
                    transitionSlide.duration = 5000
                    TransitionManager.go(scene3, transitionSlide)
                    animCtrl = 0;
                }
            }
        }

        btnNextActivity.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            // Should be after startActivity
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

    }
}
