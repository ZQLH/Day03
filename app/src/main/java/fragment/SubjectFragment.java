package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import utils.UIUtils;

/**
 * Created by nidaye on 2017/7/9.
 */

public class SubjectFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(UIUtils.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(this.getClass().getSimpleName());
        textView.setTextSize(18);
        return textView;
    }
}
