package org.campjoy.identitree.starter2020.fragments

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.campjoy.identitree.starter2020.FragmentActivityBase
import org.campjoy.identitree.starter2020.R
import org.campjoy.identitree.starter2020.model.Tree
import org.campjoy.identitree.starter2020.model.TreeModel
import java.io.IOException


class TreeInfoFragment : Fragment() {
    lateinit var start1: Button
    lateinit var start2: Button
    lateinit var gallery: RecyclerView
    lateinit var imageCaption: TextView
    lateinit var mapImage: ImageView
    lateinit var treeDescription: TextView
    lateinit var widthValue: TextView
    lateinit var heightValue: TextView
    lateinit var growthValue: TextView
    lateinit var sunValue: TextView
    lateinit var shapeValue: TextView
    lateinit var soilTypeValue: TextView
    lateinit var leafShapeValue: TextView
    lateinit var lifeSpanValue: TextView
    var treeId: String? = null

    @android.annotation.SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tree_info, container, false)
        val bundle = this.arguments
        treeId = bundle?.getString("TreeId")

        start1 = v.findViewById<View>(R.id.button_start1) as Button
        start2 = v.findViewById<View>(R.id.button_start2) as Button
        treeDescription = v.findViewById<View>(R.id.tree_description) as TextView
        gallery = v.findViewById<View>(R.id.mygallery) as RecyclerView
        mapImage = v.findViewById<View>(R.id.tree_map_image) as ImageView
        imageCaption = v.findViewById<View>(R.id.image_caption) as TextView
        widthValue = v.findViewById<View>(R.id.widthValue) as TextView
        heightValue = v.findViewById<View>(R.id.heightValue) as TextView
        growthValue = v.findViewById<View>(R.id.growthRateValue) as TextView
        sunValue = v.findViewById<View>(R.id.sunValue) as TextView
        shapeValue = v.findViewById<View>(R.id.shapeValue) as TextView
        soilTypeValue = v.findViewById<View>(R.id.soilTypeValue) as TextView
        leafShapeValue = v.findViewById<View>(R.id.leafShapeValue) as TextView
        lifeSpanValue = v.findViewById<View>(R.id.lifeSpanValue) as TextView

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayListOf(start1, start2).forEach { start ->
            start.setOnClickListener {
                val id = 0
                val path = "Path: "
                val questionFragment = QuestionFragment()
                val bundle = Bundle()
                bundle.putInt("ID", id)
                bundle.putString("Path", path)
                questionFragment.arguments = bundle
                (activity as FragmentActivityBase?)!!.loadFragment(questionFragment)
            }
        }
        val model = TreeModel.getInstance()
        val treeId = this.treeId ?: return
        val tree: Tree = model.getTreeById(treeId)
        val assetManager: AssetManager = activity?.assets ?: return

        val assets = assetManager.list("")?.filter { it.startsWith(treeId) } ?: emptyList()

        val mapName = assets.firstOrNull { it.endsWith("map.jpg") }
        if (mapName != null && mapName.isNotEmpty()) {
            mapImage.setBackgroundDrawable(Drawable.createFromStream(assetManager.open(mapName), null))
        }

        gallery.layoutManager = LinearLayoutManager(this.context).apply { orientation = RecyclerView.HORIZONTAL }
        val adapter = TreeGalleryListAdapter(assets.filterNot { it.endsWith("map.jpg") }.sorted(), assetManager)
        gallery.adapter = adapter
        setInformationValues(tree)
    }

    private fun setInformationValues(tree: Tree) {
        treeDescription.text = tree.description
        imageCaption.text = tree.name + " - " + tree.scientificName + "(" + tree.family + ")"
        for (key in tree.table.keys) {
            when {
                key.equals("Width(spread)", ignoreCase = true) -> {
                    widthValue.text = tree.table[key]
                }
                key.equals("Height", ignoreCase = true) -> {
                    heightValue.text = tree.table[key]
                }
                key.equals("Growth Rate", ignoreCase = true) -> {
                    growthValue.text = tree.table[key]
                }
                key.equals("Sun", ignoreCase = true) -> {
                    sunValue.text = tree.table[key]
                }
                key.equals("Shape", ignoreCase = true) -> {
                    shapeValue.text = tree.table[key]
                }
                key.equals("Soil Type", ignoreCase = true) -> {
                    soilTypeValue.text = tree.table[key]
                }
                key.equals("Leaf Shape", ignoreCase = true) -> {
                    leafShapeValue.text = tree.table[key]
                }
                key.equals("Life Span", ignoreCase = true) -> {
                    lifeSpanValue.text = tree.table[key]
                }
            }
        }
    }

    fun insertPhoto(imageName: String?): View {
        var bm: Bitmap? = null
        try {
            bm = BitmapFactory.decodeStream(resources.assets.open(imageName!!))
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        val layout = LinearLayout(activity!!.applicationContext)
        layout.layoutParams = ViewGroup.LayoutParams(250, 250)
        layout.gravity = Gravity.CENTER
        val imageView = ImageView(activity!!.applicationContext)
        imageView.layoutParams = ViewGroup.LayoutParams(220, 220)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageBitmap(bm)
        layout.addView(imageView)
        return layout
    }

    fun decodeSampledBitmapFromUri(path: String?, reqWidth: Int, reqHeight: Int): Bitmap? {
        var bm: Bitmap? = null

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        bm = BitmapFactory.decodeFile(path, options)
        return bm
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            inSampleSize = if (width > height) {
                Math.round(height.toFloat() / reqHeight.toFloat())
            } else {
                Math.round(width.toFloat() / reqWidth.toFloat())
            }
        }
        return inSampleSize
    }
}

class TreeGalleryListItemViewHolder(val view: View, private val assetManager: AssetManager) : RecyclerView.ViewHolder(view) {
    fun setImageName(name: String) {
        val imageView = view.findViewById<ImageView>(R.id.tree_gallery_item_image) ?: return
        imageView.setBackgroundDrawable(Drawable.createFromStream(assetManager.open(name), null))
    }
}


class TreeGalleryListAdapter(private val images: List<String>, private val assetManager: AssetManager) : RecyclerView.Adapter<TreeGalleryListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeGalleryListItemViewHolder = TreeGalleryListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.tree_gallery_item,
                    parent,
                    false
            ), assetManager
    )


    override fun onBindViewHolder(holder: TreeGalleryListItemViewHolder, position: Int) {
        holder.setImageName(images[position])
    }

    override fun getItemCount(): Int = images.size
}