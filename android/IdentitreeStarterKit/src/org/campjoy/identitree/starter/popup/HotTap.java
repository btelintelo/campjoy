package org.campjoy.identitree.starter.popup;

import java.util.ArrayList;
import java.util.regex.Matcher;

import org.campjoy.identitree.starter.model.GlossaryModel;
import org.campjoy.identitree.starter.model.Term;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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
		TextView tv = (TextView) v;
		tv.setHighlightColor(Color.TRANSPARENT);
	}

	private void createSpans(View v) {
		TextView definitionView = (TextView) v;
		definitionView.setMovementMethod(LinkMovementMethod.getInstance());

		Spannable spans = (Spannable) definitionView.getText();
		ArrayList<Pair<Integer, Integer>> indices = getIndices(definitionView
				.getText().toString());
		
		for (Pair<Integer, Integer> p : indices)
		{
			ClickableSpan clickSpan = getClickableSpan();
			spans.setSpan(clickSpan, p.first, p.second, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
		Term term = glossary.getTermByLink(title);
		popup = new HotTapBubble(inflater.getContext(), term.getName(),
				term.getDescription());

		popup.show(parent, popupX, popupY);
	}

	public static ArrayList<Pair<Integer, Integer>> getIndices(String s) {
		ArrayList<Matcher> matches = GlossaryModel.getInstance().findMatches(s);

		ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
		for (Matcher match : matches) {
			pairs.add(new Pair<Integer, Integer>(match.start(), match.end()));
		}

		return pairs;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		popupX = event.getX();
		popupY = event.getY();
		return false;
	}
}
