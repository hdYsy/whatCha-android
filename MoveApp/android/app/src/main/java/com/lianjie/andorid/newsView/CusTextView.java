package com.lianjie.andorid.newsView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.ClipboardManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.lianjie.andorid.R;

@SuppressLint("AppCompatCustomView")
public class CusTextView extends TextView {
	private Context mContext;
	private PopupWindow mPopupWindow; 

	public CusTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

	}


    /**
     * ���� PopupWindow
     */
    private void dismissPopupWindowInstance(){
    	  if (null != mPopupWindow) { 
              mPopupWindow.dismiss(); 
    	  }
    }
    /**
     * ��� PopupWindow ʵ��
     */
    private void getPopupWindowsInstance(){
    	if(mPopupWindow!=null){
    		mPopupWindow.dismiss();
    	}else{
    		initPopuptWindow();
    	}
    }
	/*
     * ����PopupWindow
     */ 
    private void initPopuptWindow() { 
        LayoutInflater layoutInflater = LayoutInflater.from(mContext); 
        View popupWindow = layoutInflater.inflate(R.layout.popup_window, null);
        Button btnCopy = (Button) popupWindow.findViewById(R.id.btnCopy); 
        btnCopy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				setTextColor(android.graphics.Color.BLACK);  //��ʼ��Ϊ��ɫ
				dismissPopupWindowInstance();
				ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(getText());
			}
		});
		mPopupWindow = new PopupWindow(popupWindow, dipTopx(mContext, 50),ViewGroup.LayoutParams.WRAP_CONTENT);
		// ���д��� ����Ҫ
		mPopupWindow.setBackgroundDrawable(getDrawable());
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				setTextColor(android.graphics.Color.BLACK); //ȡ��ѡ��Ϊ��ɫ
			}
		});
    } 
    /**
     * ����һ�� ͸���ı���ͼƬ
     * @return
     */
    private Drawable getDrawable(){
    	ShapeDrawable bgdrawable =new ShapeDrawable(new OvalShape());
    	bgdrawable.getPaint().setColor(getResources().getColor(android.R.color.transparent));
    	return   bgdrawable;
    }
    /**
 	 * ת���ɶ�Ӧ��pxֵ
 	 * @param context
 	 * @param dipValue
 	 * @return
 	 */
 	public static int dipTopx(Context context, float dipValue){  
 		 final float scale = context.getResources().getDisplayMetrics().density;  
         return (int)(dipValue * scale + 0.5f);  
     }  
}
