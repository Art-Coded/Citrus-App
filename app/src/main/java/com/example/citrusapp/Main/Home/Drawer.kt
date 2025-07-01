package com.example.citrusapp.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import com.example.citrusapp.ui.theme.calm_white
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeDrawer(
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    var AboutExpanded by remember { mutableStateOf(false) }
    var AdministrationExpanded by remember { mutableStateOf(false) }
    var InternationalExpanded by remember { mutableStateOf(false) }
    var LinkagesExpanded by remember { mutableStateOf(false) }
    var SustainabilityExpanded by remember { mutableStateOf(false) }

    var AcademeExpanded by remember { mutableStateOf(false) }
    var OthersExpanded by remember { mutableStateOf(false) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(screenWidth * 0.8f),
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nwssutext),
                            contentDescription = "NwSSU Text Logo",
                            colorFilter = ColorFilter.tint(
                                if (isDarkTheme) Color.White else blue_green
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(50.dp)
                                .fillMaxWidth()
                        )

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo1),
                                contentDescription = "NwSSU Text Logo 1",
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo2),
                                contentDescription = "NwSSU Text Logo 2",
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo3),
                                contentDescription = "NwSSU Text Logo 3",
                                modifier = Modifier
                                    .height(50.dp),
                                colorFilter = if (isDarkTheme) {
                                    ColorFilter.tint(Color.White)
                                } else {
                                    null
                                }
                            )
                        }

                        HorizontalDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { AboutExpanded = !AboutExpanded }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "About",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (AboutExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (AboutExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "The University",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Vision and Mission",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU Official Seal",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU General Mandate",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU History",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Board of Regents",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                // Expandable Row for "Administration"
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { AdministrationExpanded = !AdministrationExpanded }
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Administration",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (AdministrationExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        contentDescription = null,
                                    )
                                }

                                if (AdministrationExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "The University President",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Key Officials",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                    }
                                }
                                // Expandable Row for "International Affairs"
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { InternationalExpanded = !InternationalExpanded }
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "International Affairs",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (InternationalExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        contentDescription = null
                                    )
                                }

                                if (InternationalExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Office of the Director for International Affairs",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                    }
                                }

                                // Expandable Row for "Linkages"
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { LinkagesExpanded = !LinkagesExpanded }
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Linkages",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (LinkagesExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        contentDescription = null
                                    )
                                }

                                if (LinkagesExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Regional Linkages",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "National Linkages",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "International Linkages",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                    }
                                }
                                // Expandable Row for "Sustainability Plan (SDGs)"
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { SustainabilityExpanded = !SustainabilityExpanded }
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Sustainability Plan (SDGs)",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (SustainabilityExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        contentDescription = null
                                    )
                                }

                                if (SustainabilityExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Environmental Sustainability Plan",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Energy and Water Conservation",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Waste Management",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Alumni Affairs",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Gender and Development",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }

                        HorizontalDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { AcademeExpanded = !AcademeExpanded }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Academe",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (AcademeExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (AcademeExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "San Jorge Campus",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Colleges",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "School Calendar (SY: 2024 - 2025)",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }

                        HorizontalDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { OthersExpanded = !OthersExpanded }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Others",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (OthersExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (OthersExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "Student Handbook",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Registrar's Office",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NWSSU Official Website",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
        },
        content = content
    )
}