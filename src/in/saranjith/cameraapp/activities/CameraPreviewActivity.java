package in.saranjith.cameraapp.activities;

import in.saranjith.cameraapp.R;
import in.saranjith.cameraapp.cameraStuff.CameraPreview;
import in.saranjith.cameraapp.cameraStuff.CameraUtils;
import in.saranjith.cameraapp.cameraStuff.SavePicture;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Creates a preview of the subject
 * 
 * @author saranjith
 * 
 */
public class CameraPreviewActivity extends Activity {

	/**
	 * Instance variable of CameraUtils Class
	 */
	protected CameraUtils cameraUtils;

	/**
	 * Instance of camera
	 */
	private Camera mCamera;

	/**
	 * CameraPreview instance
	 */
	private CameraPreview mPreview;

	/**
	 * Button to take picture on click
	 */
	private Button takePicture;

	/**
	 * FrameLayout to show camera preview
	 */
	private FrameLayout preview;
	
	/**
	 * instance of SavePicture
	 */
	private SavePicture savePicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_preview);

		cameraUtils = new CameraUtils(CameraPreviewActivity.this);

		mCamera = cameraUtils.getCameraInstance();
		
		savePicture = new SavePicture(CameraPreviewActivity.this);

		initViews();
	}

	/**
	 * Initialize Views
	 */
	private void initViews() {

		takePicture = (Button) findViewById(R.id.button_capture);

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(CameraPreviewActivity.this, mCamera);
		preview = (FrameLayout)findViewById(R.id.camera_preview);
		
		preview.addView(mPreview);

		initListeners();
	}

	/**
	 * Initialize listeners
	 */
	private void initListeners() {

		takePicture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCamera.takePicture(null, null, savePicture);
			}
		});
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        //releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();              // release the camera immediately on pause event
    }

	
	/**
	 * release camera
	 */
	 private void releaseCamera(){
	        if (mCamera != null){
	            mCamera.release();        // release the camera for other applications
	            mCamera = null;
	        }
	    }
	

}
