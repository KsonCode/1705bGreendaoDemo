package com.bwie.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bwie.greendaodemo.entity.UserEntity;
import com.bwie.greendaodemo.greendao.DaoMaster;
import com.bwie.greendaodemo.greendao.DaoSession;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster.DevOpenHelper devOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {

        initGreendao();
    }

    /**
     * 初始化greendao数据
     */
    private void initGreendao() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "wdmall.db", null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();

    }

    /**
     * 查询
     * @param view
     */
    public void query(View view) {

        List<UserEntity> userEntities = daoSession.loadAll(UserEntity.class);
        System.out.println("size:"+userEntities.size());
//        System.out.println("name:"+userEntities.get(0).name);

    }

    UserEntity userEntity;
    public void update(View view) {
        userEntity.name ="sdddfdfdf";
        daoSession.update(userEntity);

    }

    public void delete(View view) {

        daoSession.delete(userEntity);
    }

    public void add(View view) {

        userEntity = new UserEntity();
        userEntity.name = "kson";
        userEntity.age = 100;
        daoSession.insert(userEntity);


    }
}
