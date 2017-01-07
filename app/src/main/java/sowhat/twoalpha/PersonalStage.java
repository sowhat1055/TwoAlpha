package sowhat.twoalpha;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalStage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText mViewTime;
    private TextView mViewFactor;
    private Button mButtonA;
    private int mNumberA = 0;
    private int mNumberC = 0;
    private int mNumberD = 0;
    private int mNumberMiss = 0;
    private int mNumberPT = 0;
    private int mNumberProc = 0;
    private boolean mIsALocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewTime = (EditText) findViewById(R.id.personal_stage_time);
        mViewTime.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateResult();
                }
                return false;
            }
        });
        mViewFactor = (TextView) findViewById(R.id.personal_stage_factor);
        mButtonA = (Button) findViewById(R.id.button_a);
        mButtonA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mIsALocked = !mIsALocked;
                ((Button) v).setTextColor(mIsALocked ? Color.BLACK : Color.WHITE);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personal_stage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            if (mIsALocked) {
                mNumberA = mNumberA + mNumberC + mNumberD + mNumberMiss;
                mButtonA.setText(mNumberA + " " + getString(R.string.point_a));
            } else {
                mNumberA = 0;
                mButtonA.setText(getString(R.string.point_a));
            }

            mNumberC = 0;
            mNumberD = 0;
            mNumberMiss = 0;
            mNumberPT = 0;
            mNumberProc = 0;
            mViewTime.setText("");
            mViewFactor.setText("");
            ((Button) findViewById(R.id.button_c)).setText(getString(R.string.point_c));
            ((Button) findViewById(R.id.button_d)).setText(getString(R.string.point_d));
            ((Button) findViewById(R.id.button_miss)).setText(getString(R.string.point_miss));
            ((Button) findViewById(R.id.button_pt)).setText(getString(R.string.point_pt));
            ((Button) findViewById(R.id.button_proc)).setText(getString(R.string.point_proc));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onButtonClick(View view) {
        Button clickedButton = (Button) view;
        int buttonId = view.getId();

        if (mIsALocked) {
            switch (buttonId) {
                case R.id.button_a:
                    return;
                case R.id.button_c:
                case R.id.button_d:
                case R.id.button_miss:
                    mNumberA--;
                    mButtonA.setText(mNumberA + " " + getString(R.string.point_a));
                    break;
            }
        }

        switch (buttonId) {
            case R.id.button_a:
                mNumberA++;
                clickedButton.setText(mNumberA + " " + getString(R.string.point_a));
                break;
            case R.id.button_c:
                mNumberC++;
                clickedButton.setText(mNumberC + " " + getString(R.string.point_c));
                break;
            case R.id.button_d:
                mNumberD++;
                clickedButton.setText(mNumberD + " " + getString(R.string.point_d));
                break;
            case R.id.button_miss:
                mNumberMiss++;
                clickedButton.setText(mNumberMiss + " " + getString(R.string.point_miss));
                break;
            case R.id.button_pt:
                mNumberPT++;
                clickedButton.setText(mNumberPT + " " + getString(R.string.point_pt));
                break;
            case R.id.button_proc:
                mNumberProc++;
                clickedButton.setText(mNumberProc + " " + getString(R.string.point_proc));
                break;
        }
        updateResult();
    }

    private void updateResult() {
        if (!TextUtils.isEmpty(mViewTime.getText())) {
            double time = Double.valueOf(mViewTime.getText().toString());
            if (time != 0) {
                double factor = (5 * mNumberA + 3 * mNumberC + 1 * mNumberD
                        - 10 * (mNumberMiss + mNumberPT + mNumberProc)) / time;
                mViewFactor.setText(String.format("%.3f", factor));
            }
        }
    }
}
