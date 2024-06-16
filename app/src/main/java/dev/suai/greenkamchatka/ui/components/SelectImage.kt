package dev.suai.greenkamchatka.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import dev.suai.greenkamchatka.R

@Composable
@Preview
fun SelectImageFromGallery(imageUri: Uri? = null, onImageChanged:(Uri?)->Unit = {}) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        onImageChanged(uri)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {



        Button(onClick = { launcher.launch("image/*") }) {
            Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
        }

        TextButton(onClick = { launcher.launch("image/*")  }) {
            Text(text = "Прикрепить фотографию")
        }
        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = uri).apply(block = fun ImageRequest.Builder.() {
                        transformations(CircleCropTransformation())
                    }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clickable { launcher.launch("image/*") }
            )
        }
    }
}