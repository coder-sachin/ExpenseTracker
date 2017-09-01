package np.com.sachinmaharzan.expensetracker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lazyboy on 9/1/17.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    Context context;

    public CustomTextView(Context context) {
        super(context);
        this.context=context;
        setCustomTypeface();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setCustomTypeface();
    }

    public void setCustomTypeface(){

        Typeface typeface=Typeface.DEFAULT.createFromAsset(context.getAssets(),"PTN57F.ttf");
        this.setTypeface(typeface);
    }
}
