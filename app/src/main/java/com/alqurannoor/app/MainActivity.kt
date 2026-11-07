package com.alqurannoor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Black = Color(0xFF0B0B0B)
private val Gold = Color(0xFFD4AF37)
private val White = Color(0xFFF5F1E6)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranNoorApp()
        }
    }
}

@Composable
fun QuranNoorApp() {
    var selectedPage by remember { mutableStateOf("الرئيسية") }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "القرآن نور",
                    color = Gold,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "نورٌ يضيء قلبك",
                    color = White,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                    color = Gold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                MenuCard("📖", "القرآن الكريم") {
                    selectedPage = "القرآن الكريم"
                }

                MenuCard("🎧", "الاستماع إلى القراء") {
                    selectedPage = "الاستماع إلى القراء"
                }

                MenuCard("🤲", "الأذكار والأدعية") {
                    selectedPage = "الأذكار والأدعية"
                }

                MenuCard("⭐", "المفضلة") {
                    selectedPage = "المفضلة"
                }

                MenuCard("⚙️", "الإعدادات") {
                    selectedPage = "الإعدادات"
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = if (selectedPage == "الرئيسية") {
                        "اللهم اجعل القرآن ربيع قلوبنا"
                    } else {
                        "القسم المختار: $selectedPage"
                    },
                    color = Gold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MenuCard(
    emoji: String,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B1B1B)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.width(18.dp))

            Text(
                text = title,
                color = Gold,
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
