package org.campjoy.identitree.starter.fragments;

import java.io.IOException;

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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TreeInfoFragment extends FragmentBase{
	LinearLayout myGallery;
	Tree tree;
	TextView treeDescription,imageCaption;
	ImageView mapImage;
	LinearLayout widthText, widthValue, heightText, heightValue;
	LinearLayout gowthText, growthValue, sunText, sunValue;
	LinearLayout shapeText, shapeValue,soilTypeText, soilTypeValue;
	LinearLayout leafShapeText, leafShapeValue, lifeSpanText, lifeSpanValue;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v =inflater.inflate(R.layout.fragment_tree_info, container, false);
	
		tree = TreeModel.getInstance(getActivity().getApplicationContext()).getTreeById("american_basswood");
		if(tree !=null)
		{
		treeDescription = (TextView)v.findViewById(R.id.tree_description);
		treeDescription.setText(tree.getDescription());
        myGallery = (LinearLayout)v.findViewById(R.id.mygallery);
        mapImage= (ImageView)v.findViewById(R.id.tree_map_image);
        //TODO remove hard code for tree name
                 
        myGallery.addView(insertPhoto("american_basswood_img_0107.jpg"));
        myGallery.addView(insertPhoto("american_basswood_imgp8744.jpg"));
        myGallery.addView(insertPhoto("american_basswood_imgp8745.jpg"));
        myGallery.addView(insertPhoto("american_basswood_imgp8867.jpg"));
        
        imageCaption = (TextView)v.findViewById(R.id.image_caption);
        imageCaption.setText(tree.getName() + " - " + tree.getScientificName() + "(" + tree.getFamily() + ")");
        
       try {
        	mapImage.setBackgroundDrawable(Drawable.createFromStream(getActivity().getAssets().open("american_basswood_map.jpg"), null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		}
        
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
