package AcedoChaidez.guessthenumber

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import AcedoChaidez.guessthenumber.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding1
    var minValue= 0
    var maxValue= 100
    var num: Int = 0
    var won: Boolean= false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)

        val guessings: TextView= findViewById<TextView>(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        generate.setOnClickListener(){
            num = Random.nextInt(minValue,maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }
        up.setOnClickListener(){
            minValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else {
                guessings.setText("No puede ser: (Me ganaste)")
            }

        }

        down.setOnClickListener(){
            maxValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else {
                guessings.setText("No puede ser: (Me ganaste)")
            }
        }

        guessed.setOnClickListener(){
            if(!won){
                guessings.setText("Adivine tu numero es el: "+ num)
                guessed.setText("Play again")
                won= true
            }else{
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility= View.GONE
                resetValues()
            }

        }
    }
    fun resetValues(){
        minValue = 0
        maxValue = 0
        num = 0
        won = false
    }
    fun checkingLimits(): Boolean{
    return minValue != maxValue
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}