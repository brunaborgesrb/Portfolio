package com.example.portfoliomobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.minharotina.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        message = "Happy Birthday Sam!",
                        from = "From Emma"
                    )

                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = message,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Sobre mim:",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = from,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(12.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(10f))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioMobileTheme {
        GreetingImage(
            message = "Bruna Borges",
            from = "Seja bem vindo(a)! Me chamo Bruna, minha jornada na tecnologia está apenas começando, mal posso esperar pelos novos conhecimentos que virão! <3"
        )
    }
}

@Composable
fun PortfolioMobileTheme(content: @Composable () -> Unit) {

}

@Composable
fun LinkText(text: String, url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
            .padding(1.dp),
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ProjectsGallery(projects: List<Project>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Galeria de Projetos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        projects.forEach { project ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.clickable {}
                ) {
                    Image(
                        painter = painterResource(id = project.imageResource),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth(),
                    )
                    Text(
                        text = project.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = project.description,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

data class Project(val name: String, val description: String, val imageResource: Int)

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        val backgroundPainter = painterResource(R.drawable.fundo4) // Imagem de fundo
        val foregroundPainter = painterResource(R.drawable.eu) // Imagem abaixo do texto

        Image(
            painter = backgroundPainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)
        ) {
            GreetingText(
                message = message,
                from = from,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(1.dp))
            Image(
                painter = foregroundPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinkText(
                text = "GitHub",
                url = "https://github.com/brunaborgesrb",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinkText(
                text = "LinkedIn",
                url = "https://www.linkedin.com/in/bruna-borges-098a64200/",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(5.dp))
            ProjectsGallery(
                projects = listOf(
                    Project("Projeto 1", "Portfólio em HTML-CSS", R.drawable.ft),
                ),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }


    }
}
