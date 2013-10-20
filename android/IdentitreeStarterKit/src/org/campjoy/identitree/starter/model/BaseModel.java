package org.campjoy.identitree.starter.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public abstract class BaseModel {

	protected BaseModel(final Context applicationContext, final String fileName) {

		InputStream file = loadFile(applicationContext, fileName);
		if (file == null) {
			return;
		}

		loadModel(file);
		try {
			file.close();
		} catch (IOException ex) {

		}
	}

	private InputStream loadFile(Context applicationContext, String fileName) {
		InputStream file = null;
		try {
			file = applicationContext.getAssets().open(fileName);
		} catch (IOException e) {
		}

		return file;
	}

	private void loadModel(InputStream file) {
		String json = readJsonFromFile(file);
		parseJson(json);
	}

	private String readJsonFromFile(InputStream file) {
		String info = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					file));
			StringBuilder builder = new StringBuilder();

			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				line = reader.readLine();
			}

			info = builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return info;
	}

	protected abstract void parseJson(String json);
}
