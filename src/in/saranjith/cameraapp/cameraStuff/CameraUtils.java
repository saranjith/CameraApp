package in.saranjith.cameraapp.cameraStuff;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * A utility class for some commonly used methods
 * 
 * @author saranjith
 *
 */
public class CameraUtils {

	/**
	 * Context variable from activities
	 */
	
	public static Context mContext;
	
	/**
	 * Constructor for CameraUtils.
	 * 
	 * @param context
	 */
	public CameraUtils(Context context) {
		CameraUtils.mContext = context;
	}

	/**
	 * Check whether the device has a camera hardware that is usable by this app.
	 * @return true if camera is present, false if the camera is not present.
	 */
	public boolean checkCameraHardware(){
		if (mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}
	
	/**
	 * 
	 * @return Instance of the Camera, if the device has a camera. 
	 * @return null if camera is not present.
	 */
	public Camera getCameraInstance(){
		Camera c = null;
		try {
			c = Camera.open(); //try to get a camera instance
		} catch (Exception e) {
			//Camera is not avaialble. It may be in use or does not exist
		}
		return c; // returns null if camera is unAvaiable
	}
}
