package me.yokeyword.rxsmartsharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import me.yokeyword.rxsmartsharedpreferences.spf.RxSpf_User;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvShow;
    private EditText mEtName;
    private Button mBtnSave, mBtnRemoveName, mBtnExistsName, mBtnGet;

    private RxSpf_User mUserSpf;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserSpf = RxSpf_User.create(this);

        initView();
        initListener();

        // 如果你使用RxBinding
        mSubscriptions.add(RxView.clicks(mBtnSave)
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        return mEtName.getText().toString();
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe(mUserSpf.name().asAction()));
        // 如果不使用RxBinding
//        mUserSpf.name().put(mEtName.getText().toString());
        // 事务操作
//        mUserSpf.edit()
//                .name()
//                .put("")
//                .token()
//                .put(0L)
//                .apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_remove_name:
                mUserSpf.name().remove();
                Toast.makeText(getApplicationContext(), "移除name成功!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exsits_name:
                Toast.makeText(getApplicationContext(), mUserSpf.name().exists() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_get:
                mSubscriptions.add(mUserSpf.name().asObservable()
                        .subscribeOn(Schedulers.io())
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                return "rx" + s;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvShow.setText("name:  " + s);
                            }
                        }));
                break;
        }
    }

    private void initListener() {
        mBtnGet.setOnClickListener(this);
        mBtnRemoveName.setOnClickListener(this);
        mBtnExistsName.setOnClickListener(this);
    }

    private void initView() {
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mEtName = (EditText) findViewById(R.id.et_name);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnRemoveName = (Button) findViewById(R.id.btn_remove_name);
        mBtnExistsName = (Button) findViewById(R.id.btn_exsits_name);
        mBtnGet = (Button) findViewById(R.id.btn_get);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
    }
}
