package org.campjoy.identitree.starter.popup;

import java.util.ArrayList;
import java.util.List;

import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.GlossaryModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class HotTap implements OnTouchListener, OnDismissListener {
	private final LayoutInflater inflater;
	private final GlossaryModel glossary;

	private PopupWindow popup = null;
	private boolean dismissFlag = false;
	private float popupX = -1f;
	private float popupY = -1f;

	public HotTap(Context activityContext, View v, GlossaryModel glossary) {
		inflater = LayoutInflater.from(activityContext);
		v.setOnTouchListener(this);
		createSpans(v);

		this.glossary = glossary;
	}

	private void createSpans(View v) {
		TextView definitionView = (TextView) v;
		definitionView.setMovementMethod(LinkMovementMethod.getInstance());

		Spannable spans = (Spannable) definitionView.getText();
		Integer[] indices = getIndices(definitionView.getText().toString(), ' ');
		int start = 0;
		int end = 0;
		// to cater last/only word loop will run equal to the length of
		// indices.length
		for (int i = 0; i <= indices.length; i++) {
			ClickableSpan clickSpan = getClickableSpan();
			// to cater last/only word
			end = (i < indices.length ? indices[i] : spans.length());
			String word = definitionView.getText().subSequence(start, end)
					.toString();
			if (glossary.hasTerm(word)) {
				spans.setSpan(clickSpan, start, end,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			start = end + 1;
		}

	}

	private ClickableSpan getClickableSpan() {
		return new ClickableSpan() {
			@Override
			public void onClick(View widget) {
				TextView tv = (TextView) widget;
				String s = tv
						.getText()
						.subSequence(tv.getSelectionStart(),
								tv.getSelectionEnd()).toString();
				if (popup == null) {
					initializeAndShowPopup(tv);
				}
				Log.d("tapped on:", s);
			}

			public void updateDrawState(TextPaint ds) {
				super.updateDrawState(ds);
			}
		};
	}
	
	private void initializeAndShowPopup(View parent)
	{
		if(dismissFlag)
		{
			dismissFlag = false;
			return;
		}
		View popupView = inflater.inflate(R.layout.glossary_popup, null);
		popup = new PopupWindow(popupView, 500, 0);
		popup.setOnDismissListener(HotTap.this);
		popup.setOutsideTouchable(true);

		Drawable bubbleBackground = inflater.getContext().getResources().getDrawable(R.drawable.bubble);
		popup.setBackgroundDrawable(bubbleBackground);
		popup.setWindowLayoutMode(0, LayoutParams.WRAP_CONTENT);
		popup.showAsDropDown(parent, (int) popupX, (int) popupY);
	}

	public static Integer[] getIndices(String s, char c) {
		int pos = s.indexOf(c, 0);
		List<Integer> indices = new ArrayList<Integer>();
		do {
			indices.add(pos);
			pos = s.indexOf(c, pos + 1);
		} while (pos != -1);
		return (Integer[]) indices.toArray(new Integer[0]);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		popupX = event.getX();
		popupY = event.getY();
		System.out.println(event.getX() + " " + event.getY());
		return false;
	}

	@Override
	public void onDismiss() {
		dismissFlag = true;
		popup = null;
	}
}
