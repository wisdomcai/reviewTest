package com.dragonest.autochess.google;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.test.testReview.R;

public class MainActivity extends AppCompatActivity {

    private Button _myBtn;
private Button _myBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _myBtn = findViewById(R.id.myBtn);
        _myBtn2 = findViewById(R.id.myBtn2);
        _myBtn.setOnClickListener(view -> Do1());
        _myBtn2.setOnClickListener(view ->Do2());

    }

    ReviewManager mgr = null;
    ReviewInfo reviewInfo =null;

    //此处分为两步，方便演示调用逻辑
    private void Do1() {
        mgr = new  FakeReviewManager(this);
        Task<ReviewInfo> request = mgr.requestReviewFlow();
        request.addOnCompleteListener(task ->{
                if(task.isSuccessful())
                {
                    reviewInfo = task.getResult();
                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
        });
    }

    private void Do2(){
        try {
            Task<Void> flow = mgr.launchReviewFlow(MainActivity.this, reviewInfo);
            if(flow.isSuccessful()){
                flow.addOnSuccessListener(result -> Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show());
            }else{
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {

        }

    }

}
