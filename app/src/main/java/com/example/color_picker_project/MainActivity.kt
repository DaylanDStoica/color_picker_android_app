package com.example.color_picker_project


import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    // the slider variables, as seekbars
    private lateinit var redSlider : SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    //the reset button
    private lateinit var resetButton: Button
    // each color's reset temp button
    private lateinit var redResetButton: Switch
    private lateinit var blueResetButton: Switch
    private lateinit var greenResetButton: Switch
    //the number boxes to show each RGB color's current value
    private lateinit var redTextBox : EditText
    private lateinit var greenTextBox: EditText
    private lateinit var blueTextBox : EditText

    private lateinit var colorBox: View


    //create the View Model object
    private val colorValueViewModel: MyViewModel by lazy{
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // restore the saved integer values for the colors

        //detect the slider/seekbar objects
        redSlider = findViewById(R.id.RedSeekBar)
        greenSlider = findViewById(R.id.GreenSeekBar)
        blueSlider = findViewById(R.id.BlueSeekBar)

        // set the parameters of the sliders , range (0,255)
//        redSlider.max = 255
//        greenSlider.max = 255
//        blueSlider.max = 255


        // detect when the sliders are touched
        // and update their integer values to the system
//        TODO("get the sliders to recognize input so that we can get their values")

        // red slider listener, to get the progress
        redSlider.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            val TAG_RED_SLIDER = "red slider listener"
            val colorName = "red"
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                TODO("Not yet implemented")
                Log.d(TAG_RED_SLIDER, "on progress changed")
                colorValueViewModel.setRedValue( redSlider.progress) //update the red value as the slider moves
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_RED_SLIDER, "on start tracking touch detected")
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_RED_SLIDER, "on stop tracking touch")
                Log.d(TAG_RED_SLIDER, "progress at ${redSlider.progress}")
                colorValueViewModel.setRedValue(redSlider.progress)
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }
        })
        // green slider listener,
        greenSlider.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            val TAG_GREEN_SLIDER = "green slider listener"
            val colorName = "green"
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                TODO("Not yet implemented")
                Log.d(TAG_GREEN_SLIDER, "on progress changed")
                colorValueViewModel.setGreenValue( greenSlider.progress)
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_GREEN_SLIDER, "on start tracking touch detected")
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_GREEN_SLIDER, "on stop tracking touch")
                Log.d(TAG_GREEN_SLIDER, "progress at ${greenSlider.progress}")
                colorValueViewModel.setGreenValue(greenSlider.progress)
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }
        })
        // blue slider listener
        blueSlider.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            val TAG_BLUE_SLIDER = "blue slider listener"
            val colorName = "blue"
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                TODO("Not yet implemented")
                Log.d(TAG_BLUE_SLIDER, "on progress changed")
                colorValueViewModel.setBlueValue( blueSlider.progress)
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_BLUE_SLIDER, "on start tracking touch detected")
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
                Log.d(TAG_BLUE_SLIDER, "on stop tracking touch")
                Log.d(TAG_BLUE_SLIDER, "blue progress at ${blueSlider.progress}")
                colorValueViewModel.setBlueValue(blueSlider.progress)
                setColorTextBoxIntegers(colorName)
                setColorWithinColorDisplayBox()
            }
        })


        //assign the values of the text boxes of the integer values of the RGB sliders
        redTextBox = findViewById(R.id.RedTextNumber)
        blueTextBox = findViewById(R.id.BlueTextNumber)
        greenTextBox = findViewById(R.id.GreenTextNumber)
        //set the TextEdit box text to non-blank
//        this.setColorTextBoxIntegers()

        //declare the color sample box
        colorBox = findViewById(R.id.ColorBox)
        setColorWithinColorDisplayBox()

        // assign the individual color reset/temp buttons
        redResetButton = findViewById(R.id.RedSwitch)
        blueResetButton = findViewById(R.id.BlueSwitch)
        greenResetButton =  findViewById(R.id.GreenSwitch)


//        redResetButton.setOnClickListener( View.OnClickListener {
//            Log.d(TAG, "onCreate: redResetButton pressed")
//            colorValueViewModel.redResetButton(false)
//        })

