package com.example.fr.baimanongji;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SelfDialog extends Dialog {

	private Button btn_confirm, btn_cancel;
	private TextView tv_title;
	private Switch s1;
	private String title;
	private Context context;

	public SelfDialog(Context context) {
		super(context, R.style.MyDialog);
		this.context = context;
	}

	public SelfDialog(@NonNull Context context, int themeResId) {
		super(context, themeResId);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.irrigation_control_layout);
		setCanceledOnTouchOutside(false);
		init();
	}

	private void init() {
		btn_cancel = (Button) findViewById(R.id.btn_no);
		btn_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("您点击了取消");
				dismiss();
			}
		});
		btn_confirm = (Button) findViewById(R.id.btn_yes);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("您点击了确认");
				dismiss();
			}
		});
		s1 = (Switch) findViewById(R.id.switch_1);
		s1.setChecked(false);
		s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					System.out.println("咋这么难用");
				} else {
					System.out.println("又出现这么多bug");
				}
			}
		});
	}

}
