package sandip.example.com.message.fragments


import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import sandip.example.com.message.R
import sandip.example.com.message.adapter.MessageAdapter
import sandip.example.com.message.binding.FragmentDataBindingComponent
import sandip.example.com.message.databinding.FragmentMessageListBinding
import sandip.example.com.message.di.Injectable
import sandip.example.com.message.helper.ConverterUtils
import sandip.example.com.message.utils.helperUtils.AppExecutors
import sandip.example.com.message.utils.helperUtils.autoCleared
import sandip.example.com.message.utils.permissionUtil.BaseFragment
import sandip.example.com.message.viewModel.MessageListViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MessageListFragment : BaseFragment(), Injectable {

    private lateinit var viewModel: MessageListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var adapter by autoCleared<MessageAdapter>()

    var binding by autoCleared<FragmentMessageListBinding>()

    var message = " "
    var isBackgroundChange = false

    @Inject
    lateinit var executors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        message = activity?.intent?.getStringExtra("message")?:" "
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_message_list,
            container,
            false,
            dataBindingComponent
        )

        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MessageListViewModel::class.java)

        initViewModel(viewModel = viewModel)

        adapter = MessageAdapter(dataBindingComponent = dataBindingComponent, appExecutors = executors)

        binding.let {
            it.adapter = adapter
            it.lifecycleOwner = this
        }

        requestCall()
    }

    private fun requestCall() {
        val permissionList = listOf(Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS)
        requestThenRun(
            permissions = permissionList,
            action = this::showSuccess,
            failAction = this::showError)
    }

    private fun showSuccess() {
        viewModel.init(refID = "value")
    }

    private fun showError(list: List<String>) {
        Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_LONG).show()
    }

    private fun initViewModel(viewModel: MessageListViewModel) {

        viewModel.result.observe(this, Observer { listResource ->
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.

            listResource?.mapIndexed { index, message ->
                message.header = ConverterUtils().getHour(message.date)
                if(!isBackgroundChange){
                    isBackgroundChange = !ConverterUtils().checkValues(message.body, this.message)
                    message.isChangeBackground = isBackgroundChange
                }
                if (index==0) message.isVisible = true
                else {
                    message.isVisible = ConverterUtils().checkValues(listResource[index-1].header, listResource[index].header)
                }
            }

            binding.count = listResource?.size?:0
            adapter.submitList(listResource)

        })
    }

}
