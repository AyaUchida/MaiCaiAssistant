package com.univerindream.maicaiassistant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.JsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.univerindream.maicaiassistant.MHData
import com.univerindream.maicaiassistant.MCSolution
import com.univerindream.maicaiassistant.databinding.FragmentHelpBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.helpSetDefault.setOnClickListener {
            MHData.curMCSolutionJSON = ""

            loadData()
        }
        binding.helpSave.setOnClickListener {
            val data = binding.helpSteps.text.toString()

            try {
                GsonUtils.fromJson(data, MCSolution::class.java)
                MHData.curMCSolutionJSON = data
                ToastUtils.showLong("保存成功")
            } catch (e: Exception) {
                ToastUtils.showLong("JSON 数据非法")
            }
        }

        loadData()

    }

    fun loadData() {
        binding.helpSteps.setText(JsonUtils.formatJson(MHData.curMCSolutionJSON))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}