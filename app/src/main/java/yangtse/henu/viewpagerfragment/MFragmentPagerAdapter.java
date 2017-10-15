package yangtse.henu.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Yangtse on 2017/10/15.
 */

public class MFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment>fragmentslist;
    public MFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentsList){
        super(fm);
        this.fragmentslist=fragmentsList;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentslist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentslist.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