//        TODO: disable each colors row with the color switch is flipped
        redResetButton.setOnCheckedChangeListener{ _, isChecked->
            Log.d(TAG, "onCreate: redResetButton pressed")
            if (isChecked){
                Log.d(TAG, "setting red to 0")
//                redSlider.isClickable(false)
                redSlider.isEnabled = false
                redTextBox.isEnabled = false
            }

            else{
                Log.d(TAG, "restoring red value")
                redSlider.isEnabled = true
                redTextBox.isEnabled = true
            }
            colorValueViewModel.redResetButton(isChecked)
//            setColorTextBoxIntegers()
            setColorWithinColorDisplayBox()
        }
        greenResetButton.setOnCheckedChangeListener{ _, isChecked->
            Log.d(TAG, "onCreate: greenResetButton pressed")
            if (isChecked){
                Log.d(TAG, "setting red to 0")
                greenSlider.isEnabled = false
                greenTextBox.isEnabled = false
            }
            else{
                Log.d(TAG, "restoring green value")
                greenSlider.isEnabled = true
                greenTextBox.isEnabled = true
            }
            colorValueViewModel.greenResetButton(isChecked)
//            setColorTextBoxIntegers()
            setColorWithinColorDisplayBox()
        }
        blueResetButton.setOnCheckedChangeListener{ _, isChecked->
            Log.d(TAG, "onCreate: blueResetButton pressed")
            colorValueViewModel.blueResetButton(isChecked)
            if (isChecked){
                Log.d(TAG, "setting blue to 0")
                // don't move slider
                blueSlider.isEnabled = false
                blueTextBox.isEnabled = false
            }
            else{
                Log.d(TAG, "restoring blue value")
                // restore slider to its position
                blueSlider.isEnabled = true
                blueTextBox.isEnabled = true
                blueSlider.setProgress(colorValueViewModel.getBlueValue())
            }
//            colorValueViewModel.blueResetButton(isChecked)
//            setColorTextBoxIntegers()
            setColorWithinColorDisplayBox()
        }

        // on change of the color Text value integers
        redTextBox.setOnClickListener {
//        redTextBox.setOnKeyListener{
//            val redText = redTextBox.text
//            val redInt = redText.
//            val redInt = Float.valueOf(redTextBox.text.toFloat() )
//            redSlider.setProgress( redInt )
            val redTextValue = redTextBox.text
            val redTextInteger = Integer.parseInt(redTextValue.toString())
//            val redTextInteger = Int(redTextValue)
//            colorValueViewModel.setRedValue( redTextBox.text)
            colorValueViewModel.setRedValue(redTextInteger)

//            colorValueViewModel.setRedValue(redTextInteger*255)

            // set the number within the redTextBox, in the case of boundary correction
            // set the value shown to be within the max and min of the colors

            setColorTextBoxIntegers("red")
            setColorWithinColorDisplayBox()
            setColorProgressBars("red")
        }
        blueTextBox.setOnClickListener {
            val blueTextValue = blueTextBox.text
            val blueTextInteger = Integer.parseInt(blueTextValue.toString())
            colorValueViewModel.setBlueValue(blueTextInteger)
            //read and print the text box as range of 0-1
//            val blueTextFloat = (Float.toString(blueTextValue) )
//            colorValueViewModel.setBlueValue(blueTextFloat)

            setColorTextBoxIntegers("blue")
            setColorWithinColorDisplayBox()
            setColorProgressBars("blue")

        }
        greenTextBox.setOnClickListener {
            val greenTextValue = greenTextBox.text
            val greenTextInteger = Integer.parseInt(greenTextValue.toString())
            colorValueViewModel.setGreenValue(greenTextInteger*255)
            setColorTextBoxIntegers("green")
            setColorWithinColorDisplayBox()
            setColorProgressBars("green")

        }

        //create the reset button, for setting the color values to 0
        resetButton =  findViewById(R.id.ResetButton)
        resetButton.setOnClickListener {
            // reset color temp buttons
            redResetButton.isChecked = false
            greenResetButton.isChecked = false
            blueResetButton.isChecked = false
            // reset the color integer values
//            colorValueViewModel.resetColorValues()
            colorValueViewModel.resetAllValues()
            // reset the values within the text boxes

//            redTextBox.text = colorValueViewModel.getRedValue().toString()
//            blueTextBox.text = colorValueViewModel.getBlueValue().toString()
//            greenTextBox.text = colorValueViewModel.getGreenValue().toString()
//            redTextBox.setText( colorValueViewModel.getRedValue())
//            blueTextBox.setText(colorValueViewModel.getBlueValue())
//            greenTextBox.setText(colorValueViewModel.getGreenValue()
            redTextBox.setText("0")
            blueTextBox.setText("0")
            greenTextBox.setText("0")

            // reset the slider positions to match the reset integers
            redSlider.setProgress(0)
            greenSlider.setProgress(0)
            blueSlider.setProgress(0)

            // reset the color displayed within the color box
            colorBox.setBackgroundColor(Color.parseColor("#000000"))
//            setColorTextBoxIntegers()
//            setColorWithinColorDisplayBox()



        }
//        resetButton.performClick() // when this is not present, the ColorBox does not update upon startup

        //workaround to fix a bug where, upon app statup, the sliders's progress is not read.
        // so flip each color's disableButton twice to restore, with persistence
        redResetButton.performClick()
        redResetButton.performClick()
        greenResetButton.performClick()
        greenResetButton.performClick()
        blueResetButton.performClick()
        blueResetButton.performClick()

        //save and restore data on application closing/opening

    }

    /*
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertNumbersToColor(): Color {
//    fun convertNumbersToColor(): Int {
        //return the Color for setting the Color sample box
        Log.d(TAG, "getting the color from the convertNumbersToColor function")
        val redValue = colorValueViewModel.getRedValue().toFloat()
        val greenValue = colorValueViewModel.getGreenValue().toFloat()
        val blueValue = colorValueViewModel.getBlueValue().toFloat()

        Log.d(TAG, "color values retrieved")
        //        val returnedColor = Color.rgb(redValue, greenValue, blueValue)
        // return returnedColor
        return Color.valueOf(redValue, greenValue, blueValue)
    }

     */

