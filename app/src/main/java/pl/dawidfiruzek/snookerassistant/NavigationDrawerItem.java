package pl.dawidfiruzek.snookerassistant;

/**
 * Created by fks on 2015-03-30.
 */
public class NavigationDrawerItem {
    private String mTitle;
    private String mSubtitle;
    private int mIcon;

    public NavigationDrawerItem(String title, String subtitle, int icon){
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public int getIcon() {
        return mIcon;
    }
}
