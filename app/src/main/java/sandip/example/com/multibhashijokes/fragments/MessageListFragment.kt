package sandip.example.com.multibhashijokes.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import sandip.example.com.multibhashijokes.AppController

import sandip.example.com.multibhashijokes.R
import sandip.example.com.multibhashijokes.di.Injectable
import sandip.example.com.multibhashijokes.viewModel.MessageListViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MessageListFragment : Fragment(), Injectable {

    private lateinit var viewModel: MessageListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MessageListViewModel::class.java)

        initViewModel(viewModel = viewModel)

        viewModel.init(refID = "value")


    }

    private fun initViewModel(viewModel: MessageListViewModel) {

        viewModel.result.observe(this, Observer { listResource ->
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            Log.e("Size", "Message: $listResource")
            Toast.makeText(AppController.instance, "Message ${Gson().toJson(listResource)}", Toast.LENGTH_LONG).show()


        })
    }

}
