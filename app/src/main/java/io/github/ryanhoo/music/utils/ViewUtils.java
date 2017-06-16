package io.github.ryanhoo.music.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.ui.widget.CharacterDrawable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/4/16
 * Time: 4:08 PM
 * Desc: ViewUtils
 */
public class ViewUtils {

    public static void setLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void clearLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static CharacterDrawable generateAlbumDrawable(Context context, String albumName) {
        if (context == null || albumName == null) return null;

     int[] colors = new int[9];             // different background colors for folders like in gmail 

        //Initialize the values of the array
        colors[0] = R.color.red_500;
        colors[1] = R.color.deep_purple_500;
        colors[2] = R.color.light_blue_500;
        colors[3] = R.color.green_500;
        colors[4] = R.color.yellow_500;
        colors[5] = R.color.deep_orange_500;
        colors[6] = R.color.pink_500;
        colors[7] = R.color.indigo_500;
        colors[8] = R.color.cyan_500;

        int randomNum=0;                //initial color index
        SharedPreferences sp = context.getSharedPreferences("colorvalue", Activity.MODE_PRIVATE);
        int colorvalue = sp.getInt("color", 0);     //get last folder color index

        if(colorvalue!=0)
         randomNum=colorvalue;
        if(colorvalue==9) randomNum=0;

        sp = context.getSharedPreferences("colorvalue", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("color", randomNum+1);        // save new color index
        editor.commit();
        return new CharacterDrawable.Builder()
                .setCharacter(albumName.length() == 0 ? ' ' : albumName.charAt(0))
                .setBackgroundColor(ContextCompat.getColor(context, colors[randomNum]))     //set folder background color
                .setCharacterTextColor(ContextCompat.getColor(context, R.color.mp_characterView_textColor))
                .setCharacterPadding(context.getResources().getDimensionPixelSize(R.dimen.mp_characterView_padding))
                .build();

    }
}
