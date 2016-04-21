package com.example.fx50j.calculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_dian;//小数点按钮
    Button btn_jia;
    Button btn_jian;
    Button btn_cheng;
    Button btn_chu;
    Button btn_c;//清除按钮
    Button btn_del;//删除一个按钮
    Button btn_dengyu;//等于按钮
    EditText et_input;//显示屏
    boolean flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.bt_0);
        btn_1 = (Button) findViewById(R.id.bt_1);
        btn_2 = (Button) findViewById(R.id.bt_2);
        btn_3 = (Button) findViewById(R.id.bt_3);
        btn_4 = (Button) findViewById(R.id.bt_4);
        btn_5 = (Button) findViewById(R.id.bt_5);
        btn_6 = (Button) findViewById(R.id.bt_6);
        btn_7 = (Button) findViewById(R.id.bt_7);
        btn_8 = (Button) findViewById(R.id.bt_8);
        btn_9 = (Button) findViewById(R.id.bt_9);
        btn_dian = (Button) findViewById(R.id.bt_dian);
        btn_jia = (Button) findViewById(R.id.bt_jia);
        btn_jian = (Button) findViewById(R.id.bt_jian);
        btn_cheng = (Button) findViewById(R.id.bt_cheng);
        btn_chu = (Button) findViewById(R.id.bt_chu);
        btn_c = (Button) findViewById(R.id.bt_c);
        btn_del = (Button) findViewById(R.id.bt_del);
        btn_dengyu = (Button) findViewById(R.id.bt_dengyu);
        et_input = (EditText) findViewById(R.id.et_input);
        //实例化按钮和显示屏

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dian.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        btn_cheng.setOnClickListener(this);
        btn_chu.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_dengyu.setOnClickListener(this);
        //给按钮设置点击事件
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.bt_0:
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_dian:
                if(flag)
                {
                    flag = false;
                    str ="";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                //只要点击以上按钮就会在输入框里面添加相应内容,在原来的输入框内直接累加
                break;

            case R.id.bt_jia:
            case R.id.bt_jian:
            case R.id.bt_cheng:
            case R.id.bt_chu:
                if(flag)
                {
                    flag = false;
                    //str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;

            case R.id.bt_c:
                flag = false;
                str = "";
                et_input.setText("");//将显示屏设置为空即可达到清除效果
                break;

            case R.id.bt_del:
                if(flag)
                {
                    flag = false;
                    str = "";
                    et_input.setText("");
                }
               else if (str!= null &&!str.equals(""))//保证显示框内容不为空
                {
                    et_input.setText(str.substring(0,str.length()-1));
                    //显示从零到当前内容减一从而达到少一个的功能的实现
                }
                break;

            case R.id.bt_dengyu:
                getResult();
                break;

        }
    }

    private void getResult()//单独写个方法来处理等号按钮的功能,运算结果
    {
        String exp = et_input.getText().toString();//获取显示屏信息
        if (exp == null || exp.equals("")) {
            return;
        }
        if (!exp.contains(" "))  //判断当前内容是否有空格,如果有说明当前只有数字没有运算符，可以直接return
        {
            return;
        }
        if(flag){
            flag = false;
            return;
        }
        flag = true;
        double result = 0;
        String s1 = exp.substring(0,exp.indexOf(" "));//截取至运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ") + 1,exp.indexOf(" ") + 2);//截取运算符
        String s2 = exp.substring(exp.indexOf(" ") + 2);//运算符后面的字符串
        if (!s1.equals("") && !s2.equals("")) //如果s1，s2都不为空
        {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            //强制类型转换
            if (op.equals("＋"))
            {
                result = d1 + d2;
            }
            else if (op.equals("－"))
            {
                result = d1 - d2;
            }
            else if (op.equals("×"))
            {
                result = d1 * d2;
            }
            else if (op.equals("÷"))
            {
                if (d2 == 0)
                {
                    result = 0;
                }
                else
                {
                    result = d1 / d2;
                }
            }
            if ((!s1.contains(".") && !s2.contains(".")&&!op.equals("÷"))||(!s1.contains(".") && !s2.contains(".")&&op.equals("÷")&&result==(int)result)) //如果是s1,s2都不含小数点则为int型
            {
                int re = (int) result;
                et_input.setText(re + "");
            }
            else
            {
                et_input.setText(result + "");
            }
        }
        else if (!s1.equals("") && s2.equals("")) //一个数字后面有运算符
        {
            et_input.setText(exp);
        }
        else if (s1.equals("") && !s2.equals(""))
        {
            double d2 = Double.parseDouble(s2);
            if (op.equals("＋"))
            {
                result = 0 + d2;
            }
            else if (op.equals("－"))
            {
                result = 0 - d2;
            }
            else if (op.equals("×"))
            {
                result = 0;
            }
            else if (op.equals("÷"))
            {
                result = 0;
            }
            if (!s2.contains(".")) //如果是s2不含小数点则为int型
            {
                int re = (int) result;
                et_input.setText(re + "");
            }
            else
            {
                et_input.setText(result + "");
            }
        }
        else
        {
            et_input.setText("");
        }
    }
}
