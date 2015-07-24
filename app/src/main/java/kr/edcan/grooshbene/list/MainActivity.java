package kr.edcan.grooshbene.list;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    SharedPreferences pref1;
    SharedPreferences pref2;
    SharedPreferences.Editor edit1;
    SharedPreferences.Editor edit2;
    //셰어드 프리퍼런스 선언
    Intent intent_plus;
    Button btn;
    int cnt;
    ArrayList<CData> dataArr;
    ListView Listv;
    ArrayAdapter<CData> mAdapter;
    //선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref1 = getSharedPreferences("content", 0);
        pref2 = getSharedPreferences("count", 0);
        edit1 = pref1.edit();
        edit2 = pref2.edit();
        //밑에 수정항목 적기(아이템 추가)
        btn = (Button)findViewById(R.id.plus_btn);
        Listv = (ListView)findViewById(R.id.listv);
        //수정항목 작성 끝
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_plus = new Intent(MainActivity.this, PlusActivity.class);
                startActivityForResult(intent_plus, 200);
            }
        });
        //버튼 온클릭
        dataArr = new ArrayList<CData>();
        mAdapter = new DataAdapter(MainActivity.this, dataArr);
        Listv.setAdapter(mAdapter);
        initList();
        //리스트뷰 선언부
         //추가 코드


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            initList();
        }
    }

    public void initList(){
        mAdapter.clear();
        cnt = pref2.getInt("count",0);
        for(int i=0;i<=cnt;i++) {
            String name = pref1.getString("name" + i, "");
            if( name.isEmpty())
                continue;
            mAdapter.add(new CData(getApplicationContext(), name));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
