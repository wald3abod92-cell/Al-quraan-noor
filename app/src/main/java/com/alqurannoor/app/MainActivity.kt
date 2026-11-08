
package com.alqurannoor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
private val Panel = Color(0xFF1B1B1B)

private val surahs = listOf(
    "الفاتحة","البقرة","آل عمران","النساء","المائدة","الأنعام","الأعراف","الأنفال","التوبة","يونس",
    "هود","يوسف","الرعد","إبراهيم","الحجر","النحل","الإسراء","الكهف","مريم","طه",
    "الأنبياء","الحج","المؤمنون","النور","الفرقان","الشعراء","النمل","القصص","العنكبوت","الروم",
    "لقمان","السجدة","الأحزاب","سبأ","فاطر","يس","الصافات","ص","الزمر","غافر",
    "فصلت","الشورى","الزخرف","الدخان","الجاثية","الأحقاف","محمد","الفتح","الحجرات","ق",
    "الذاريات","الطور","النجم","القمر","الرحمن","الواقعة","الحديد","المجادلة","الحشر","الممتحنة",
    "الصف","الجمعة","المنافقون","التغابن","الطلاق","التحريم","الملك","القلم","الحاقة","المعارج",
    "نوح","الجن","المزمل","المدثر","القيامة","الإنسان","المرسلات","النبأ","النازعات","عبس",
    "التكوير","الانفطار","المطففين","الانشقاق","البروج","الطارق","الأعلى","الغاشية","الفجر","البلد",
    "الشمس","الليل","الضحى","الشرح","التين","العلق","القدر","البينة","الزلزلة","العاديات",
    "القارعة","التكاثر","العصر","الهمزة","الفيل","قريش","الماعون","الكوثر","الكافرون","النصر",
    "المسد","الإخلاص","الفلق","الناس"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { QuranNoorApp() }
    }
}

@Composable
fun QuranNoorApp() {
    var page by remember { mutableStateOf("الرئيسية") }
    var selectedSurah by remember { mutableStateOf("الفاتحة") }

    BackHandler(enabled = page != "الرئيسية") {
        page = if (page.startsWith("سورة:")) "القرآن الكريم" else "الرئيسية"
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize(), color = Black) {
            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(24.dp))
                Text("القرآن نور", color = Gold, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text("نورٌ يضيء قلبك", color = White, fontSize = 15.sp)
                Spacer(Modifier.height(20.dp))

                if (page != "الرئيسية") {
                    MenuCard("‹", "رجوع") {
                        page = if (page.startsWith("سورة:")) "القرآن الكريم" else "الرئيسية"
                    }
                    Spacer(Modifier.height(12.dp))
                }

                when {
                    page == "الرئيسية" -> {
                        Text("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", color = Gold, fontSize = 22.sp, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(24.dp))
                        MenuCard("📖", "القرآن الكريم") { page = "القرآن الكريم" }
                        MenuCard("🎧", "الاستماع إلى القراء") { page = "الاستماع إلى القراء" }
                        MenuCard("🤲", "الأذكار والأدعية") { page = "الأذكار والأدعية" }
                        MenuCard("⭐", "المفضلة") { page = "المفضلة" }
                        MenuCard("⚙️", "الإعدادات") { page = "الإعدادات" }
                        Spacer(Modifier.height(28.dp))
                        Text("اللهم اجعل القرآن ربيع قلوبنا", color = Gold, textAlign = TextAlign.Center)
                    }

                    page == "القرآن الكريم" -> {
                        Text("اختر السورة", color = Gold, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                        Text("عدد السور: 114", color = White)
                        surahs.forEachIndexed { index, name ->
                            MenuCard("📖", "${index + 1}. سورة $name") {
                                selectedSurah = name
                                page = "سورة: $name"
                            }
                        }
                    }

                    page.startsWith("سورة:") -> {
                        Text("سورة $selectedSurah", color = Gold, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(18.dp))

                        if (selectedSurah == "الفاتحة") {
                            Text(
                                "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ\nالرَّحْمَٰنِ الرَّحِيمِ\nمَالِكِ يَوْمِ الدِّينِ\nإِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ\nاهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ\nصِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
                                color = White,
                                fontSize = 23.sp,
                                lineHeight = 43.sp,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Text("سيتم إضافة النص القرآني الموثوق لهذه السورة في التحديث القادم.", color = White, fontSize = 18.sp, textAlign = TextAlign.Center)
                            Text("نحرص على ربط نص المصحف بمصدر موثوق قبل عرضه.", color = Gold, textAlign = TextAlign.Center)
                        }
                    }

                    page == "الاستماع إلى القراء" ->
                        InfoPage("الاستماع إلى القراء", "سيتم إضافة التلاوات الصوتية الموثوقة وخيارات اختيار القارئ في تحديث قادم.")

                    page == "الأذكار والأدعية" ->
                        InfoPage("الأذكار والأدعية", "قسم الأذكار والأدعية قيد التجهيز.")

                    page == "المفضلة" ->
                        InfoPage("المفضلة", "يمكنك الوصول إلى السور المفضلة هنا بعد تفعيل الحفظ في تحديث قادم.")

                    page == "الإعدادات" ->
                        InfoPage("الإعدادات", "إعدادات التطبيق قيد التجهيز.")
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun InfoPage(title: String, message: String) {
    Text(title, color = Gold, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(16.dp))
    Text(message, color = White, fontSize = 18.sp, textAlign = TextAlign.Center)
}

@Composable
fun MenuCard(emoji: String, title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Row(
            Modifier.fillMaxWidth().padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 24.sp)
            Spacer(Modifier.width(16.dp))
            Text(title, color = Gold, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}
