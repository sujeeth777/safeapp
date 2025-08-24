package com.example.themoneypart // Change this to your actual package name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define colors to match the UI
val ScreenBackground = Color(0xFFF5F5F7)
val CardBackground = Color.White
val PositiveAmountGreen = Color(0xFF4CAF50)
val NegativeAmountRed = Color(0xFFF44336)
val RewardPointsOrange = Color(0xFFFF9800)
val IconBackgroundGreen = Color(0xFFE8F5E9)
val IconBackgroundBlue = Color(0xFFE3F2FD)
val IconBackgroundOrange = Color(0xFFFFF3E0)
val TextPrimary = Color.Black
val TextSecondary = Color.Gray
val DividerColor = Color(0xFFF0F0F0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the theme and the main screen content
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    // Scaffold provides the basic structure like TopAppBar, content area
    Scaffold(
        backgroundColor = ScreenBackground,
        topBar = { HomeTopAppBar() }
    ) { paddingValues ->
        // A scrollable column for all the content
        Column(
            modifier = Modifier
                .padding(paddingValues) // Apply padding from Scaffold
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Space between items
        ) {
            DashboardCard(
                title = "Available Credit",
                value = "INR 125.50",
                valueColor = PositiveAmountGreen,
                iconBackgroundColor = IconBackgroundGreen,
                iconTintColor = PositiveAmountGreen
            )
            DashboardCard(
                title = "Total Orders",
                value = "23",
                valueColor = TextPrimary,
                iconBackgroundColor = IconBackgroundBlue,
                iconTintColor = Color.Blue // A default blue for the cart icon
            )
            DashboardCard(
                title = "Rewards Points",
                value = "1,250",
                valueColor = RewardPointsOrange,
                iconBackgroundColor = IconBackgroundOrange,
                iconTintColor = RewardPointsOrange
            )

            Spacer(modifier = Modifier.height(8.dp))

            RecentActivitySection()
        }
    }
}

@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Home",
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* Handle menu click */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            // "SAM" logo
            Text(
                text = "SAM",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(PositiveAmountGreen, CircleShape)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Spacer to push the minus icon to the end
            Spacer(modifier = Modifier.width(16.dp))

            // Minus icon
            IconButton(onClick = { /* Handle minus click */ }) {
                // A simple Text can be used for the minus symbol
                Text(text = "—", fontSize = 20.sp, color = TextSecondary)
            }
        },
        backgroundColor = CardBackground,
        elevation = 0.dp
    )
}

@Composable
fun DashboardCard(
    title: String,
    value: String,
    valueColor: Color,
    iconBackgroundColor: Color,
    iconTintColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = CardBackground,
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, color = TextSecondary, fontSize = 14.sp)
                Text(
                    text = value,
                    color = valueColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // As requested, using the word "arrow" for the logo
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "arrow", color = iconTintColor, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun RecentActivitySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Recent Activity",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = CardBackground,
            elevation = 2.dp,
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ActivityItem(
                    title = "Order Placed",
                    subtitle = "Downtown Vending",
                    amount = "-INR 120.50",
                    time = "2h ago",
                    amountColor = NegativeAmountRed
                )
                Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
                ActivityItem(
                    title = "Credit Added",
                    subtitle = "Monthly allowance",
                    amount = "+INR 100.00",
                    time = "3d ago",
                    amountColor = PositiveAmountGreen
                )
                Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
                ActivityItem(
                    title = "Order Completed",
                    subtitle = "Fresh Market Vending",
                    amount = "-INR 34.75",
                    time = "1w ago",
                    amountColor = NegativeAmountRed
                )
            }
        }
    }
}

@Composable
fun ActivityItem(
    title: String,
    subtitle: String,
    amount: String,
    time: String,
    amountColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(text = subtitle, color = TextSecondary, fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = amount, fontWeight = FontWeight.Bold, color = amountColor)
            Text(text = time, color = TextSecondary, fontSize = 14.sp)
        }
    }
}

// The Preview function allows you to see your UI in the Android Studio editor
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HomeScreen()
    }
}