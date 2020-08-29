package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList=new ArrayList<>();
    private final HashMap<Fragment,Integer> mFragments = new HashMap<>();
    private final HashMap<String,Integer> mFragmentsNumbers = new HashMap<>();
    private final HashMap<Integer,String> mFragmentsNames = new HashMap<>();

    public SectionsStatePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String fragmentName){
        mFragmentList.add(fragment);
        mFragments.put(fragment,mFragmentList.size()-1);
        mFragmentsNumbers.put(fragmentName,mFragmentList.size()-1);
        mFragmentsNames.put(mFragmentList.size()-1,fragmentName);
    }

    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentsNumbers.containsKey(fragmentName)){
            return mFragmentsNumbers.get(fragmentName);
        }else{
            return null;
        }
    }

    public Integer getFragmentNumber(Fragment fragment){
        if(mFragments.containsKey(fragment)){
            return mFragments.get(fragment);
        }else{
            return null;
        }
    }

    public String getFragmentName(Integer fragmentNumber){
        if(mFragmentsNames.containsKey(fragmentNumber)){
            return mFragmentsNames.get(fragmentNumber);
        }else{
            return null;
        }
    }
}
