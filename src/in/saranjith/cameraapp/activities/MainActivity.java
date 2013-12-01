package in.saranjith.cameraapp.activities;

import in.saranjith.cameraapp.R;
import in.saranjith.cameraapp.cameraStuff.CameraUtils;
import in.saranjith.cameraapp.constants.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author saranjith
 * 
 */

public class MainActivity extends Activity {

	

	/**
	 * Button to start camera
	 */
	private Button startCamera;

	/**
	 * Instance variable of CameraUtils Class
	 */
	protected CameraUtils cameraUtils;


	/**
	 * Intent to go to CameraPreviewActivity
	 */
	public Intent cameraPreviewIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cameraUtils = new CameraUtils(MainActivity.this);
		
		
		cameraPreviewIntent = new Intent(getApplicationContext(), CameraPreviewActivity.class);

		initViews();
	}

	/**
	 * Initialize Views
	 */
	public void initViews() {
		startCamera = (Button) findViewById(R.id.button_startCamera);
		initListeners();
	}

	/**
	 * Initialize the various listeners
	 */
	private void initListeners() {

		startCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (cameraUtils.checkCameraHardware()) {
					/**
					 * the device has a camera that we can use What are you
					 * waiting for? Make use of it, damn it!
					 */
					Log.i(Constants.tag, "Camera is Present");
					startActivity(cameraPreviewIntent);
				}else{
					Toast.makeText(MainActivity.this,"Camera not present" ,Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
