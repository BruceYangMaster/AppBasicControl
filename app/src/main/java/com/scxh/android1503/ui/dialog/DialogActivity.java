package com.scxh.android1503.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.scxh.android1503.R;

public class DialogActivity extends Activity implements OnClickListener {
	private static final int DIAOG_SYSTEM = 1; 
	private static final int DIAOG_LOGIN = 2; //登录对话框
	
	private Button mProgressBtn, mDateBtn, mConfirmBtn, mSingleRadioBtn,
			mCustomViewBtn, mCustomAlertBtn, mToastBtn;
	private ProgressDialog mProgressDialog;
	private DatePickerDialog mDatePickerDialog;
	private AlertDialog mCustomViewDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_layout);
		mProgressBtn = (Button) findViewById(R.id.dialog_progress_btn);
		mDateBtn = (Button) findViewById(R.id.dialog_date_btn);
		mConfirmBtn = (Button) findViewById(R.id.dialog_alert_ok_btn);
		mSingleRadioBtn = (Button) findViewById(R.id.dialog_alert_radio_btn);
		mCustomViewBtn = (Button) findViewById(R.id.dialog_custome_view_btn);
		mCustomAlertBtn = (Button) findViewById(R.id.dialog_custome_alert_daiog_btn);
		mToastBtn = (Button) findViewById(R.id.dialog_toast_btn);

		mProgressBtn.setOnClickListener(this);
		mDateBtn.setOnClickListener(this);
		mConfirmBtn.setOnClickListener(this);
		mSingleRadioBtn.setOnClickListener(this);
		mCustomViewBtn.setOnClickListener(this);
		mCustomAlertBtn.setOnClickListener(this);
		mToastBtn.setOnClickListener(this);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIAOG_SYSTEM:
			return new AlertDialog.Builder(this)
					.setTitle("标题")
					.setMessage("是否退出系统?")
					.setPositiveButton("退出",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
		case DIAOG_LOGIN:
			return new LoginAlertDialog(this);
		default:
			return null;
		}

	}

	public void onActivityTwoDialogClickLisenter(View v) {
		showDialog(DIAOG_LOGIN);
	}

	public void onActivityDialogClickLisenter(View v) {
		showDialog(DIAOG_SYSTEM);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_progress_btn:
			setProgressDiaog();
			break;
		case R.id.dialog_date_btn:
			setDatePickerDialog();
			break;
		case R.id.dialog_alert_ok_btn:
			setAlertConfirmDialog();
			break;
		case R.id.dialog_alert_radio_btn:
			setAlertSigleChoiceDialog();
			break;
		case R.id.dialog_custome_view_btn:
			setCustomViewDialog();
			break;
		case R.id.dialog_custome_alert_daiog_btn:
			LoginAlertDialog dialog = new LoginAlertDialog(this);
			dialog.show();
			break;
		case R.id.dialog_toast_btn:
			// Toast toast = Toast.makeText(DialogActivity.this,
			// "这是一个普通的Toast!", Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
			// View currentView = toast.getView();
			// toast.show();

			Toast toast = new Toast(this);
			View contentView = LayoutInflater.from(this).inflate(
					R.layout.dialog_toast_layout, null);
			toast.setView(contentView);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.show();
			break;
		}

	}

	/**
	 * 自定义View对话框
	 */
	public void setCustomViewDialog() {
		View contentView = LayoutInflater.from(this).inflate(
				R.layout.dialog_login_layout, null);
		final EditText userNameEdit = (EditText) contentView
				.findViewById(R.id.dialog_user_name_edit);
		final EditText passWordEdit = (EditText) contentView
				.findViewById(R.id.dialog_password_edit);
		Button loginBtn = (Button) contentView
				.findViewById(R.id.dialog_login_btn);

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = userNameEdit.getText().toString();
				String passWord = passWordEdit.getText().toString();
				if (userName.equals("admin") && passWord.equals("123456")) {
					// Toast.makeText(DialogActivity.this, "登录成功",
					// Toast.LENGTH_SHORT).show();

					Toast toast = Toast.makeText(DialogActivity.this, "登录成功",
							Toast.LENGTH_SHORT);
					toast.show();

				} else {
					Toast.makeText(DialogActivity.this, "登录失败",
							Toast.LENGTH_SHORT).show();
				}
				mCustomViewDialog.dismiss();
			}
		});

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(contentView);

		mCustomViewDialog = builder.create();
		mCustomViewDialog.show();
	}

	/**
	 * 单选对话框
	 */
	public void setAlertSigleChoiceDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("姓别");
		builder.setSingleChoiceItems(new String[] { "男", "女" }, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogActivity.this, "which" + which,
								Toast.LENGTH_SHORT).show();
					}
				});
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT)
						.show();
			}
		});
		builder.create().show();

	}

	/**
	 * 确认对话框
	 */
	public void setAlertConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("确认对话框");
		builder.setMessage("确认对话框学习");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "确定成功", Toast.LENGTH_SHORT)
						.show();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "取消成功", Toast.LENGTH_SHORT)
						.show();
			}
		});

		builder.setNeutralButton("继续", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "继续", Toast.LENGTH_SHORT)
						.show();

			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 日期对话框
	 */
	public void setDatePickerDialog() {
		mDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Toast.makeText(DialogActivity.this,
						"" + year + " " + monthOfYear + " " + dayOfMonth,
						Toast.LENGTH_SHORT).show();
			}
		}, 2015, 10, 14);
		mDatePickerDialog.show();
	}

	/**
	 * 进度条对话框
	 */
	public void setProgressDiaog() {
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setProgressDrawable(getResources().getDrawable(
				R.drawable.seekbar_progressdrawable_selector));
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.show();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					mProgressDialog.setProgress(i);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				mProgressDialog.dismiss();
			}
		}).start();
	}
}
