package org.campjoy.identitree.starter.fragments;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.campjoy.identitree.starter.FragmentActivityBase;
import org.campjoy.identitree.starter.FragmentBase;
import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.Tree;
import org.campjoy.identitree.starter.model.TreeModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TreeInfoFragment extends FragmentBase{
	LinearLayout myGallery;
	Tree tree;
	TextView treeDescription,imageCaption;
	ImageView mapImage;
	TextView widthText, heightText, heightValue;
	TextView widthValue;
	TextView gowthText, growthValue, sunText, sunValue;
	TextView shapeText, shapeValue,soilTypeText, soilTypeValue;
	TextView leafShapeText, leafShapeValue, lifeSpanText, lifeSpanValue;
	Button start1,start2;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v =inflater.inflate(R.layout.fragment_tree_info, container, false);

		Bundle bundle = this.getArguments(); 
		String id = bundle.getString("TreeId");
		TreeModel model = TreeModel.getInstance();
		tree = model.getTreeById(id);
		if(tree !=null)
		{
			treeDescription = (TextView)v.findViewById(R.id.tree_description);
			treeDescription.setText(tree.getDescription());
			myGallery = (LinearLayout)v.findViewById(R.id.mygallery);
			mapImage= (ImageView)v.findViewById(R.id.tree_map_image);
			//TODO remove hard code for tree name



			try {
				for (String iterable_element : getActivity().getAssets().list("")) {
					if(iterable_element.startsWith(id))
					{
						if(iterable_element.endsWith("map.jpg"))
						{
							try {
								mapImage.setBackgroundDrawable(Drawable.createFromStream(getActivity().getAssets().open("american_basswood_map.jpg"), null));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
						}
						else
						{
							//Add to gallery
							myGallery.addView(insertPhoto(iterable_element));
						}
					}
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			imageCaption = (TextView)v.findViewById(R.id.image_caption);
			imageCaption.setText(tree.getName() + " - " + tree.getScientificName() + "(" + tree.getFamily() + ")");



			widthValue = (TextView)v.findViewById(R.id.widthValue);
			heightValue= (TextView)v.findViewById(R.id.heightValue);
			growthValue= (TextView)v.findViewById(R.id.growthRateValue);
			sunValue= (TextView)v.findViewById(R.id.sunValue);
			shapeValue = (TextView)v.findViewById(R.id.shapeValue);
			soilTypeValue= (TextView)v.findViewById(R.id.soilTypeValue);
			leafShapeValue= (TextView)v.findViewById(R.id.leafShapeValue);
			lifeSpanValue= (TextView)v.findViewById(R.id.lifeSpanValue);



			for (String key : tree.getTable().keySet()) {
				if(key.equalsIgnoreCase("Width(spread)"))
				{
					widthValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Height")))
				{
					heightValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Growth Rate")))
				{
					growthValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Sun")))
				{
					sunValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Shape")))
				{
					shapeValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Soil Type")))
				{
					soilTypeValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Leaf Shape")))
				{
					leafShapeValue.setText(tree.getTable().get(key));
				}
				else if((key.equalsIgnoreCase("Life Span")))
				{
					lifeSpanValue.setText(tree.getTable().get(key));
				}

			}
		}
		
		start1 = (Button) v.findViewById(R.id.button_start1);
		start1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = 0;
				String path = "Path: ";
				QuestionFragment questionFragment = new QuestionFragment();
				
				Bundle bundle = new Bundle();
				bundle.putInt("ID", id);
				bundle.putString("Path", path);
				
				questionFragment.setArguments(bundle);
				((FragmentActivityBase) getActivity()).loadFragment(questionFragment);
				
			}
		});
		start2 = (Button) v.findViewById(R.id.button_start2);
		start2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = 0;
				String path = "Path: ";
				QuestionFragment questionFragment = new QuestionFragment();
				
				Bundle bundle = new Bundle();
				bundle.putInt("ID", id);
				bundle.putString("Path", path);
				
				questionFragment.setArguments(bundle);
				((FragmentActivityBase) getActivity()).loadFragment(questionFragment);
				
			}
		});
		return v;
	}

	View insertPhoto(String imageName){
		Bitmap bm=null;
		try {
			bm = BitmapFactory.decodeStream(getResources().getAssets().open(imageName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
		layout.setLayoutParams(new LayoutParams(250, 250));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(getActivity().getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(220, 220));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		layout.addView(imageView);
		return layout;
	}

	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
		Bitmap bm = null;

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(path, options); 

		return bm;  
	}

	public int calculateInSampleSize(

			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float)height / (float)reqHeight);   
			} else {
				inSampleSize = Math.round((float)width / (float)reqWidth);   
			}   
		}

		return inSampleSize;   
	}
}
