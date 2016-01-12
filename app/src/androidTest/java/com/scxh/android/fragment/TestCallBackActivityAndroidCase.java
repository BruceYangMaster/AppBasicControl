package com.scxh.android.fragment;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.scxh.android1503.R;
import com.scxh.android1503.pattern.callback.CallBackActivity;

public class TestCallBackActivityAndroidCase<Activity> extends	ActivityInstrumentationTestCase2<CallBackActivity> {
	private CallBackActivity mActivity;
	private Instrumentation mInstrumentation;

	private Button mButton;

	public TestCallBackActivityAndroidCase() {
		super(CallBackActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mInstrumentation = getInstrumentation();
		mActivity = getActivity();

		mButton = (Button) mActivity.findViewById(R.id.pattern__start_btn);
	}

	public void testDemo() {
		// 验证重置按钮的实现功能，是否点击后内容为空
		assertEquals("开始加载", mButton.getText().toString());
	}

	public void testUiThread() {
		mInstrumentation.runOnMainSync(new Runnable() {

			@Override
			public void run() {
				mButton.setText("hello");
			}
		});
		
		assertEquals("hello", mButton.getText().toString());
	}

}
