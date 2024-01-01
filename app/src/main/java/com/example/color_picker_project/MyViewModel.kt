package com.example.color_picker_project

import androidx.lifecycle.ViewModel

const val STARTING_COLOR_VALUE = 0
const val STARTING_TEXT_VALUE = 0.0
const val COLOR_SEEKBAR_MIN_VALUE = 0
const val COLOR_SEEKBAR_MAX_VALUE = 255
const val MAX_COLOR_VALUE = COLOR_SEEKBAR_MAX_VALUE
const val MIN_COLOR_VALUE = COLOR_SEEKBAR_MIN_VALUE
const val MAX_TEXT_VALUE = 1.0
const val MIN_TEXT_VALUE = 0.0
class MyViewModel : ViewModel(){

    //sets the color values
    private var redValue = STARTING_COLOR_VALUE
    private var blueValue = STARTING_COLOR_VALUE
    private var greenValue = STARTING_COLOR_VALUE

    private var tempRedValue = STARTING_COLOR_VALUE
    private var tempBlueValue = STARTING_COLOR_VALUE
    private var tempGreenValue = STARTING_COLOR_VALUE

    // boolean swithces that hold the value
    private var redDisabled = true
    private var greenDisabled = true
    private var blueDisabled = true

    private var redText = STARTING_TEXT_VALUE
    private var greenText = STARTING_TEXT_VALUE
    private var blueText = STARTING_TEXT_VALUE

    //get,return the values of the colors
    fun getRedValue(): Int{
        return redValue
    }
    fun getBlueValue(): Int{
        return blueValue
    }
    fun getGreenValue(): Int{
        return greenValue
    }
    fun getRedTextValue() : Double{
        return redText
    }
    fun getGreenTextValue(): Double{
        return greenText
    }
    fun getBlueTextValue(): Double{
        return blueText
    }

    fun resetColorValues(){
        redValue = STARTING_COLOR_VALUE
        blueValue = STARTING_COLOR_VALUE
        greenValue = STARTING_COLOR_VALUE

        redText = STARTING_TEXT_VALUE
        greenText = STARTING_TEXT_VALUE
        blueText = STARTING_TEXT_VALUE
    }

    fun resetAllValues(){ // when the big RESET button is pressed
        resetColorValues()
        tempRedValue = STARTING_COLOR_VALUE
        tempGreenValue = STARTING_COLOR_VALUE
        tempBlueValue = STARTING_COLOR_VALUE
        redDisabled = false
        greenDisabled = false
        blueDisabled = false
    }
    // set new color values
    fun setRedValue( newRedValue: Int){


        redValue = newRedValue
        if ( redValue > MAX_COLOR_VALUE){ // exceeding the max
            redValue = MAX_COLOR_VALUE
        }
        // below the minimum
        if ( redValue < MIN_COLOR_VALUE){
            redValue = MIN_COLOR_VALUE
        }
        //check that color switch is enabled
        // if true, keep color at 0
        if ( redDisabled){
            redValue = STARTING_COLOR_VALUE
        }
        redText = (redValue / 255).toDouble()
    }
    fun setGreenValue( newGreenValue: Int){
        greenValue = newGreenValue
        if ( greenValue > MAX_COLOR_VALUE){ // exceeding the max
            greenValue = MAX_COLOR_VALUE
        }
        // below the minimum
        if ( greenValue < MIN_COLOR_VALUE){
            greenValue = MIN_COLOR_VALUE
        }
        //check that color switch is enabled
        if ( greenDisabled){
            greenValue = STARTING_COLOR_VALUE
        }
        greenText = (greenValue/255).toDouble()

    }
    fun setBlueValue ( newBlueValue: Int){
        blueValue = newBlueValue
        if ( blueValue > MAX_COLOR_VALUE){ // exceeding the max
            blueValue = MAX_COLOR_VALUE
        }
        // below the minimum
        if ( blueValue < MIN_COLOR_VALUE){
            blueValue = MIN_COLOR_VALUE
        }
        //check that color switch is enabled
        if ( blueDisabled){
            blueValue = STARTING_COLOR_VALUE
        }
        blueText = (blueValue/255).toDouble()
    }

    //color specific temp-reset buttons, conditionally
    // if button is setting to ON:
    // set the value of the temporary holding color values to the current value
    // and set the current value as Default
    // if the button is setting to OFF:
    // set the current color value to the stored temporary value

    fun redResetButton ( buttonIsOn: Boolean) {
        redDisabled = buttonIsOn
        if (buttonIsOn == true) { // store the value to temporary
            tempRedValue = redValue
            redValue = STARTING_COLOR_VALUE
        } else { // button is restored to off, restore the color value
            redValue = tempRedValue
        }
    }
    fun blueResetButton ( buttonIsOn: Boolean) {
        blueDisabled = buttonIsOn
        if (buttonIsOn == true) { // store the value to temporary
            tempBlueValue = blueValue
            blueValue = STARTING_COLOR_VALUE
        } else { // button is restored to off, restore the color value
            blueValue = tempBlueValue
        }
    }
    fun greenResetButton ( buttonIsOn: Boolean) {
        greenDisabled = buttonIsOn
        if (buttonIsOn == true) { // store the value to temporary
            tempGreenValue = greenValue
            greenValue = STARTING_COLOR_VALUE
        } else { // button is restored to off, restore the color value
            greenValue = tempGreenValue
        }
    }
}