package com.example.simplecalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplecalculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOne.setOnClickListener { appendExp("1", true) }
        binding.tvTwo.setOnClickListener { appendExp("2", true) }
        binding.tvThree.setOnClickListener { appendExp("3", true) }
        binding.tvFour.setOnClickListener { appendExp("4", true) }
        binding.tvFive.setOnClickListener { appendExp("5", true) }
        binding.tvSix.setOnClickListener { appendExp("6", true) }
        binding.tvSeven.setOnClickListener { appendExp("7", true) }
        binding.tvEight.setOnClickListener { appendExp("8", true) }
        binding.tvNine.setOnClickListener { appendExp("9", true) }
        binding.tvZero.setOnClickListener { appendExp("0", true) }
        binding.tvDot.setOnClickListener { appendExp(".", true) }

        binding.tvPlus.setOnClickListener { appendExp("+", false) }
        binding.tvMinus.setOnClickListener { appendExp("-", false) }
        binding.tvMultiply.setOnClickListener { appendExp("*", false) }
        binding.tvDivide.setOnClickListener { appendExp("/", false) }
        binding.tvOpen.setOnClickListener { appendExp("(", false) }
        binding.tvClose.setOnClickListener { appendExp(")", false) }

        binding.tvClear.setOnClickListener {
            binding.tvResult.text = ""
            binding.tvExpression.text = ""
        }

        binding.tvBackspace.setOnClickListener {
            var string = binding.tvExpression.text
            if (string.isNotEmpty())
                binding.tvExpression.text = string.substring(0, string.length - 1)
        }

        binding.tvEqual.setOnClickListener {
            try {
                val exp = ExpressionBuilder(binding.tvExpression.text.toString()).build()
                val result = exp.evaluate()
                val long = result.toLong()
                if (result == long.toDouble()) {
                    binding.tvResult.text = long.toString()
                } else
                    binding.tvResult.text = result.toString()

            } catch (e: Exception) {
                binding.tvResult.text = "Invalid Operation"
            }
        }
    }

    private fun appendExp(string: String, canClear: Boolean) {

        if (binding.tvResult.text.isNotEmpty()) {
            binding.tvExpression.text = ""
        }

        if (canClear) {
            binding.tvResult.text = ""
            binding.tvExpression.append(string)
        } else {
            binding.tvExpression.append(binding.tvResult.text)
            binding.tvExpression.append(string)
            binding.tvResult.text = ""
        }
    }
}