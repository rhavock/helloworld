package com.example.rapper.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class MainActivity extends Activity {

	String TAG = "MainActivity.java";

	String popUpContents[];
	PopupWindow popupWindowDogs;
	LinearLayout buttonShowDropDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * initialize pop up window items list
		 */

		// add items on the array dynamically
		// format is DogName::DogID
		List<String> dogsList = new ArrayList<String>();
		dogsList.add("Akita Inu::1");
		dogsList.add("Alaskan Klee Kai::2");
		dogsList.add("Papillon::3");
		dogsList.add("Tibetan Spaniel::4");

		// convert to simple array
		popUpContents = new String[dogsList.size()];
		dogsList.toArray(popUpContents);

		/*
		 * initialize pop up window
		 */
		popupWindowDogs = popupWindowDogs();
		popupWindowDogs.setAnimationStyle(R.style.animationName);

		/*
		 * button on click listener
		 */
		View.OnClickListener handler = new View.OnClickListener() {
			public void onClick(View v) {

				switch (v.getId()) {

				case R.id.buttonShowDropDown:
					// show the list view as dropdown

					popupWindowDogs.showAsDropDown(v, -5, 0);
					break;
				}
			}
		};

		// our button
		buttonShowDropDown = (LinearLayout) findViewById(R.id.buttonShowDropDown);
		buttonShowDropDown.setOnClickListener(handler);
	}

	/*
	 *
	 */
	public PopupWindow popupWindowDogs() {

		// initialize a pop up window type
		PopupWindow popupWindow = new PopupWindow(this);

		// the drop down list is a list view
		ListView listViewDogs = new ListView(this);

		// set our adapter and pass our pop up window contents
		listViewDogs.setAdapter(dogsAdapter(popUpContents));

		// set the item click listener
		listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener());

		// some other visual settings
		popupWindow.setFocusable(true);

		popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

		// set the list view as pop up window content
		popupWindow.setContentView(listViewDogs);

		return popupWindow;
	}

	/*
	 * adapter where the list values will be set
	 */
	private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogsArray) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				// setting the ID and text for every items in the list
				String item = getItem(position);
				String[] itemArr = item.split("::");
				String text = itemArr[0];
				String id = itemArr[1];

				// visual settings for the list item
				TextView listItem = new TextView(MainActivity.this);

				listItem.setText(text);
				listItem.setTag(id);
				listItem.setTextSize(22);
				listItem.setPadding(10, 10, 10, 10);
				listItem.setTextColor(Color.WHITE);

				return listItem;
			}
		};

		return adapter;
	}
}
