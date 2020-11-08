package com.example.lcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Words> word_list; // words 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // words 데이터베이스 load
        initLoadDB();

        // word 출력
        int index = 0;
        String get_number = Integer.toString(word_list.get(index).getNumber()); // 단어의 번호
        String get_word = word_list.get(index).getWord(); // 단어
        String get_img_name = word_list.get(index).getImg_name(); // 단어 이미지 파일 이름
        String get_img_extension = word_list.get(index).getImg_extension(); // 단어 이미지 파일 확장자명

        TextView tv_word_num = (TextView) findViewById(R.id.word_number);
        TextView tv_word = (TextView) findViewById(R.id.word);
        TextView tv_word_img_name = (TextView) findViewById(R.id.word_img_name) ;
        ImageView iv_word_img = (ImageView) findViewById(R.id.word_img);

        tv_word_num.setText(get_number);
        tv_word.setText(get_word);
        tv_word_img_name.setText(get_img_name + get_img_extension);
        int lid = this.getResources().getIdentifier(get_img_name, "drawable", this.getPackageName());
        iv_word_img.setImageResource(lid) ;

    }

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(this.getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        word_list = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();
    }
}
