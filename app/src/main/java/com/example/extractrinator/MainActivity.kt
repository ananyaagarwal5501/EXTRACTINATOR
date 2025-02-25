
//we want to show thw 3 things: 1. camera button, 2. opening it & capture the image , 3. scanning and extracting
//we will have 2 other icons for the erasing and scanning the text

package com.example.extractrinator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {

    lateinit var result: EditText//we want to show the results in teh result-text-view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val camera = findViewById<ImageView>(R.id.btnCamera)
        val erase = findViewById<ImageView>(R.id.btnErase)
        val copy = findViewById<ImageView>(R.id.btnCopy)

        result = findViewById(R.id.resultTV)
        //actions performed by these variables
        camera.setOnClickListener {
            //action: open up the camera and store the image
            //on clicked image we will run the MLAlgo to detect the text out of it
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)//******************

            if (intent.resolveActivity(packageManager) != null) { //the result of the camera tab
                //if yes, then i want to receive the image  send it for the result extraction
                //Going for the ML Part:***
                startActivityForResult(intent, 123)
            } else {
                //else, display a toast message
                Toast.makeText(this, "Oopsie!, something went wrong:(", Toast.LENGTH_SHORT).show()
            }
        }
        //code for the the 2 buttons
        erase.setOnClickListener {
            result.setText("") //erasing the text, thus empty:""
        }

        copy.setOnClickListener {
            //copy the text to the clipboard of the device
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager //clipboardmanager type variable
            val clip= ClipData.newPlainText("label",result.text.toString()) //study this line and next line of code
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to Clipboard Baby", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //check for the request code
        if (requestCode == 123 && resultCode == RESULT_OK) {
            //now i will squeeze the data and send it to the ML Kit-> take it, scan it, detect it
           //BASICALLY, PREPARED THE INPUT IMAGE HERE FOR THE ML KIT
            val extras= data?.extras
            val bitmap= extras?.get("data") as Bitmap//Converting the image-> BitMap[as the ML Kit can only use some specific types..mentioned in the doc]
            /* now, i will pass this bitmap data into a new fxn,
            A fxn to do purely ML Stuff */

            detectTextUsingML(bitmap){

            }


        }
    }
    //Fxn for the ML Stuff->
    private fun detectTextUsingML(bitmap: Bitmap, function: () -> Unit) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) //copied, instance of 'TextRecognizer', passing the options related to the library you declared a dependency

        val image = InputImage.fromBitmap(bitmap, 0)//copied, calling the input bitmap
        //image is completely ready
        //processing now->
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(this, "Oopsie!, something went wrong:(", Toast.LENGTH_SHORT).show()
            }

    }
}




//in this activity code:
//
//NOTE: ViewBinding >> FindViewById(): when there are about 50-60 ids to work on click on in the app, its not possible to click each and every id separately.
//To connect with the mobile camera: Intents are an important mechanism for interactions between components in Android applications.
//******* val i= Intent(MediaStore.ACTION_IMAGE_CAPTURE)******
//MediaStore.ACTION_IMAGE_CAPTURE: can be different actions of the device.
//resolveActivity() queries the database that I mentioned to see what would handle the Intent.
//packageManager: Package Manager is like a registry. it contains all details of application.
//LENGTH_SHORT & LENGTH_LONG are mapped to time interval of 1 Second (1000mS) & 5 Seconds (5000mS) respectively,
//Going for the ML Part: startActivityForResult()
//startActivityForResult(): a powerful method in Android that allows one activity to start another activity and receive a result back.
//startActivityForResult()->It has deprecated startActivityForResult in favour of registerForActivityResult().
//123: request code, to check if the message/image is got for the evaluation.
//A bitmap: Digital image file format that uses pixels to represent an image. An array of bits that specify the color of each pixel in a rectangular array of pixels


//COMPLETE APP LAYOUT
//1. Splash Screen: welcome screen
//2. Onboarding/Tutorial Screen: 3rd party dependency: to use the tutorial/onboarding screen on our app[summary of all the features that ae are offering]
//3. [IMP]Enabled Jetifier=true (when a 3rd party dependency is not compatible with the androidX, then it enables them to connect)
//4. Main Activity Layout Completed
//5. ML Backed in the Main Activity: startActivityForResult()
//6. override fun onActivityResult
//7. Converting the image-> BitMap
//8. pass this bitmap data into a new fxn to do purely ML Stuff: detectTextUsingML(bitmap)
//9. going inside this fxn, acc. to the document steps: 1.integrate the dependency in gradle, 2.instance of textrecognizer, 3.the clipping part
//10. Dont forget to add the user permissions in the menifest file




//**TASK TO DO: IMPLEMENT THE DEPENDENCY FROM THE DOC TO MAKE IT PUBLISHABLE ON GOOGLE PLAY STORE
//IMPLEMENT ALL THE DIFFERENT LANGUAGE DEPENDENCIES IN THE BUILD GRADLE AND ADD TO THIS APP
//Read about the 3rd party dependency and who makes it
