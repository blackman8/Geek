package com.example.log;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MyAdapter adapter = new MyAdapter(this);
        List<String> list = new ArrayList<String>();
        list.add("   Hot：http://geek.csdn.net/hotest");
        list.add("  Full：http://geek.csdn.net/forum/14");
        
        list.add("  Rust：http://geek.csdn.net/forum/8");
        list.add(" Swift：http://geek.csdn.net/forum/12");
        
        list.add("Weekly：http://www.androidweekly.cn/");
        adapter.add(list);
        
        ListView listView = (ListView) findViewById(R.id.list_parent);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    class MyAdapter extends ArrayAdapter<String>{

    	public MyAdapter(Context context) {
			super(context, 0);
		}
    	
    	public void add(List<String> list) {
    		for(String str : list){
    			add(str);
    		}
    	}
    	
		public MyAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}
    	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tView = new TextView(parent.getContext());
			tView.setTextSize(16);
			tView.setPadding(10, 0, 10, 0);
			tView.setGravity(Gravity.CENTER_VERTICAL);
			tView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 180));
			tView.setText(getItem(position));
			
			return tView;
		}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        try {
        	String url = (String) parent.getItemAtPosition(position);
        	if(!TextUtils.isEmpty(url) && url.length() > 7){
        		intent.setData(Uri.parse(url.substring(7)));
        	}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
        if(null != intent.resolveActivity(getPackageManager())){
        	startActivity(intent);
        }else{
        	
        }
	}
}
