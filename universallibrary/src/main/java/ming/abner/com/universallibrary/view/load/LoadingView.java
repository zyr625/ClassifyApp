package ming.abner.com.universallibrary.view.load;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class LoadingView extends RelativeLayout{
    public LoadingView(Context context) {
        super(context);
        initView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

    }
}
