package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.InviteEvent;
import com.crashlytics.android.answers.RatingEvent;
import com.digits.sdk.android.models.Invite;
import com.example.sslab.samplegroupapplication.R;

public class AnswersActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet2);

        // TODO: Use your own attributes to track content views in your app
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Tweet")
                .putContentType("Video")
                .putContentId("1234")
                .putCustomAttribute("Favorites Count", 20)
                .putCustomAttribute("Screen Orientation", "Landscape"));

        findViewById(R.id.btn_invite).setOnClickListener( this );
        findViewById(R.id.btn_rating).setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_invite:
                Answers.getInstance().logInvite(new InviteEvent().putMethod("sample!"));
                break;
            case R.id.btn_rating:
                RatingEvent ratingEvent = new RatingEvent();
                ratingEvent.putContentName("putcontentName");
                ratingEvent.putContentId("1111");
                ratingEvent.putContentType("ButtonType");
                Answers.getInstance().logRating(ratingEvent);
                break;
        }
    }
}
