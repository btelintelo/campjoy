package org.campjoy.identitree.starter.popup;

import java.util.ArrayList;
import java.util.List;

import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.GlossaryModel;
import org.campjoy.identitree.starter.model.Term;

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

public class HotTap implements OnTouchListener {
	private static final String LOG_TAG = HotTap.class.getSimpleName();

	private final LayoutInflater inflater;
	private final GlossaryModel glossary;

	private HotTapBubble popup = null;
	private float popupX = -1f;
	private float popupY = -1f;

	public HotTap(Context activityContext, View v) {
		inflater = LayoutInflater.from(activityContext);
		glossary = GlossaryModel.getInstance();
		v.setOnTouchListener(this);
		createSpans(v);
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
				String s;
				try {
					s = tv.getText()
							.subSequence(tv.getSelectionStart(),
									tv.getSelectionEnd()).toString();
				} catch (Exception ex) {
					Log.e(LOG_TAG, "Error finding hot tap location");
					return;
				}
				if (popup == null) {
					initializeAndShowPopup(tv, s);
				} else {
					popup.show(tv, popupX, popupY);
				}
				Log.d("tapped on:", s);
			}

			public void updateDrawState(TextPaint ds) {
				super.updateDrawState(ds);
			}
		};
	}

	private void initializeAndShowPopup(View parent, String title) {
		Term term = glossary.getTermById(title);
		popup = new HotTapBubble(inflater.getContext(), term.getName(),
				term.getDescription());

		popup.show(parent, popupX, popupY);
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
}
