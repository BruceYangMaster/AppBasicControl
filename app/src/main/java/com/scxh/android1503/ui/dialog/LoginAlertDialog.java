package com.scxh.android1503.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1503.R;

public class LoginAlertDialog extends AlertDialog {
	private EditText userNameEdit;
	private EditText passWordEdit;
	private Button loginBtn;
	private Activity context;

	public LoginAlertDialog(Activity context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_login_layout);

		userNameEdit = (EditText) findViewById(R.id.dialog_user_name_edit);
		passWordEdit = (EditText) findViewById(R.id.dialog_password_edit);
		loginBtn = (Button) findViewById(R.id.dialog_login_btn);

		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = userNameEdit.getText().toString();
				String passWord = passWordEdit.getText().toString();
				if (userName.equals("admin") && passWord.equals("123456")) {
					Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
					context.finish();
				} else {
					Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
					
					
				}
				dismiss();
			}

		});
	}

}
