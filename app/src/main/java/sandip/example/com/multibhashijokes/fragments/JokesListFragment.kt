package sandip.example.com.multibhashijokes.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import sandip.example.com.multibhashijokes.R

/**
 * A simple [Fragment] subclass.
 *
 */
class JokesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jokes_list, container, false)
    }


}
