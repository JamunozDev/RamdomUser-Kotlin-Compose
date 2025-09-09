package com.jamunoz.devkotlin.ramdomuser_kotlin_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.model.User
import com.jamunoz.devkotlin.ramdomuser_kotlin_compose.ui.theme.RamdomUserKotlinComposeTheme
import com.valentinilk.shimmer.shimmer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RamdomUserKotlinComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                     MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: UserViewModel = hiltViewModel())
{
    val users by viewModel.users.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    MyApp1(onAddClick = {
        viewModel.addUser()
    }, onDeleteClick = {
        viewModel.deleteUser(it)
    },
        users=users,
        isLoading=isLoading)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp1(
    onAddClick: (() -> Unit)?=null,
    onDeleteClick: ((toDelete: User) -> Unit)? = null,
    users:List<User>,
    isLoading:Boolean
    ) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("Simple REST + ROOM") },
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = null
                )
            },
            actions = {
                IconButton(onClick = {
                    //viewModel.addUser()
                    onAddClick?.invoke()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add"
                    )
                }
            }
        )
    })
    { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            var itemCount = users.size
            if (isLoading) itemCount++
            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCard()
                    auxIndex--
                }
                val user = users[auxIndex]
                Card(
                    shape = RoundedCornerShape(8.dp),

                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = rememberImagePainter(
                                data = user.thumbnail,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                    error(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Column(
                            Modifier.weight(1f),
                        ) {
                            Text("${user.name} ${user.lastName}")
                            Text(user.city)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        IconButton(onClick = {
                            onDeleteClick?.invoke(user)
                        }) {
                            Icon(Icons.Filled.Delete, "Remove")
                        }
                    }
                }

            }
        }
    }
}

    @Composable
    fun LoadingCard() {
        Card(
            shape = RoundedCornerShape(8.dp),
            //elevation = 1.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth()
                .testTag("loadingCard")
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Box(modifier = Modifier.shimmer()) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(modifier = Modifier.shimmer()) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(15.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Box(
                                modifier = Modifier
                                    .height(15.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )
                        }
                    }
                }

            }
        }
    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RamdomUserKotlinComposeTheme {
        MyApp1(isLoading = true, users = arrayListOf())
    }
}