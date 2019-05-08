package com.onlab.gymapp.Training

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : DialogFragment(){

    private lateinit var listener: TrainingCreatedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            listener = if (targetFragment != null) {
                targetFragment as TrainingCreatedListener
            } else {
                activity as TrainingCreatedListener
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        dialog.setTitle("Edzés hozzáadása")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fm_training_type.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Súlyzós", "Kardió", "Nyújtás")
        )

        btnSave.setOnClickListener {
            val selectedType = when (fm_training_type.selectedItemPosition) {
                0 ->Training_Type.Sulyzos_edzes
                1 ->Training_Type.Kardio
                2 ->Training_Type.Nyujtas
                else ->Training_Type.Sulyzos_edzes
            }
            if(!(fm_duration.text.isEmpty()) && !(fm_kcal.text.isEmpty())) {
                listener.onTrainingCreated(
                    Training(null,
                        selectedType.toString(),
                        fm_duration.text.toString().toInt(),
                        fm_kcal.text.toString().toInt()
                    )
                )
                dismiss()
            }
            else {
                if(fm_duration.text.isEmpty()) {
                    fm_duration.setError("Kitöltendő mező")
                }
                else {
                    fm_kcal.setError("Kitöltendő mező")
                }
            }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

    }


    interface TrainingCreatedListener {
        fun onTrainingCreated(training: Training)
    }

}
