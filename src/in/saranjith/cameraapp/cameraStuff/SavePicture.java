package in.saranjith.cameraapp.cameraStuff;

import in.saranjith.cameraapp.constants.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * Saving the captured picture
 * 
 * @author saranjith
 * 
 */
public class SavePicture implements PictureCallback {

	/**
	 * for images
	 */
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	/**
	 * for videos
	 */
	public static final int MEDIA_TYPE_VIDEO = 2;
	
	/**
	 * instance of Context
	 */
	private Context mContext;
	
	/**
	 * 
	 * @param context
	 */
	public SavePicture(Context context){
		this.mContext = context;
	}
	
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
		if (pictureFile == null) {
			Log.d(Constants.tag, "Error creating Media file. check Storage permissions");
			Toast.makeText(mContext, "Error creating Media file. check Storage permissions", Toast.LENGTH_SHORT).show();
			return;
		}else{
			Toast.makeText(mContext, "Done!", Toast.LENGTH_SHORT).show();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(Constants.tag, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(Constants.tag, "Error accessing file: " + e.getMessage());
        }
	}

	/** Create a file Uri for saving an image or video */
	@SuppressWarnings("unused")
	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
			System.out.println(mediaFile.getAbsolutePath());
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}

}
