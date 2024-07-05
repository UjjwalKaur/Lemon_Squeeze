package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Lemonade(
                        instruction = stringResource(id = R.string.lemon_tree_to_lemon),
                        image = painterResource(id = R.drawable.lemon_tree),
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Lemonade(instruction: String, image: Painter, modifier: Modifier = Modifier.fillMaxSize()) {
    var buttonClickCount = 0
    var lemonSqueeze = (2..4).random()
    var proceedToPathway = true
    var pathway by remember { mutableStateOf(1) }
    var imageResource = R.drawable.lemon_tree
    var descriptionString = R.string.lemon_tree
    var instructionString = R.string.lemon_tree_to_lemon
    if(pathway == 1){
        imageResource = R.drawable.lemon_tree
        descriptionString = R.string.lemon_tree
        instructionString = R.string.lemon_tree_to_lemon
        proceedToPathway = true
    } else if (pathway == 2){
        imageResource = R.drawable.lemon_squeeze
        descriptionString = R.string.lemon
        instructionString = R.string.lemon_to_lemonade
        proceedToPathway = true
    } else if (pathway ==3){
        imageResource = R.drawable.lemon_drink
        descriptionString = R.string.lemonade
        instructionString = R.string.lemonade_to_glass
        proceedToPathway = true
    } else if(pathway == 4){
        imageResource = R.drawable.lemon_restart
        descriptionString = R.string.glass
        instructionString = R.string.glass_to_lemon_tree
    } else {
        pathway = 1
    }

    Column {
        Box (modifier = Modifier.background(Color.Yellow)){
            Text(
                text = "Lemonade",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 16.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(160.dp)
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if(pathway != 2){
                        pathway++
                    } else {
                        buttonClickCount++
                    }
                    if(buttonClickCount == lemonSqueeze) pathway++
                          },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_background))
            ){
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = descriptionString)
                )
            }

            Text(
                text = stringResource(id = instructionString),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeMaker() {
    LemonadeTheme {
        Lemonade(
            instruction = stringResource(id = R.string.lemon_tree_to_lemon),
            image = painterResource(id = R.drawable.lemon_tree))
    }
}