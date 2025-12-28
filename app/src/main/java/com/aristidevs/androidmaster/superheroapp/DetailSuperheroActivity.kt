package com.aristidevs.androidmaster.superheroapp

import android.icu.text.UnicodeSetSpanner
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityDetailSuperheroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superherodetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if(superherodetail.body()!=null){
                runOnUiThread { createUI(superherodetail.body()!!) }
                superherodetail.body()
            }
        }
    }

    private fun createUI(superhero: SuperheroDetailResponse) {
       // binding.ivSuperhero.load(superhero.image.url)
        binding.ivSuperhero.load("https://picsum.photos/400/400")
        binding.tvSuperheroname.text = superhero.name
        binding.tvSuperheroRealname.text = superhero.biography.fullname
        binding.tvPublisher.text = superhero.biography.publisher

        prepareStats(superhero.powerstats)


    }

    private fun prepareStats(powerstats: PowerStatsResponse) {

        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewPower, powerstats.power)



    }

    private fun updateHeight(view: View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }
}