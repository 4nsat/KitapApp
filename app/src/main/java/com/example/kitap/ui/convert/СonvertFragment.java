package com.example.kitap.ui.convert;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kitap.R;

public class СonvertFragment extends Fragment implements View.OnClickListener {

    TextView firstPlain, secondPlain, firstLang, secondLang;
    LinearLayout changeLang;
    String txt = "", res = "";
    Boolean chek = true, updown = true;

    Button Btn, Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9;

    char[] charArrKir = "АаӘәБбВвГгҒғДдЕеЁёЖжЗзИиЙйКкҚқЛлМмНнҢңОоӨөПпРрСсТтУуҰұҮүФфХхҺһЦцЧчШшЩщЫыІіЭэЮюЯяЬьЪъ".toCharArray();
    char[] charArrLat = "AaÄäBbVvGgĞğDdEeEeJjZzİiİiKkQqLlMmNnÑñOoÖöPpRrSsTtUuŪūÜüFfHhHhCcChchŞşŞşYyIıEeİuiuİaia".toCharArray();
    String strArrKir = "АаӘәБбВвГгҒғДдЕеЁёЖжЗзИиЙйКкҚқЛлМмНнҢңОоӨөПпРрСсТтУуҰұҮүФфХхҺһЦцЧчШшЩщЫыІіЭэЮюЯяЬьЪъ";
    String strArrLat = "AaÄäBbVvGgĞğDdEeEeJjZzİiİiKkQqLlMmNnÑñOoÖöPpRrSsTtUuŪūÜüFfHhHhCcChchŞşŞşYyIıEeİuiuİaia";
    char chrPrev;

    public СonvertFragment() {
        // Required empty public constructor
    }

    public static СonvertFragment newInstance(String param1, String param2) {
        СonvertFragment fragment = new СonvertFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_convert, container, false);
        firstPlain = root.findViewById(R.id.firstPlain);
        secondPlain = root.findViewById(R.id.secondPlain);
        firstLang = root.findViewById(R.id.firstLang);
        secondLang = root.findViewById(R.id.secondLang);

        //Buttons
        Btn = root.findViewById(R.id.button);
        Btn1 = root.findViewById(R.id.button1);
        Btn2 = root.findViewById(R.id.button2);
        Btn3 = root.findViewById(R.id.button3);
        Btn4 = root.findViewById(R.id.button4);
        Btn5 = root.findViewById(R.id.button5);
        Btn6 = root.findViewById(R.id.button6);
        Btn7 = root.findViewById(R.id.button7);
        Btn8 = root.findViewById(R.id.button8);
        Btn9 = root.findViewById(R.id.button9);
        Btn.setOnClickListener(this);
        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        Btn3.setOnClickListener(this);
        Btn4.setOnClickListener(this);
        Btn5.setOnClickListener(this);
        Btn6.setOnClickListener(this);
        Btn7.setOnClickListener(this);
        Btn8.setOnClickListener(this);
        Btn9.setOnClickListener(this);
        //Buttons

        changeLang = root.findViewById(R.id.changeButton);

        firstPlain.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txt = String.valueOf(firstPlain.getText());
                if (txt.length() < 1) {
                    secondPlain.setText("");
                }
                res = "";
                if (chek) {
                    for (int i = 0; i <= txt.length() - 1; i++) {
                        char chr = txt.charAt(i);

                        //Log.d("length", "onTextChanged: " + txt.length());
                        //Log.d("chr", "onTextChanged: " + chr);


                        int ind = strArrKir.indexOf(chr);
                        Log.d("index", "onTextChanged: " + ind);
                        if (ind >= 0) {
                            switch (ind) {
                                case 64: //Ч
                                    res += "Ch";
                                    break;
                                case 65: //ч
                                    res += "ch";
                                    break;
                                case 76: //Ю
                                    res += "İu";
                                    break;
                                case 77: //ю
                                    res += "iu";
                                    break;
                                case 78: //Я
                                    res += "İa";
                                    break;
                                case 79: //я
                                    res += "ia";
                                    break;
                                default:
                                    if  (ind > 79 && ind < 84) {
                                        //ЬьЪъ
                                    } else {
                                        if (ind > 65 && 76 > ind) ind += 2;
                                        res += String.valueOf(charArrLat[ind]);
                                    }
                                    break;
                            }
                            //res += String.valueOf(strArrLat[ind]);

                            secondPlain.setText(res);
                        } else {
                            res += chr;
                            secondPlain.setText(res);
                        }
                    }//For
                } else { //Chek False
                    for (int i = 0; i <= txt.length() - 1; i++) {
                        char chr = txt.charAt(i);
                        if (i > 0) {
                            chrPrev = txt.charAt(i - 1);//Пред буква
                            //Log.d("index", "onTextChanged: " + chrPrev);
                        }

                        int ind = strArrLat.indexOf(chr);
                        //Log.d("index", "onTextChanged: " + ind);
                        if (ind >= 0) {
                            switch (ind) {
                                case 58: //Ч
                                    if (chrPrev == 'C') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Ч";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                case 59: //ч
                                    if (chrPrev == 'C') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Ч";
                                    } else if (chrPrev == 'c') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "ч";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                case 50: //Ю
                                    if (chrPrev == 'İ') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Ю";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                case 51: //ю
                                    if (chrPrev == 'İ') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Ю";
                                    } else if (chrPrev == 'i') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "ю";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                case 0: //Я
                                    if (chrPrev == 'İ') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Я";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                case 1: //я
                                    if (chrPrev == 'İ') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "Я";
                                    } else if (chrPrev == 'i') {
                                        res = res.substring(0, res.length() - 1);
                                        res += "я";
                                    } else {
                                        res += String.valueOf(charArrKir[ind]);
                                    }
                                    break;
                                default:
                                    if (ind > 65 && 76 > ind) ind -= 2;
                                    res += String.valueOf(charArrKir[ind]);
                                    break;
                            }
                            //res += String.valueOf(strArrLat[ind]);

