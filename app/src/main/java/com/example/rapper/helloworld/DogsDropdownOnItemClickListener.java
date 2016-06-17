package com.example.rapper.helloworld;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DogsDropdownOnItemClickListener implements OnItemClickListener {

	String TAG = "DogsDropdownOnItemClickListener.java";
	
	@Override
	public void onItemClick(AdapterView<?> arg0, final View v, int arg2, long arg3) {

		// get the context and main activity to access variables
		Context mContext = v.getContext();
		final MainActivity mainActivity = ((MainActivity) mContext);
		
		// add some animation when a list item was clicked
		/*Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.linear_interpolator);
		fadeInAnimation.setDuration(10);
		v.startAnimation(fadeInAnimation);
		v.animate().translationY(v.getHeight());*/

		TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -(mainActivity.popupWindowDogs.getHeight() * 3), 0);
		translateAnimation.setDuration(300);
		translateAnimation.setFillAfter(true);
		translateAnimation.setFillEnabled(true);
		translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               // mainActivity.popupWindowDogs.setHeight(0);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Context mContext = v.getContext();
                MainActivity mainActivity = ((MainActivity) mContext);
                mainActivity.popupWindowDogs.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
		v.startAnimation(translateAnimation);

		// dismiss the pop up

		
		// get the text and set it as the button text
		String selectedItemText = ((TextView) v).getText().toString();

		TextView txt = (TextView)mainActivity.buttonShowDropDown.findViewById(R.id.texto);
		txt.setText(selectedItemText);
		//mainActivity.buttonShowDropDown.f .setText(selectedItemText);
		
		// get the id
		String selectedItemTag = ((TextView) v).getTag().toString();
		Toast.makeText(mContext, "Dog ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();
		
	}

}
