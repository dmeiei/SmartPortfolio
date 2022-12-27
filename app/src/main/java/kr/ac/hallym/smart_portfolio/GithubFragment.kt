package kr.ac.hallym.smart_portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kr.ac.hallym.smart_portfolio.databinding.FragmentGithubBinding

class GithubFragment : Fragment() {

    lateinit var binding: FragmentGithubBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github, container, false)
//        binding = FragmentGithubBinding.inflate(inflater,container,false)
//
//        binding.webView.webViewClient = WebViewClient()
//        binding.webView.loadUrl("https://github.com/dmeiei")
//
//        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val web = view.findViewById<WebView>(R.id.webView)
        web.webViewClient = WebViewClient()
        web.loadUrl("https://github.com/dmeiei")
    }


}