package me.yokeyword.sample.demo_zhihu.base;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends SupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressed());
    }
}
