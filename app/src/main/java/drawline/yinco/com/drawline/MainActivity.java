package drawline.yinco.com.drawline;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {

    private static final int MSG_DATA_CHANGE = 0x11;
    private LineView mLineView;
    private Handler mHandler;
    private int mX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineView = (LineView) this.findViewById(R.id.line);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch (msg.what) {
                    case MSG_DATA_CHANGE:
                        mLineView.setLinePoint(msg.arg1, msg.arg2);
                        break;

                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };

        new Thread(){
            public void run() {
                for (int index=0; index<40; index++)
                {
                    Message message = new Message();
                    message.what = MSG_DATA_CHANGE;
                    message.arg1 = mX;
                    message.arg2 = (int)(Math.random()*200);;
                    mHandler.sendMessage(message);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    mX += 30;
                }
            };
        }.start();
    }

}
