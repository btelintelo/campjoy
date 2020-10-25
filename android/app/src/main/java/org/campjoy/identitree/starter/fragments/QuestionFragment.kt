package org.campjoy.identitree.starter.fragments

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.campjoy.identitree.starter.FragmentActivityBase
import org.campjoy.identitree.starter.R
import org.campjoy.identitree.starter.model.QuestionModel
import org.campjoy.identitree.starter.popup.HotTap
import java.io.IOException
import java.io.InputStream
import java.util.*

class QuestionFragment : Fragment() {
    private var firstText = ""
    private var secondText = ""
    private var firstImagePath = ""
    private var secondImagePath = ""
    private var pathsTraversed: String? = ""

    private var pathId = 0

    lateinit var answer1View: View
    lateinit var answer2View: View
    lateinit var firstTextView: TextView
    lateinit var secondTextView: TextView
    lateinit var pathTextView: TextView
    lateinit var firstImageView: ImageView
    lateinit var secondImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        answer1View = view.findViewById(R.id.answer1)
        answer2View = view.findViewById(R.id.answer2)
        firstTextView = view.findViewById<View>(R.id.text_first_question) as TextView
        secondTextView = view.findViewById<View>(R.id.text_second_question) as TextView
        pathTextView = view.findViewById<View>(R.id.text_path) as TextView
        firstImageView = view.findViewById<View>(R.id.image_first_question) as ImageView
        secondImageView = view.findViewById<View>(R.id.image_second_question) as ImageView
        val bundle = this.arguments
        pathId = bundle?.getInt("ID") ?: 0

        pathsTraversed = bundle?.getString("Path") ?: ""
        pathsTraversed += (pathId + 1).toString() + ", "

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = QuestionModel(activity!!.applicationContext)
        firstText = model.questions[pathId].choice1.text
        secondText = model.questions[pathId].choice2.text
        firstTextView.text = firstText
        HotTap(activity, firstTextView)
        secondTextView.text = secondText
        HotTap(activity, secondTextView)
        pathTextView.text = pathsTraversed
        firstImagePath = (pathId + 1).toString() + "a.png"
        secondImagePath = (pathId + 1).toString() + "b.png"
        val am: AssetManager = activity?.assets ?: return
        val mapList = ArrayList(am.list("")?.asList() ?: emptyList())

        firstImageView.setImageDrawable(loadImageDrawable(mapList, am, firstImagePath))
        secondImageView.setImageDrawable(loadImageDrawable(mapList, am, secondImagePath))

        answer1View.setOnClickListener {
            val stringNextId = model.questions[pathId].choice1.nextId
            if (stringNextId != "") {
                showNextQuestion(stringNextId)
            } else {
                showTreeInfo(model.questions[pathId].choice1.treeId)
            }
        }
        answer2View.setOnClickListener {
            val stringNextId = model.questions[pathId].choice2.nextId
            if (stringNextId != "") {
                showNextQuestion(stringNextId)
            } else {
                showTreeInfo(model.questions[pathId].choice2.treeId)
            }
        }
    }

    private fun showNextQuestion(stringNextId: String) {
        // Need to add Paths from ids
        //Accounts for array start index
        val nextId = stringNextId.toInt() - 1
        val questionFragment = QuestionFragment()
        val bundle = Bundle()
        bundle.putInt("ID", nextId)
        bundle.putString("Path", pathsTraversed)
        questionFragment.arguments = bundle
        (activity as FragmentActivityBase?)!!.loadFragment(questionFragment)
    }

    private fun showTreeInfo(treeId: String?) {
        val treeInfoFragment = TreeInfoFragment()
        val bundle = Bundle()
        bundle.putString("TreeId", treeId)
        treeInfoFragment.arguments = bundle
        (activity as FragmentActivityBase?)!!.loadFragment(treeInfoFragment)
    }

    private fun loadImageDrawable(mapList: ArrayList<String>, am: AssetManager, imagePath: String): Drawable? {
        return if (mapList.contains(imagePath)) {
            var ims: InputStream? = null
            try {
                ims = am.open(imagePath)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Drawable.createFromStream(ims, null)
        } else {
            null
        }
    }
}