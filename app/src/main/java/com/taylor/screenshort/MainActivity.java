package com.taylor.screenshort;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.taylor.commonjar.contentProvider.ConstantUtil;
import com.taylor.commonjar.contentProvider.SPHelper;
import com.taylor.screenshort.utils.SnackBarUtil;
import com.taylor.screenshort.view.HintTextView;

import static com.taylor.commonjar.contentProvider.ConstantUtil.BROADCAST_FLOAT_BAR_SERVICE_MODIFIED;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout showFloatViewRL;
    private SwitchCompat showFloarViewSwitch;
    private HintTextView showFloatViewTV;
    private Context mContext;

    private boolean isClickFloat = false;
    private boolean showFloatView = true;
    private boolean isInFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFloatViewRL = (RelativeLayout) findViewById(R.id.show_float_view_rl);
        showFloarViewSwitch = (SwitchCompat) findViewById(R.id.show_float_view_switch);
        showFloatViewTV = (HintTextView) findViewById(R.id.show_float_view_tv);

        showFloarViewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                //UrlCountUtil.onEvent(UrlCountUtil.STATUS_SHOW_FLOAT_WINDOW, isChecked);

                showFloatView = isChecked;
                SPHelper.save(ConstantUtil.SHOW_FLOAT_VIEW, showFloatView);
                mContext.sendBroadcast(new Intent(BROADCAST_FLOAT_BAR_SERVICE_MODIFIED));

                if (isClickFloat && isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(mContext)) {
                        SnackBarUtil.show(buttonView,
                                mContext.getString(R.string.punish_float_problem),
                                mContext.getString(R.string.punish_float_action),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                    Uri.parse("package:" + mContext.getPackageName()));
                                            mContext.startActivity(intent);

                                        } catch (Throwable e) {
                                            SnackBarUtil.show(buttonView, R.string.open_setting_failed_diy);
                                        }
                                    }
                                });
                    } else {
                        SnackBarUtil.show(buttonView, mContext.getString(R.string.punish_float_problem));
                    }
                }
                isInFirst = false;
                showFloatViewTV.setShowHint(!showFloatView);
            }
        });
        showFloatViewRL.setOnClickListener(myOnClickListerner);
    }
    private View.OnClickListener myOnClickListerner = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.show_float_view_rl:
                    isClickFloat = true;
                    showFloarViewSwitch.setChecked(!showFloarViewSwitch.isChecked());
                    break;
                default:
                    break;
            }
        }
    };
}
