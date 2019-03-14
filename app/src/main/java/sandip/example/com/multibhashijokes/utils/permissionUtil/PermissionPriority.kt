package sandip.example.com.multibhashijokes.utils.permissionUtil

import android.content.pm.PackageManager.PERMISSION_GRANTED

abstract class PermissionPriority {

    abstract fun onPermissionResult(permissions: Array<out String>, grantResults: IntArray)

    companion object {
        const val PERMISSION_HIGH = 0
        const val PERMISSION_LOW = 1

        fun create(permission: Int,
                   failAction: (List<String>) -> Unit?,
                   action: () -> Unit?): PermissionPriority {
            return when (permission) {
                PERMISSION_HIGH -> HighPriorityPermission(failAction, action)
                PERMISSION_LOW -> LowPriorityPermission(failAction, action)
                else -> LowPriorityPermission(failAction, action)
            }
        }
    }
}

class HighPriorityPermission(
    val failAction: (List<String>) -> Unit?,
    val action: () -> Unit?
) : PermissionPriority() {

    override fun onPermissionResult(permissions: Array<out String>, grantResults: IntArray) {
        val denied = grantResults.indices.filter { grantResults[it] != PERMISSION_GRANTED }
        if (denied.isEmpty())
            action()
        else
            failAction(denied.map { permissions[it] })
    }
}

class LowPriorityPermission(
    val failAction: (List<String>) -> Unit?,
    val action: () -> Unit?
) : PermissionPriority() {

    override fun onPermissionResult(permissions: Array<out String>, grantResults: IntArray) {
        val denied = grantResults.indices.filter { grantResults[it] != PERMISSION_GRANTED }
        if (denied.isEmpty())
            action()
        else
            failAction(emptyList())
    }
}