                            secondPlain.setText(res);
                        } else {
                            res += chr;
                            secondPlain.setText(res);
                        }
                    }
                } //endIf chek


            }//onTextChanged

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        changeLang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String frst = String.valueOf(firstPlain.getText());
                String scnd = String.valueOf(secondPlain.getText());
                if (chek) {
                    firstPlain.setText(scnd);
                    secondPlain.setText(frst);
                    firstLang.setText("Latynşa");
                    secondLang.setText("Кириллица");
                    LatKz(chek);
                    chek = false;
                } else {
                    firstPlain.setText(scnd);
                    secondPlain.setText(frst);
                    firstLang.setText("Кириллица");
                    secondLang.setText("Latynşa");
                    LatKz(chek);
                    chek = true;
                }
            }
        });

        return root;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ә");
                    } else {
                        firstPlain.append("ә");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ä");
                    } else {
                        firstPlain.append("ä");
                    }
                }
                break;
            case R.id.button1:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("І");
                    } else {
                        firstPlain.append("і");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ğ");
                    } else {
                        firstPlain.append("ğ");
                    }
                }
                break;
            case R.id.button2:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ң");
                    } else {
                        firstPlain.append("ң");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("İ");
                    } else {
                        firstPlain.append("ı");
                    }
                }
                break;
            case R.id.button3:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ғ");
                    } else {
                        firstPlain.append("ғ");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ñ");
                    } else {
                        firstPlain.append("ñ");
                    }
                }
                break;
            case R.id.button4:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ү");
                    } else {
                        firstPlain.append("ү");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ö");
                    } else {
                        firstPlain.append("ö");
                    }
                }
                break;
            case R.id.button5:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ұ");
                    } else {
                        firstPlain.append("ұ");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ū");
                    } else {
                        firstPlain.append("ū");
                    }
                }
                break;
            case R.id.button6:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Қ");
                    } else {
                        firstPlain.append("қ");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ü");
                    } else {
                        firstPlain.append("ü");
                    }
                }
                break;
            case R.id.button7:
                if (chek) {
                    if (!updown) {
                        firstPlain.append("Ө");
                    } else {
                        firstPlain.append("ө");
                    }
                } else {
                    if (!updown) {
                        firstPlain.append("Ş");
                    } else {
                        firstPlain.append("ş");
                    }
                }
                break;
            case R.id.button8:
                    if (!updown) {
                        firstPlain.append("Һ");
                    } else {
                        firstPlain.append("һ");
                    }
                break;
            case R.id.button9:
                if (updown) {
                    updown = false;
                    UpDowner(updown);
                } else {
                    updown = true;
                    UpDowner(updown);
                }
                break;
        }
    }

    private void UpDowner(Boolean bool) {
        if (!bool) {
            Btn.setAllCaps(true);
            Btn1.setAllCaps(true);
            Btn2.setAllCaps(true);
            Btn3.setAllCaps(true);
            Btn4.setAllCaps(true);
            Btn5.setAllCaps(true);
            Btn6.setAllCaps(true);
            Btn7.setAllCaps(true);
            Btn8.setAllCaps(true);
            if (!chek) Btn2.setText("İ");
        } else {
            Btn.setAllCaps(false);
            Btn1.setAllCaps(false);
            Btn2.setAllCaps(false);
            Btn3.setAllCaps(false);
            Btn4.setAllCaps(false);
            Btn5.setAllCaps(false);
            Btn6.setAllCaps(false);
            Btn7.setAllCaps(false);
            Btn8.setAllCaps(false);
            if (!chek) Btn2.setText("ı");
        }
    }

    private void LatKz(Boolean bool) {
        if (!bool) {
            Btn.setText("ә");
            Btn1.setText("і");
            Btn2.setText("ң");
            Btn3.setText("ғ");
            Btn4.setText("ү");
            Btn5.setText("ұ");
            Btn6.setText("қ");
            Btn7.setText("ө");
            Btn8.setVisibility(View.VISIBLE);
            Btn8.setText("һ");
        } else {
            Btn.setText("ä");
            Btn1.setText("ğ");
            Btn2.setText("ı");
            Btn3.setText("ñ");
            Btn4.setText("ö");
            Btn5.setText("ū");
            Btn6.setText("ü");
            Btn7.setText("ş");
            Btn8.setVisibility(View.GONE);
        }
    }
}