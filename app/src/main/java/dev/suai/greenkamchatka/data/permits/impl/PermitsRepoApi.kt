package dev.suai.greenkamchatka.data.permits.impl

import android.util.Log
import dev.suai.greenkamchatka.TAG
import dev.suai.greenkamchatka.data.permits.Permit
import dev.suai.greenkamchatka.data.permits.PermitsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PermitsRepoApi (private val permitsService: PermitsService) :PermitsRepo {
    override suspend fun sendPermit(permit: Permit) {
        withContext(Dispatchers.IO) {
            Log.e(TAG, "sendPermit: "+ permitsService.sendPermit(permit).execute().body()?.success)
        }
    }
}