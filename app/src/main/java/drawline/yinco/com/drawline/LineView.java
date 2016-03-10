package drawline.yinco.com.drawline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/21.
 */
public class LineView extends View {
    private final static String X_KEY = "Xpos";
    private final static String Y_KEY = "Ypos";

    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String,Integer>>();

    Paint mPaint = new Paint();

    public LineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public LineView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        for (int index=0; index<mListPoint.size(); index++)
        {
            if (index > 0)
            {
                canvas.drawLine(mListPoint.get(index-1).get(X_KEY), mListPoint.get(index-1).get(Y_KEY),
                        mListPoint.get(index).get(X_KEY), mListPoint.get(index).get(Y_KEY), mPaint);
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
            }
        }
    }
    /**
     * @param curX  which x position you want to draw.
     * @param curY  which y position you want to draw.
     * @see all you put x-y position will connect to a line.
     */
    public void setLinePoint(int curX, int curY)
    {
        Map<String, Integer> temp = new HashMap<String, Integer>();
        temp.put(X_KEY, curX);
        temp.put(Y_KEY, curY);
        mListPoint.add(temp);
        invalidate();
    }
}
