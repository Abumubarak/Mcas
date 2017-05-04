package rexzen.maps;

/**
 * Created by harishananth on 01/01/17.
 */

import android.content.Intent;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class OpenSplash extends AppCompatActivity {
    ImageView openpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opensplash);
        openpic=(ImageView)findViewById(R.id.openpic);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        //final Animation an2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
        openpic.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //logo.startAnimation(an2);
                finish();
                Intent nextintent = new Intent(OpenSplash.this,optionnav.class);
                startActivity(nextintent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        transit();
    }
    public void transit()
    {
        ImageView logo=(ImageView)findViewById(R.id.openpic);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent=new Intent(OpenSplash.this,optionnav.class);
                startActivity(newintent);
            }
        });

    }
}
