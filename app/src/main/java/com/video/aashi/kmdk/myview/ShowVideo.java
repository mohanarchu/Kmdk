package com.video.aashi.kmdk.myview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;

import com.video.aashi.kmdk.R;

public class ShowVideo extends PopupWindow {

    Context context;
    String files;
    View view;
    public ShowVideo(Context context,String files,View locatoion) {
        super(context);
        this.context = context;
        this.files = files;
        this.view = locatoion;
    }

    public void initiatelayout(boolean bool)
    {


            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.video, null);
            VideoView videoView;
            setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            setOutsideTouchable(true);
            setFocusable(true);

            videoView  =(VideoView)layout.findViewById(R.id.playVideo);
            ImageView close =(ImageView)layout.findViewById(R.id.closeb);
            videoView.setVideoPath(files);
            setContentView(layout);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            videoView.start();
            showAtLocation(view,Gravity.CENTER,0,0);


    }


    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }
}
