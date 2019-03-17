package sandip.example.com.message.utils.permissionUtil

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker

class PermissionRequester(owner: Any) {
    private val mOwner: Any = owner
    private val context: Context = when (owner) {
        is Activity -> owner
        else -> (owner as Fragment).requireContext()
    }

    fun requestPermission(permissions: Array<String>, grantResults: Int) {
        when (mOwner) {
            is Activity -> requestPermissions(mOwner, permissions, grantResults)
            is Fragment -> mOwner.requestPermissions(permissions, grantResults)
        }
    }

    fun isGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED
    }
}