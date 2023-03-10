package com.PunicGames.flappyphone

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeResumeLevelActivity : ComponentActivity() {

    var points = 0
    var minutes = 1
    var seconds = 1

    var star1Alpha = 0.2f
    var star2Alpha = 0.2f
    var star3Alpha = 0.2f
    var star4Alpha = 0.2f

    private lateinit var backgroundMusic: MediaPlayer
    private lateinit var buttonEffect: MediaPlayer

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContent {

            var bundle = intent.extras
            points = bundle?.getInt("points")!!;
            minutes = bundle?.getInt("minutes")!!;
            seconds = bundle?.getInt("seconds")!!;

            when(points){
                1 -> Star1()
                2 -> Star2()
                3 -> Star3()
                4 -> Star4()
            }

            ResumeLevelViewContainer()
        }


        backgroundMusic = MediaPlayer.create(this, R.raw.resume_song);
        backgroundMusic.start()
        backgroundMusic.isLooping = true;
        backgroundMusic.setVolume(0.5f, 0.5f);

        buttonEffect = MediaPlayer.create(this, R.raw.button);
        buttonEffect.setVolume(0.6f, 0.6f)
    }

    fun Star1(){
        star1Alpha = 1f
    }

    fun Star2(){
        star1Alpha = 1f
        star2Alpha = 1f
    }

    fun Star3(){
        star1Alpha = 1f
        star2Alpha = 1f
        star3Alpha = 1f
    }

    fun Star4(){
        star1Alpha = 1f
        star2Alpha = 1f
        star3Alpha = 1f
        star4Alpha = 1f
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true, widthDp = 400, heightDp = 800)
    @Composable
    fun ResumeLevelViewContainer(){

        Scaffold(
            content = { ResumeLevelContent()}
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun TimeText(){

        val aux = 150f

        val customTypeface = LocalContext.current.resources.getFont(R.font.marblefont)

        val textPaintStroke = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            style = android.graphics.Paint.Style.STROKE
            textSize = aux
            color = android.graphics.Color.BLACK
            strokeWidth = 13f
            strokeMiter= 10f
            strokeJoin = android.graphics.Paint.Join.ROUND
            textAlign = android.graphics.Paint.Align.CENTER
            typeface = customTypeface
        }

        val textPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            style = android.graphics.Paint.Style.FILL
            textSize = aux
            color = android.graphics.Color.WHITE
            textAlign = android.graphics.Paint.Align.CENTER
            typeface = customTypeface
        }

        var time = minutes.toString() + " : " + seconds.toString()

        Canvas(
            modifier = Modifier
                .fillMaxSize(),
            onDraw = {
                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        time,
                        size.width / 2,
                        size.height / 2 + aux / 3,
                        textPaintStroke
                    )
                    it.nativeCanvas.drawText(
                        time,
                        size.width / 2,
                        size.height / 2 + aux / 3,
                        textPaint
                    )
                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ExitButton(){


        val mContext = LocalContext.current


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.btn),
                contentDescription = "Button",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(40))
                    .border(2.dp, Color.Black, RoundedCornerShape(40))
                    .clickable(
                        onClick = {
                            mContext.startActivity(
                                Intent(
                                    mContext,
                                    ComposeLevelSelectionActivity::class.java
                                )
                            );

                            backgroundMusic.stop()
                            backgroundMusic.release()

                            buttonEffect.seekTo(0)
                            buttonEffect.start()
                        }
                    )
            )

            val aux = 150f

            val customTypeface = LocalContext.current.resources.getFont(R.font.marblefont)

            val textPaintStroke = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                style = android.graphics.Paint.Style.STROKE
                textSize = aux
                color = android.graphics.Color.BLACK
                strokeWidth = 13f
                strokeMiter= 10f
                strokeJoin = android.graphics.Paint.Join.ROUND
                textAlign = android.graphics.Paint.Align.CENTER
                typeface = customTypeface
            }

            val textPaint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                style = android.graphics.Paint.Style.FILL
                textSize = aux
                color = android.graphics.Color.WHITE
                textAlign = android.graphics.Paint.Align.CENTER
                typeface = customTypeface
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize(),
                onDraw = {
                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            "Exit",
                            size.width / 2,
                            size.height / 2 + aux / 3,
                            textPaintStroke
                        )
                        it.nativeCanvas.drawText(
                            "Exit",
                            size.width / 2,
                            size.height / 2 + aux / 3,
                            textPaint
                        )
                    }
                }
            )

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ResumeLevelContent(){

        ImageBackground()

        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
            ) {

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    //.background(Color.Green)
            ) {

                //Blank 1
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
                ){

                }

                //Image logo
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Green)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.level_resume),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )


                }

                //Blank 2
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
                ){

                }

                //Stars
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Green)
                ){
                    //Star 1
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            //.background(Color.Yellow)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.estrella),
                            contentDescription = "Star",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                            alpha = star1Alpha
                        )

                    }

                    //Star 2
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            //.background(Color.Red)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.estrella),
                            contentDescription = "Star",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                            alpha = star2Alpha
                        )

                    }

                    //Star 3
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            //.background(Color.Yellow)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.estrella),
                            contentDescription = "Star",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                            alpha = star3Alpha
                        )

                    }

                    //Star 4
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            //.background(Color.Red)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.estrella),
                            contentDescription = "Star",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                            alpha = star4Alpha
                        )

                    }
                }

                //Blank 3
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
                ){

                }

                //Time
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Green)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "Time",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(2f)
                    ) {
                        TimeText()
                    }



                }

                //Blank 4
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
                ){

                }

                //Button exit
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Green)
                ){
                    ExitButton()
                }

                //Blank 5
                Row(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
                ){

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    //.background(Color.Blue)
            ) {

            }
        }

    }

}

