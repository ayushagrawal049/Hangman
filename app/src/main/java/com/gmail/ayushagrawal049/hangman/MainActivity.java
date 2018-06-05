package com.gmail.ayushagrawal049.hangman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView hiddentext;

    String entered="";
    TextView wrongtext;
    EditText input;
    View view;
    int count = 8;
    int csss;
    int hsss;
    String css="";
    String hss="";
    TextView cs;
    TextView hs;
    int gcount=0;
    int scount=0;
    String texthint="";
    String temp="";
    String newLetter="&";
    int pcount=25;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texthint = gettext();
        hiddentext = findViewById(R.id.ht);
        wrongtext = findViewById(R.id.eti);
        cs=findViewById(R.id.cs);
        hs=findViewById(R.id.hs);

        hiddentext.setText(encodeString(texthint, "*"));
        SharedPreferences pref = getSharedPreferences("pref", 0);
        pcount=pref.getInt("gc",25);
        hsss=250-10*pcount;
        hs.setText("  High Score-"+hsss);



    }

    private String gettext() {
        String result = "adhere";

        ArrayList<String> words = new ArrayList<String>();
        words.add("ADHERE");
        words.add("ANTICIPATE");
        words.add("CHARACTERITICS");
        words.add("COMPOSE");
        words.add("CRITICAL");
        words.add("DETERMINE");
        words.add("DIFFERENTIATE");
        words.add("ENGAGE");
        words.add("GLARING");
        words.add("HYPOTHESIS");
        words.add("IMMINENT");
        words.add("INEVITABLE");
        words.add("INTUTION");
        words.add("JUSTIFY");
        words.add("OMIT");
        words.add("PRECEDE");
        words.add("REDUNDANT");
        words.add("RELEVANT");
        words.add("TRIVIAL");
        words.add("UNIFORM" +
                "");
        Random generator = new Random();
        int randomIndex = generator.nextInt(words.size());
        result = words.get(randomIndex);
        return result;
    }

    private String encodeString(String target, String letter) {
        String result = "";
        if (letter.contains("*")) {
            for (int i = 0; i < target.length(); i++) {
                result += "_ ";
            }
            return result;
        }

          else if(letter.contains("#")) {
            for(int i = 0; i < target.length(); i++) {
                result += target.charAt(i) + " ";
            }
            return result;
        }
        else {
            for(int i = 0; i < target.length(); i++) {
                if(target.charAt(i) == letter.charAt(0)) {
                    result += target.charAt(i) + " ";
                }
                else if(entered.indexOf(target.charAt(i)) != -1) {
                    result += target.charAt(i) + " ";
                }
                else
                    result += "_ ";
            }
            return result;
        }
    }

    public void enter(View view) {input = (EditText) findViewById(R.id.et);

        newLetter = input.getText().toString();
        input.setText("");
        entered += newLetter.toUpperCase();
        hiddentext.setText(encodeString(texthint, newLetter.toUpperCase()));
        if(win(texthint)) {

            input.setFocusable(false);
            changeImage(999);
            scount=gcount;
            if(gcount<=pcount){
            SharedPreferences pref = getSharedPreferences("pref", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("gc",scount);
            editor.apply();
            }


        }
        if(texthint.indexOf(newLetter.toUpperCase()) == -1) {
            count--;
            gcount++;
            temp += newLetter;
            wrongtext.setText(" "+temp.toUpperCase()+" ");

            changeImage(count);
        }
        if(count == 0) {

            hiddentext.setText(encodeString(texthint, "#"));
            input.setFocusable(false); //no more guesses
        }
        csss=250-10*gcount;
        cs.setText("  Current Score-"+csss);
    }
    private boolean win(String texthint) {
        if(texthint.length() > 0 && entered.length() > 0) {
            for(int i = 0; i < texthint.length(); i++) {
                if(entered.indexOf(texthint.charAt(i)) == -1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void changeImage(int count) { LinearLayout layout2 = (LinearLayout) findViewById(R.id.bg);
        switch (count) {
            case 7:
                layout2.setBackgroundResource(R.drawable.s1);
                break;
            case 6:
                layout2.setBackgroundResource(R.drawable.s2);
                break;
            case 5:
                layout2.setBackgroundResource(R.drawable.s3);
                break;
            case 4:
                layout2.setBackgroundResource(R.drawable.s4);
                break;
            case 3:
                layout2.setBackgroundResource(R.drawable.s5);
                break;
            case 2:
                layout2.setBackgroundResource(R.drawable.s6);
                break;
            case 1:
                layout2.setBackgroundResource(R.drawable.s7);
                break;
            case 0:
                layout2.setBackgroundResource(R.drawable.s10);
                break;
            case 999:
                layout2.setBackgroundResource(R.drawable.s9);
                break;

        }
    }
}



