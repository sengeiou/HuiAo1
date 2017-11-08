package com.myp.huiao.widget.inputdialogfragment;

/**
 * Created by showzeng on 17-8-11.
 * Email: kingstageshow@gmail.com
 * Github: https://github.com/showzeng
 */

public interface DialogFragmentDataCallback {

    String getCommentText();

    void setCommentText(String commentTextTemp);

    void sendText(String text);

}
