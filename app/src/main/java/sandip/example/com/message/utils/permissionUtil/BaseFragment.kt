package sandip.example.com.message.utils.permissionUtil

import android.content.Context
import android.support.v4.app.Fragment

/**
 * A simple [Fragment] class,
 * that handle basic fragment lifecycle aware tasks
 */
open class BaseFragment : Fragment() {

    private val permissionManager by lazy { PermissionManager() }

    @Suppress("LeakingThis")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        lifecycle.addObserver(permissionManager, this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionManager.onRequestPermissionsResult(
            requestCode = requestCode,
            permissions = permissions,
            grantResults = grantResults)
    }

    /*
    * low priority permissions
     */
    fun requestAndRun(
        permissions: List<String>,
        failAction: (List<String>) -> Unit,
        action: () -> Unit
    ) {
        permissionManager.requestAndRun(
            permissions = permissions,
            failAction = failAction,
            action = action)
    }

    /*
    * high priority permissions
      */
    fun requestThenRun(
        permissions: List<String>,
        failAction: (List<String>) -> Unit,
        action: () -> Unit
    ) {
        permissionManager.requestThenRun(
            permissions = permissions,
            failAction = failAction,
            action = action)
    }
}