//    fun setColorWithinColorDisplayBox( newBoxColor: Color ){
    @RequiresApi(Build.VERSION_CODES.O)
    fun setColorWithinColorDisplayBox(){
        // set the new displayed color within the display color box
//        val newColor = this.convertNumbersToColor()
//        colorBox.background = newColor
//        colorBox = findViewById(R.id.ColorBox)
        Log.d(TAG, "attempting to generate color for the colorBox")
//        val newColor = this.convertNumbersToColor()
//        colorBox.color = newColor
//        Log.d(LOG_TAG, "at set colorBox color,  the newColor is: $newColor")
//        colorBox.setBackgroundColor(Color.parseColor(newColor))
//        colorBox.setBackgroundColor(R.color.newColor)
//        colorBox.background = newColor
//        colorBox.setBackgroundColor(Color.parseColor(newColor))
//        colorBox.setBackgroundColor( Int.toString())

        val redInt = colorValueViewModel.getRedValue().toInt()
        val blueInt = colorValueViewModel.getBlueValue().toInt()
        val greenInt = colorValueViewModel.getGreenValue().toInt()

//        val newColor = Color.rgb( redInt, blueInt, greenInt)
        val newColor = Color.rgb( redInt, greenInt, blueInt)

        Log.d(TAG, "at set colorBox color, the newColor is: $newColor")
        colorBox.setBackgroundColor(newColor)

    }
    /* //commented for preference of the convertNumbersToColor function above
    fun getSliderValuesToRGB(){
        //get all values of the different color sliders at once
        val redValue = findViewById(R.id.RedSeekBar)
        val greenValue = findViewById(R.id.GreenSeekBar)
        val blueValue = findViewById(R.id.BlueSeekBar)
    }
    */

    //set the values of the RGB internal integers to the text boxes,
    // to the visible integers next to the screen sliders
    // Goal: keep the displayed RGB integers current
    private fun setColorTextBoxIntegers(colorName: String){
        // update the text of the Text boxes displaying the RGB integer values

        // get integers from the viewmodel
        if ( colorName == "red") { redTextBox.setText( (colorValueViewModel.getRedValue()).toString() ) }
        if ( colorName == "green") { greenTextBox.setText( (colorValueViewModel.getGreenValue()).toString() ) }
        if ( colorName == "blue") { blueTextBox.setText(  (colorValueViewModel.getBlueValue()).toString() ) }


        // get integers from viewmodel, and divide by 255
        /*
        if ( colorName == "red") { redTextBox.setText( (colorValueViewModel.getRedValue()/255).toDouble().toString() ) }
        if ( colorName == "green") { greenTextBox.setText( (colorValueViewModel.getGreenValue()/255).toDouble().toString() ) }
        if ( colorName == "blue") { blueTextBox.setText(  (colorValueViewModel.getBlueValue()/255).toDouble().toString() ) }


         */
        //get the double values from viewmodel
        /*
        if ( colorName == "red") { redTextBox.setText( (colorValueViewModel.getRedTextValue()).toString() ) }
        if ( colorName == "green") { greenTextBox.setText( (colorValueViewModel.getGreenTextValue()).toString() ) }
        if ( colorName == "blue") { blueTextBox.setText(  (colorValueViewModel.getBlueTextValue()).toString() ) }

         */
    }

    //set the progress on the color sliders
    private fun setColorProgressBars(colorName : String){
//        redSlider.progress = colorValueViewModel.getRedValue()
        if ( colorName == "red") {redSlider.setProgress( colorValueViewModel.getRedValue()) }
        if ( colorName == "green") { greenSlider.setProgress( colorValueViewModel.getGreenValue()) }
        if ( colorName == "blue"){ blueSlider.setProgress( colorValueViewModel.getBlueValue() ) }
    }


    // TODO: persistence functions, for being able to recall data after closing and opening app
    private fun storeData(){
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
    }
    private fun readStoredData(){

    }

    //persistence for the activity
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume begun")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop begun")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause begun")
    }

//    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
//        super.onSaveInstanceState(savedInstanceState)
//    }

    // data store for activity
}

class ColorRow( colorName: String){
    /* for each color row, will have 3 components
    1. the Slider
    2. the Switch for color disabling
    3. the text box displaying the color value

    allow access to the color value
     */

    //strings for finding the objects' id
    private val sliderString  : String = colorName + "SeekBar"
    private val switchString  : String = colorName + "Switch"
    private val textBoxString : String = colorName + "TextBox"

    // find the actual object by id
//    val slider = findViewById(R.id.sliderString)
//    val switch : Switch = findViewById(R.id.switchString)
//    val textBox : EditText = findViewById(R.id.textBoxString)
}

