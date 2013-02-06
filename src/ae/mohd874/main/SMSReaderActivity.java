package ae.mohd874.main;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.MotionEvent;
import android.widget.TextView;

/*
 * This Example was found on: http://androidsourcecode.blogspot.com/2010/10/android-reading-inbox-sms.html
 */
public class SMSReaderActivity extends Activity {
	
	private TextView view;
	
	private float mx, my;
//    private float curX, curY;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
    	super.onCreate(savedInstanceState);
        view = new TextView(this);
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,null);
        StringBuffer sms = new StringBuffer();
        sms.append("<h1>Header</h1>\n");
        
//        while (cur.moveToNext()) {
//            sms += "From :" + cur.getString(2) + " : " + cur.getString(11)+"\n";         
//        }
        
        for (int i=0; i < 50; i++){
        	cur.moveToNext();
        	for(int y=0; y<cur.getColumnCount(); y++){
        		sms.append("Col " + y + ": " + cur.getString(y) + " || ");
        	}
        	sms.append("\n");
        }
        view.setText(sms);
        setContentView(view);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                curX = event.getX();
                curY = event.getY();
                view.scrollBy(0, (int) (my - curY));
//                mx = curX;
                my = curY;
                break;
            case MotionEvent.ACTION_UP:
//                curX = event.getX();
                curY = event.getY();
                view.scrollBy(0, (int) (my - curY));
                break;
        }

        return true;
    }
}