package rtc.patcharee.unchittha.crosswordgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private MyConstant myConstant;
    private Button word1Button, word2Button, answerButton;
    private TextView wordTextView, levelTextView;
    private int timesAnInt = 0; // หมายถึงข้อ
    private String[][] wordStrings; // Content ของคำใบ้
    private String[][] vocapStrings;    // โจทย์
    private EditText[] editTexts = new EditText[7];
    private String[] strings = new String[7];
    private String[] answerStrings = new String[2];
    private boolean[] booleen = new boolean[]{false, false};
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Bind Widget
        bindWidget();

        setUp();

        buttonController();

    }   // Main Method

    private void buttonController() {

        word1Button.setOnClickListener(this);
        word2Button.setOnClickListener(this);
        answerButton.setOnClickListener(this);

    }

    private void setUp() {

        myConstant = new MyConstant();
        wordStrings = myConstant.getWordStrings();
        wordTextView.setText(wordStrings[timesAnInt][0]);
        vocapStrings = myConstant.getVocapStrings();
        levelTextView.setText("Level = 1");

    }

    private void bindWidget() {

        word1Button = (Button) findViewById(R.id.button);
        word2Button = (Button) findViewById(R.id.button2);
        wordTextView = (TextView) findViewById(R.id.textView4);
        answerButton = (Button) findViewById(R.id.button3);

        int[] idInts = new int[]{R.id.editText, R.id.editText2, R.id.editText3, R.id.editText4,
                R.id.editText5, R.id.editText6, R.id.editText7};
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i] = (EditText) findViewById(idInts[i]);
        }   // for

        levelTextView = (TextView) findViewById(R.id.textView2);
        relativeLayout = (RelativeLayout) findViewById(R.id.relPlay);

    }   //bind Widget

    @Override
    public void onClick(View view) {

        String tag = "4janV1";

        switch (view.getId()) {

            case R.id.button:
                wordTextView.setText(wordStrings[timesAnInt][0]);
                break;
            case R.id.button2:
                wordTextView.setText(wordStrings[timesAnInt][1]);
                break;

            case R.id.button3:

                Log.d("2febV1", "Click Answer");

                for (int i = 0; i < strings.length; i++) {
                    strings[i] = editTexts[i].getText().toString().trim();

                }   //for

                answerStrings[0] = strings[0] + strings[2] + strings[5] + strings[6];
                answerStrings[1] = strings[1] + strings[2] + strings[3] + strings[4];

                Log.d(tag, "answer1 ==> " + answerStrings[0]);
                Log.d(tag, "answer2 ==> " + answerStrings[1]);

                if (answerStrings[0].equals(vocapStrings[timesAnInt][0])) {
                    Log.d(tag, "answer1 true");
                    booleen[0] = true;
                }

                if (answerStrings[1].equals(vocapStrings[timesAnInt][1])) {
                    Log.d(tag, "answer2 true");
                    booleen[1] = true;
                }

                if (booleen[0] && booleen[1]) {
                    //Bingo OK

                    timesAnInt += 1;
                    Log.d(tag, "timeAnInt ==> " + timesAnInt);
                    clearEdittext();
                    levelTextView.setText("Level = " + Integer.toString(timesAnInt + 1));

                    int[] ints = new int[]{R.drawable.lv1, R.drawable.lv2, R.drawable.lv3};

//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        relativeLayout.setBackground(getResources().getDrawable(ints[timesAnInt]));
//                    }


                } else {

                    Log.d("2febV1", "คำตอบผิด");
                    MyAlert myAlert = new MyAlert(this);
                    myAlert.myDialog("คำตอบผิด", "ลองใหม่ คำตอบผิดค่ะ");
                }


                break;


        }   // switch

    }   // onClick

    private void clearEdittext() {

        for (int i=0;i<editTexts.length;i++) {
            editTexts[i].setText("");
        }

    }

}   // Main Class
