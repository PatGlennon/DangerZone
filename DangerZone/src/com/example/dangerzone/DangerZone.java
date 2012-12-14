package com.example.dangerzone;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class DangerZone extends TabActivity {
	static TabHost tabHost;
	private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_zone);
        mContext = this;
        
        tabHost = getTabHost();
        Resources res = mContext.getResources();
        Drawable about = res.getDrawable(android.R.drawable.ic_menu_help);
        Drawable map = res.getDrawable(android.R.drawable.ic_menu_mapmode);
        Drawable danger = res.getDrawable(android.R.drawable.ic_dialog_alert);
        
        createTab("Map",DangerMap.class,map);
        createTab("DZones",DangerZones.class,danger);
        createTab("About",AboutActivity.class, about);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_danger_zone, menu);
        return true;
    }
    
    @SuppressWarnings("rawtypes")
	private void createTab(String name, Class className, Drawable d){
    	TabSpec spec = tabHost.newTabSpec(name);
        spec.setIndicator(null, d);
        Intent intent = new Intent(this, className);
        spec.setContent(intent);
        
        tabHost.addTab(spec);
    }
}
