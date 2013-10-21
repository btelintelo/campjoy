package org.campjoy.identitree.starter.model;

import java.io.IOException;
import java.io.InputStream;

import org.campjoy.identitree.starter.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GlossaryAdapter extends BaseAdapter {
	private static final String LOG_TAG = GlossaryAdapter.class.getSimpleName();

	private final LayoutInflater inflater;
	private final GlossaryModel glossary;
	private final String[] keys;

	public GlossaryAdapter(Context applicationContext, GlossaryModel glossary) {
		inflater = LayoutInflater.from(applicationContext);
		this.glossary = glossary;
		keys = glossary.getTerms();
	}

	@Override
	public int getCount() {
		return keys.length;
	}

	@Override
	public Term getItem(int position) {
		return glossary.getTermById(keys[position]);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.glossary_term, null);
			
			holder = new ViewHolder();
//			holder.image = (ImageView) convertView.findViewById(R.id.term_image);
			holder.term = (TextView) convertView.findViewById(R.id.term_name);
			holder.description = (TextView) convertView.findViewById(R.id.term_description);
			
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		Term term = getItem(position);
		
//		try {
//			InputStream is = inflater.getContext().getAssets().open(term.imageLocation);
//			Drawable image = Drawable.createFromStream(is, null);
//			holder.image.setImageDrawable(image);
//		} catch (IOException e) {
//			Log.w(LOG_TAG, "Failed to load image for glossary item " + term.getName());
//			holder.image.setVisibility(View.INVISIBLE);
//		}
		holder.term.setText(term.getName());
		holder.description.setText(term.getDescription());
		
		return convertView;
	}

	private final class ViewHolder {
		public ImageView image;
		public TextView term;
		public TextView description;
	}
}
