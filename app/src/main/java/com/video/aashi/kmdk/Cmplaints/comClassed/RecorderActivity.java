package com.video.aashi.kmdk.Cmplaints.comClassed;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iceteck.silicompressorr.FileUtils;
import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class RecorderActivity extends AppCompatActivity {
	private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
	private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
	private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";

    AudioRecord recorder;
	private int currentFormat = 0;
	private int output_formats[] = { MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP };
	private String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP };
	File file;
	TextView recordText;
	MenuStrings menuStrings;
	private static final int BUFFER_SIZE_FACTOR = 2;
	private static final int SAMPLING_RATE_IN_HZ = 44100;
	private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
	private static final int AUDIO_FORMAT = AudioFormat.ENCODING_DEFAULT;
	private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLING_RATE_IN_HZ,
			CHANNEL_CONFIG, AUDIO_FORMAT) * BUFFER_SIZE_FACTOR;
	private final AtomicBoolean recordingInProgress = new AtomicBoolean(false);
	private Thread recordingThread = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setButtonHandlers();

    }

	private void setButtonHandlers() {
    	menuStrings = new MenuStrings(getApplicationContext());
		((CardView)findViewById(R.id.btnStart)).setOnClickListener(btnClick);
        ((CardView)findViewById(R.id.btnStop)).setOnClickListener(btnClick);
        ((CardView)findViewById(R.id.btnFormat)).setOnClickListener(btnClick);
        recordText =(TextView)findViewById(R.id.recordText);
	}
	void startRecording()
	{
//		String filePath = Environment.getExternalStorageDirectory() + "/recorded_audio.wav";
//		int color = getResources().getColor(R.color.colorPrimaryDark);
//		int requestCode = 0;
//		AndroidAudioRecorder.with(this)
//				// Required
//				.setFilePath(filePath)
//				.setColor(color)
//				.setRequestCode(requestCode)
//
//				// Optional
//				.setSource(AudioSource.MIC)
//				.setChannel(AudioChannel.STEREO)
//				.setSampleRate(AudioSampleRate.HZ_48000)
//				.setAutoStart(true)
//				.setKeepDisplayOn(true)
//
//				// Start recording
//				.record();
		recorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, SAMPLING_RATE_IN_HZ,
				CHANNEL_CONFIG, AUDIO_FORMAT, BUFFER_SIZE);
		recorder.startRecording();
		recordingInProgress.set(true);
		recordingThread = new Thread(new RecordingRunnable(), "Recording Thread");
		recordingThread.start();
		recordText.setText("Recording...");
	}
	void  stopRecording()
	{
		if (null == recorder) {
			return;
		}

		recordingInProgress.set(false);

		recorder.stop();

		recorder.release();

		recorder = null;

		recordingThread = null;
		finish();
	}
	private class RecordingRunnable implements Runnable {

		@Override
		public void run() {
			final File file = new File(Environment.getExternalStorageDirectory(), "recording.mp3");
			final ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
           Log.i("TAG","audioRecord"+ file.toString());
			try (final FileOutputStream outStream = new FileOutputStream(file)) {
				while (recordingInProgress.get()) {
					int result = recorder.read(buffer, BUFFER_SIZE);
					if (result < 0) {
						throw new RuntimeException("Reading of audio buffer failed: " +
								getBufferReadFailureReason(result));
					}
					outStream.write(buffer.array(), 0, BUFFER_SIZE);
					buffer.clear();
				}
			} catch (IOException e) {
				throw new RuntimeException("Writing of recorded audio failed", e);
			}
		}
	}
	private String getBufferReadFailureReason(int errorCode) {
		switch (errorCode) {
			case AudioRecord.ERROR_INVALID_OPERATION:
				return "ERROR_INVALID_OPERATION";
			case AudioRecord.ERROR_BAD_VALUE:
				return "ERROR_BAD_VALUE";
			case AudioRecord.ERROR_DEAD_OBJECT:
				return "ERROR_DEAD_OBJECT";
			case AudioRecord.ERROR:
				return "ERROR";
			default:
				return "Unknown (" + errorCode + ")";
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		stopRecording();
	}

    private View.OnClickListener btnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.btnStart:{
					AppLog.logString("Start Recording");
					if (ActivityCompat.checkSelfPermission(getApplicationContext(),
							Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
						ActivityCompat.requestPermissions(RecorderActivity.this, new String[]
										{Manifest.permission.RECORD_AUDIO},
								Complaints.MY_PERMISSIONS_REQUEST_CAMERA);
					}
					else
					{
						startRecording();
					}

					break;
				}
				case R.id.btnStop:{

					stopRecording();

					break;
				}
				case R.id.btnFormat:{


					break;
				}
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);if (requestCode == 0) {
			if (requestCode == 0  && resultCode == RESULT_OK  ) {


				String realPath = null;
				Uri uriFromPath = null;

				realPath = getPathForAudio(RecorderActivity.this, data.getData());
				uriFromPath = Uri.fromFile(new File(realPath)); //
				Log.d("TAG", "File Uri: " +uriFromPath );
				// Get the path

				Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();



			} else if (resultCode == RESULT_CANCELED) {
				// Oops! User has canceled the recording
				Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
			}
		}

	}
	public static String getPathForAudio(Context context, Uri uri)
	{
		String result = null;
		Cursor cursor = null;

		try {
			String[] proj = { MediaStore.Audio.Media.DATA };
			cursor = context.getContentResolver().query(uri, proj, null, null, null);
			if (cursor == null) {
				result = uri.getPath();
			} else {
				cursor.moveToFirst();
				int column_index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
				result = cursor.getString(column_index);
				cursor.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return result;
	}
}