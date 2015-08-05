package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

	private AsyncTask task;
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
//        LogUtils.d("testtest");
//        return;
//        
//        @LogAnno(value = "")
//        String text = "";
//        task = new AsyncTask<Object, Integer, Bitmap>() {
//
//			@Override
//			protected Bitmap doInBackground(Object... params) {
//				URL url = null;
//				try {
//					url = new URL("http://image.baidu.com/i?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fh.hiphotos.baidu.com%2Fbaike%2Fw%253D268%2Fsign%3Dcc1fa1384d4a20a4311e3bc1a8529847%2F342ac65c1038534374fb7e0b9113b07ecb8065380cd790d8.jpg&thumburl=http%3A%2F%2Fimg5.imgtn.bdimg.com%2Fit%2Fu%3D490382050%2C2805470275%26fm%3D21%26gp%3D0.jpg");
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				}
//				if(null == url)
//					return null;
//				try {
//					URLConnection conn = url.openConnection();
//					conn.setUseCaches(true);
//					conn.setRequestProperty("Content-Type", "image/jpeg");
//					Object obj = conn.getContent();
//					
//					if(obj instanceof Bitmap){
//						return (Bitmap) obj;
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//        	
//			@Override
//			protected void onPostExecute(Bitmap result) {
//				super.onPostExecute(result);
//				if(null == result || isCancelled() || isFinishing())
//					return;
//				findViewById(R.id.tv_back).setBackgroundDrawable(new BitmapDrawable(getResources(), result));;
//				
//			}
//		};
//        
//		task.execute(null, null);
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
    	if(null != task && task.isCancelled()){
    		try {
    			task.cancel(true);
			} catch (Exception e) {
			}
    	}
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
