package com.example.sslab.samplegroupapplication.samples;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//TODO: 진행하기 
public class AcvGetPhoto extends Activity {
//	private String dir;
//	private String file = dir + "/";
//
//	/*
//	* <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//              android:layout_width="match_parent"
//              android:layout_height="match_parent"
//              android:background="#0000"
//              android:orientation="vertical">
//
//</LinearLayout>
//	*
//	* */
//	@Override
//	protected void onCreate( Bundle savedInstanceState ) {
//		super.onCreate( savedInstanceState );
//		setContentView( R.layout.acv_get_photo );        // 투명 레이아웃. 사용자는 이 액티비티가 실행됐는지조차 모름.
//		initBundle();
//		createDir();
////		showGallery();	// 갤러리는 이제 안 씀. 혹시 나중에 쓸지도 몰라서 관련 함수는 그냥 남겨뒀음
//		showCamera();    // 시작하자마자 카메라 화면을 보여준다.
//	}
//
//	private void initBundle() {
//		dir = getIntent().getStringExtra( "dir" );
//		String code = getIntent().getStringExtra( "code" );
//		String etcName = getIntent().getStringExtra( "etc" );
//		if ( etcName == null )
//			file = Constants.DIR_IMAGE + dir + "/" + code + ".png";    // 각 그리드 아이템별로 저장할 파일명을 미리 지정해 둠
//		else
//			file = Constants.DIR_IMAGE + dir + "/" + code + etcName + ".png";    // 각 그리드 아이템별로 저장할 파일명을 미리 지정해 둠
//	}
//
//	/**
//	 * 이미지 저장할 폴더를 생성
//	 */
//	private void createDir() {
//		File file = new File( Constants.DIR_IMAGE + dir );
//		if ( !file.exists() && !file.isDirectory() )
//			file.mkdirs();
//	}
//
//	/**
//	 * 이 부분에서 카메라 앱을 띄움
//	 * <p/>
//	 * initBundle()에서 지정해 둔 파일 경로를 넘기면 자동으로 그리로 저장한다.
//	 */
//	private void showCamera() {
//		Uri takenFileUri = Uri.fromFile( new File( file ) );
//		Intent itt = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
//		itt.putExtra( MediaStore.EXTRA_OUTPUT, takenFileUri );
//		startActivityForResult( itt, 1 );
//	}
//
//	/**
//	 * 버전별로 갤러리를 띄우는 양식
//	 * <p/>
//	 * 킷캣 이후로 파일 첨부 방식이 변경되어 버전별로 쪼개줘야 한다(현재처럼 단일 기기만 쓸 땐 큰 의미 없음)
//	 * <p/>
//	 * 현재 사용되지 않음(파일 탐색기 형식을 사용)
//	 */
//	private void showGallery() {
//		if ( Build.VERSION.SDK_INT < 19 ) {
//			Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
//			intent.addCategory( Intent.CATEGORY_OPENABLE );
//			intent.setType( "image/*" );
//			startActivityForResult( intent, 3 );
//		} else {
//			Intent intent = new Intent( Intent.ACTION_OPEN_DOCUMENT );
//			intent.addCategory( Intent.CATEGORY_OPENABLE );
//			intent.setType( "image/*" );
//			startActivityForResult( intent, 3 );
//		}
//	}
//
//	public String getPathFromData( Intent data ) {
//		String id = DocumentsContract.getDocumentId( data.getData() );
//		id = id.split( ":" )[1];
//		String[] column = { MediaStore.Images.Media.DATA };
//		String selector = MediaStore.Images.Media._ID + "=?";
//		Cursor c = getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, selector, new String[]{ id }, null );
//		int colIdx = c.getColumnIndex( column[0] );
//		String path = "";
//		if ( c.moveToFirst() ) {
//			path = c.getString( colIdx );
//		}
//		return path;
//	}
//
//	public void getPath( Intent data ) {
//		final int flag = data.getFlags() & ( Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION );
//		try {
//			Bitmap path = MediaStore.Images.Media.getBitmap( getContentResolver(), data.getData() );
//
//			Log.d( "Tag", "wid : " + path.getWidth() );
//		} catch ( Exception e ) {
//
//		}
//	}
//
//	private Bitmap getBitmap( Intent data ) {
//		try {
//			return MediaStore.Images.Media.getBitmap( getContentResolver(), data.getData() );
//		} catch ( Exception e ) {
//			return null;
//		}
//	}
//
//	private void saveBitmapToFile( Bitmap origin ) {
//		File file = new File( this.file );
//		OutputStream out = null;
//		try {
//			if ( file.exists() )
//				file.delete();
//			file.createNewFile();
//			out = new FileOutputStream( file );
//			origin.compress( Bitmap.CompressFormat.PNG, 100, out );
//
//		} catch ( Exception e ) {
//			Log.d( "Tag", "Bitmap save failed : " + e.getMessage() );
//		} finally {
//			try {
//				out.close();
//			} catch ( IOException e ) {
//
//			}
//			Intent itt = new Intent();
//			Bundle b = new Bundle();
//			b.putString( "filePath", this.file );
//			setResult( RESULT_OK, itt );
//			finish();
//		}
//	}
//
//	@Override
//	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
//		super.onActivityResult( requestCode, resultCode, data );
//		switch ( requestCode ) {
//			case 1:    // 카메라 촬영 후
//				if ( resultCode == RESULT_OK ) {
//					Intent itt = new Intent();
//					itt.putExtra( "filePath", file );    //	파일이 잘 저장됐으면 파일 경로를 매물등록 프래그먼트로 넘겨준다
//					setResult( RESULT_OK, itt );
//				}
//				finish();    // 카메라 촬영을 했든 취소를 했든 이 액티비티는 종료
//				break;
//			case 3:    // 갤러리 사용시 쓰는 거니까 지금은 안 쓰임
//				if ( data != null ) {
//					Log.d( "Tag", "Data path : " + data.getData().getPath() );
//					Log.d( "Tag", "Data : " + getPathFromData( data ) );
//					saveBitmapToFile( getBitmap( data ) );
//				}
//				break;
//		}
//	}
}
