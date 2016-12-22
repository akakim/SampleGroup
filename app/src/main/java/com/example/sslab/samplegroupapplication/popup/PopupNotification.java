package com.example.sslab.samplegroupapplication.popup;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.common.BaseDialog;

import java.util.ArrayList;

/**
 * 어떤 모드에 대한 것들은 enum이 아닌 BINARY형식으로 처리하는 것에 대한 생각이든다.
 * (xml에서 center_vertical | right)와 같은 위치를 표기할때.)
 * 다만 연습이니까 이런식으로 소스코드를 남긴다.
 */
enum PopUpdMode{
    DEFAULT_MODE,
    NOTICE_BORAD_MODE;
}
public class PopupNotification extends BaseDialog {

    View.OnClickListener mListener;
    popUpInteraction     interaction;
    OnCancelListener     onCancelListener;

    PopUpdMode mode = PopUpdMode.DEFAULT_MODE;
    TextView title,message;

    Button popupCloseBtn,popupConfirmBtn;

    ArrayList<Integer> validateItems = new ArrayList<>();

    boolean cancelable = false;
    int     selection  = -1; // in case not Selection;
    int     rootScrollId = -1; //

    public PopupNotification(Context context) {
        super(context);
        init(PopUpdMode.DEFAULT_MODE);
    }

    public PopupNotification(Context context, int theme) {
        super(context, theme);
        init(PopUpdMode.DEFAULT_MODE);
    }

    public PopupNotification(Context context, boolean cancelable,View.OnClickListener listener, OnCancelListener cancelListener) {
        super(context);
        this.cancelable = cancelable;
        onCancelListener = cancelListener;

        init(PopUpdMode.DEFAULT_MODE);
    }

    /**
     * 똑같은 title과 내용이 있다면 setContentView로 View만 바꿔주는게 좋을 것같다.
     *
     * @param mode
     */
    private void init(PopUpdMode mode){
        mListener = null;
        onCancelListener = null;
        interaction = null;
        switch (mode){
            case DEFAULT_MODE:
                setContentView(R.layout.popup_notice);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = view.getId();
                        switch (id){
                            case R.id.popupConfirmBtn:
                                break;
                            case R.id.popupCloseBtn:
                                break;
                        }
                        /**
                         * 만약 스크롤로 이동해야한다면 여기서 할 일은 더이상 없다.
                         * 설령 있다면, scroll하라는 모드를 정할뿐이다.
                         * 즉, 해당 fragment에서 onClick에서 스크롤에 대해서 정의를 내려야한다는것이다.
                         * */

                        if (interaction != null) {
                            if (selection >= 0) {
                                interaction.moveScroll(validateItems.get(selection),rootScrollId);
                            }
                        }

                        dismiss();
                    }
                };
                mListener = listener;
                break;

        }
        title           = ( TextView )findViewById(R.id.popupTitle);
        message         = ( TextView )findViewById(R.id.popupMsg);
        popupCloseBtn   = ( Button )findViewById(R.id.popupCloseBtn);
        popupConfirmBtn = ( Button )findViewById(R.id.popupConfirmBtn);

        popupCloseBtn   .setOnClickListener(mListener);
        popupConfirmBtn .setOnClickListener(mListener);

    }

    public void show(int getLayoutId) {
        super.show();
        if(validateItems.size() != 0)
            selection   = validateItems.indexOf(getLayoutId);
    }

    public void setValidateItems(ArrayList<Integer> items){

        for(Integer item : items)
            validateItems.add(item);

    }
    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setOnClickListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    public void setPopupInteraction(popUpInteraction popupInteraction){
        interaction = popupInteraction;
    }
    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.dismiss();
    }

    @Override
    public void setOnCancelListener(OnCancelListener listener) {
        onCancelListener = listener;
    }

    public interface popUpInteraction{
        public void moveScroll(int layoutId,int rootScrollId);
    }
}
