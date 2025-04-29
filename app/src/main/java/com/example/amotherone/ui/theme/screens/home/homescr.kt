package com.example.amotherone.ui.themes.screens.home




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.amotherone.R
import com.example.amotherone.navigation.ROUTE_LOGIN
import com.example.amotherone.navigation.ROUTE_REGISTER
import androidx.compose.foundation.layout.Arrangement


@Composable
fun Home_Screen(navController:NavHostController) {

    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White))
    {


        Text("Welcome",
            color = Color.Red,
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text("Welcome",
            color = Color.Magenta,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {navController.navigate(ROUTE_LOGIN)},
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier=Modifier.width(300.dp)
        ) {
            Text("log in",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive
            )
        }
        Button(onClick = {navController.navigate(ROUTE_REGISTER)},
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier=Modifier.width(300.dp)
        ) {
            Text("Register",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive
            )
        }


        }
        /*Image(painter = painterResource(id = R.drawable.pic),
            contentDescription = "man",
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape))
*/


    }




/*

@Preview(showBackground = true)
@Composable
fun Test(){
    Home_Screen(rememberNavController())
}
*/

