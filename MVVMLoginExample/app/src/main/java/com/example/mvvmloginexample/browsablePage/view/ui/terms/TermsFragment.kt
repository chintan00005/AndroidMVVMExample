package com.example.mvvmloginexample.browsablePage.view.ui.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.viewmodel.TermsViewModel
import com.example.mvvmloginexample.databinding.FragmentTermsBinding


class TermsFragment : Fragment() {

    private lateinit var termsViewModel: TermsViewModel
    private lateinit var fragmentTermsBinding: FragmentTermsBinding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        termsViewModel =
            ViewModelProvider(this).get(TermsViewModel::class.java)
        fragmentTermsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_terms, container, false)

        termsViewModel.getTerm().observe(viewLifecycleOwner, Observer {
            if(it!=null)
            {
               // fragmentTermsBinding.textShare.text = it.getData()?.firstName
                fragmentTermsBinding.textShare.text ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin sit amet turpis felis. Sed et molestie mi. Mauris faucibus justo id dapibus sodales. Maecenas pretium dui quam, eu luctus odio luctus at. Proin posuere eros eget libero tincidunt ullamcorper sit amet a ex. Ut sagittis, ipsum non rutrum semper, velit nulla lacinia magna, ac malesuada purus orci et lorem. Nullam tempus mollis urna vel sagittis. Praesent ac vestibulum mauris, sed pharetra urna. Proin at nisi accumsan, efficitur magna ut, gravida massa. Cras urna odio, interdum a tellus eget, porttitor ultricies diam.\n" +
                        "\n" +
                        "Fusce ac diam lectus. Suspendisse porta vitae magna quis maximus. Mauris tempus erat ligula, vel tempor mauris porttitor id. Fusce id massa faucibus, imperdiet diam eu, finibus turpis. Sed sagittis, leo aliquet tempor fringilla, ex augue consectetur turpis, non pretium ipsum nisl sit amet purus. Sed sed interdum arcu. Suspendisse mollis massa et luctus fermentum. Integer at interdum dui, eget dictum sapien. Sed vitae imperdiet nisi. Quisque non ex vitae erat cursus commodo.\n" +
                        "\n" +
                        "Cras ac vehicula leo. In sagittis arcu lacus, a commodo orci malesuada eu. Integer in pharetra velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque iaculis quis dolor sit amet venenatis. Praesent mattis nunc et est semper tincidunt. Vestibulum tempus vitae justo ut luctus. Ut gravida consequat sapien, eget faucibus dui sagittis gravida. Pellentesque luctus nulla nec eros euismod dictum. Nulla eros mi, vehicula eget convallis id, lacinia sed velit. Morbi facilisis venenatis urna nec condimentum. Sed rhoncus finibus ultrices. Aliquam congue ligula quis tempor fermentum. Duis vel massa a quam venenatis mollis vel at est. Duis a pretium nisl, quis consequat nulla.\n" +
                        "\n" +
                        "Ut eu libero placerat, aliquam odio sed, viverra massa. Phasellus rhoncus, risus eget posuere rutrum, tortor libero vulputate ipsum, eu hendrerit augue justo mattis nulla. Ut nec nunc a orci scelerisque congue. Mauris et ex ut nunc euismod condimentum ac eget neque. Donec cursus, nulla eu sagittis vulputate, erat purus malesuada tortor, non suscipit metus orci et sapien. Donec ex lorem, efficitur non pulvinar sed, ultrices sit amet metus. Nullam mauris libero, luctus non justo non, maximus vehicula mi. Nunc maximus eget felis in luctus. Nunc imperdiet nunc sit amet tellus facilisis, sed feugiat tortor luctus. Nam suscipit congue tristique. Suspendisse ac est tortor. Nullam accumsan fermentum massa. In hac habitasse platea dictumst. Morbi ac turpis dignissim, dapibus odio in, consectetur arcu. Sed a turpis magna. Donec id semper elit."
            }
        })

        return fragmentTermsBinding.root
    }
}