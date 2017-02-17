package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sslab.samplegroupapplication.data.ImgExplorerItem;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

//TODO : 진행하기
public class AcvImgExplorer extends AppCompatActivity{
    private boolean needPdf = false;        // pdf 파일도 보여줄 필요가 있는지
    private String fileExtension = "";      // pdf도 필요할 땐 확장자를 따로 지정해준다.
    private String rootDirectory = "/";    // 저장소 루트
    private File currentFolder;
    private String filePathTarget;

    private ArrayList<File> arrayFiles = new ArrayList<>();

    /**
     * 폴더인지 이미지 파일인지만
     */
    private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return true;
            if( file.getName().endsWith( "jpg" ) || file.getName().endsWith( "png" ) || file.getName().endsWith( "Jpg" ) || file.getName().endsWith( "Png" ) || file.getName().endsWith( "JPG" ) || file.getName().endsWith( "PNG" ) )
                return true;
            return false;
        }
    };

    private FilenameFilter filenameFilter;

//
//    // View
    private TextView textFolderName;    // 현재 폴더명을 상단에 표시하려 했는데, 별 중요한 것도 아닌데 코드만 복잡해져서 시간이 남지 않으면 안 건들기로.... 이와 관련된 변수 몇 개도 그냥 안 써서 주석 처리
    private Button btnList, btnGrid;
    private ListView listFile;
    private GridView gridFile;
    private ArrayList<ImgExplorerItem> arrayFile = new ArrayList<>();
//    private ImgExplorerAdapter adapterList, adapterGrid;
//
//    @Override
//    protected void onCreate( Bundle savedInstanceState ) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.acv_img_explorer );
//        initBundle();
//        initView();
//        currentFolder = new File( getLastPath() );
//        if ( !setFileList( currentFolder.getPath() ) ) {
//            if ( setFileList( rootDirectory ) )
//                currentFolder = new File( rootDirectory );
//        }
//
////        getFileArray( "/" );
//    }
//
//    /**
//     * 번들로 받아온 데이터를 가장 먼저 처리
//     */
//    private void initBundle() {
//        needPdf = getIntent().getBooleanExtra( "needPdf", false );    // 금융 정보 관련해서 pdf파일도 받아와야 하는 경우에는 true
//        String dir = getIntent().getStringExtra( "dir" );
//        filePathTarget = Constants.DIR_IMAGE + dir + "/" + getIntent().getStringExtra( "fileName" );    // 파일을 복사할 경로를 먼저 설정해 둔다
//        if ( !needPdf ) {
//            filePathTarget += ".png";    // pdf가 필요 없을 경우에는 이미지 확장자를 미리 붙여둔다
//
//        }
//        filter = new FileFilter() {
//            @Override
//            public boolean accept( File file ) {
//                if ( file.isDirectory() )
//                    return true;
//                if ( !needPdf ) {    // pdf 가 필요 없을 떄엔 그림 파일만 보여줌
//                    if ( file.getName().endsWith( "jpg" ) || file.getName().endsWith( "png" ) || file.getName().endsWith( "Jpg" ) || file.getName().endsWith( "Png" ) || file.getName().endsWith( "JPG" ) || file.getName().endsWith( "PNG" ) )
//                        return true;
//                } else {        // pdf도 검색해야 할 경우
//                    if ( file.getName().endsWith( "jpg" ) || file.getName().endsWith( "png" ) || file.getName().endsWith( "Jpg" ) || file.getName().endsWith( "Png" ) || file.getName().endsWith( "JPG" ) || file.getName().endsWith( "PNG" ) || file.getName().endsWith( "pdf" ) || file.getName().endsWith( "Pdf" ) || file.getName().endsWith( "PDF" ) || file.getName().endsWith( "pDf" ) || file.getName().endsWith( "pDF" ) || file.getName().endsWith( "PDf" ) || file.getName().endsWith( "PdF" ) )
//                        return true;
//                }
//                return false;
//            }
//        };
//        filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept( File dir, String name ) {
//                return true;
//            }
//        };
//    }
//
//
//    private void initView() {
//        textFolderName = ( TextView ) findViewById( R.id.textFolderName );
//        btnList = ( Button ) findViewById( R.id.btnList );
//        btnList.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick( View view ) {
//                gridFile.setVisibility( View.GONE );
//                listFile.setVisibility( View.VISIBLE );
//            }
//        } );
//        btnGrid = ( Button ) findViewById( R.id.btnGrid );
//        btnGrid.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick( View view ) {
//                gridFile.setVisibility( View.VISIBLE );
//                listFile.setVisibility( View.GONE );
//            }
//        } );
//        listFile = ( ListView ) findViewById( R.id.listFile );
//        gridFile = ( GridView ) findViewById( R.id.gridFile );
//        adapterList = new ImgExplorerAdapter( this, arrayFile, true );
//        listFile.setAdapter( adapterList );
//        listFile.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick( AdapterView<?> adapterView, View view, int i, long l ) {
//                ImgExplorerItem item = arrayFile.get( i );
//                if ( item.toUp ) {        // 상위 폴더로 이동
//                    upToFolder();
//                } else if ( item.isFolder ) {
//                    if ( setFileList( item.filePath ) ) {    // 못 들어가는 폴더가 있어서
//                        saveLastPath();
//                    } else {    // 못 들어가면 그냥 메시지만 띄움
//                        Toast.makeText( AcvImgExplorer.this, "폴더를 확인할 수 없습니다", Toast.LENGTH_SHORT ).show();
//                    }
//                } else {
//                    if ( needPdf ) {
//                        String fileNameArray[] = item.fileName.split( "\\." );
//                        if ( fileNameArray.length > 1 ) {
//                            fileExtension = "." + fileNameArray[ fileNameArray.length - 1 ];
//                        }
//                    }
//                    new FileCopy( item.filePath, filePathTarget + fileExtension ).execute( "" );
//                }
//            }
//        } );
//        adapterGrid = new ImgExplorerAdapter( this, arrayFile, false );
//        gridFile.setAdapter( adapterGrid );
//        gridFile.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick( AdapterView<?> adapterView, View view, int i, long l ) {
//                ImgExplorerItem item = arrayFile.get( i );
//                if ( item.toUp ) {
//                    upToFolder();
//                } else if ( item.isFolder ) {
//                    if ( setFileList( item.filePath ) ) {
//                        saveLastPath();
//                    } else {
//                        Toast.makeText( AcvImgExplorer.this, "폴더를 확인할 수 없습니다", Toast.LENGTH_SHORT ).show();
//                    }
//                } else {
//                    if ( needPdf ) {
//                        String fileNameArray[] = item.fileName.split( "\\." );
//                        if ( fileNameArray.length > 1 ) {
//                            fileExtension = "." + fileNameArray[ fileNameArray.length - 1 ];
//                        }
//                    }
//                    new FileCopy( item.filePath, filePathTarget ).execute( "" );        // 이미지 파일을 클릭하면 파일을 통째로 복사
//                }
//            }
//        } );
//    }
//
//    private void getFileArray( String folderPath ) {
//        try {
//            File currFolder = new File( folderPath );
//            String[] files = currFolder.list();
//            for ( int i = 0; i < files.length; i++ ) {
//                File file = new File( folderPath + files[ i ]  );
//                Log.d( "Tag", "file : " + folderPath + files[ i ] );
//                if ( file.isDirectory() ) {
//                    if( folderPath.equals( "/" )) {
//                        if( file.getName().contains( "sdcard" ) ||file.getName().contains( "storage" ) || file.getName().contains( "external" )|| file.getName().contains( "root" ) ) {
//                            getFileArray( file.getPath() + "/" );
//                        }
//                    } else {
//                        getFileArray( file.getPath() + "/" );
//                    }
//                }
//            }
//        } catch ( Exception e ) {
//            Log.d( "Tag", "exception : " + e.getLocalizedMessage() );
//        }
//
//    }
//
//    /**
//     * 파일 목록을 표시
//     *
//     * @param filePath 내가 클릭한 폴더의 경로
//     * @return
//     */
//    private boolean setFileList( String filePath ) {
//        File lastFile = currentFolder;    // 일단 현재 폴더를 저장해 두고
//        try {
//            currentFolder = new File( filePath );    // 이동할 폴더를 지정한 뒤
//            File[] files = currentFolder.listFiles( filter );    // 이동할 폴더의 파일을 긁어와 봄
//            if ( files == null ) {        // 못 긁어오면 안 들어감
//                currentFolder = lastFile;        // 안 들어갈 땐 먼저 저장해 둔 폴더로 복귀
//                return false;          // 그리고 그대로 종료
//            }
//            arrayFile.clear();      // 파일 목록이 정상이면 리스트뷰를 클리어하고
//            if ( !currentFolder.getPath().equals( rootDirectory ) ) {        // 현재 위치가 루트 폴더("/storage")가 아니라면 '위로' 가는 버튼을 한 줄 추가
//                arrayFile.add( new ImgExplorerItem( true, true, "", "..." ) );
//            }
//            for ( File item : files ) {
//                arrayFile.add( new ImgExplorerItem( item.isDirectory(), item.getPath(), item.getName() ) );     // 리스트뷰에 표시할 파일들을 배열에 저장
//            }
//            adapterList.notifyDataSetChanged();
//            adapterGrid.notifyDataSetChanged();
//            return true;
//        } catch ( Exception e ) {
//            currentFolder = lastFile;
//            return false;
//        }
//    }
//
//    /**
//     * 이전 폴더로 돌아가기
//     */
//    private void upToFolder() {
//        String currPath = currentFolder.getPath();
//        String paths[] = currPath.split( "/" );
//        Log.d("Tag", "Paths : " + currPath.toString());
//        if( paths.length > 2) {
//            String currFolderName = "/" + paths[ paths.length - 1 ];
//            currentFolder = new File( currPath.substring( 0, currPath.length() - currFolderName.length() ) ); // 상위 폴더 지정
//        } else  {
//            currentFolder = new File( rootDirectory );
//        }
//        saveLastPath();
//        setFileList( currentFolder.getPath() );
//    }
//
//
//    private class FileCopy extends AsyncTask<String, String, String> {
//        private String pathOrigin, pathTarget;
//
//        public FileCopy( String pathOrigin, String pathTarget ) {
//            this.pathOrigin = pathOrigin;
//            this.pathTarget = pathTarget;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showLoading( "파일을 불러오고 있습니다. 잠시만 기다려 주세요" );
//        }
//
//        @Override
//        protected String doInBackground( String... strings ) {
//            InputStream in = null;
//            OutputStream out = null;
//            try {
//                String dir = getIntent().getStringExtra( "dir" );
//                File d = new File( Constants.DIR_IMAGE + dir );
//                if ( !d.exists() && !d.isDirectory() ) {
//                    d.mkdirs();
//                }
//                File oringinFile = new File( pathOrigin );
//                if ( oringinFile == null || !oringinFile.exists() ) {
//                    return "1";
//                }
//                File targetFile = new File( pathTarget );
//                if ( !targetFile.exists() )
//                    targetFile.createNewFile();
//                in = new FileInputStream( oringinFile );
//                out = new FileOutputStream( targetFile );
//                byte[] buf = new byte[ 1024 ];
//                int len;
//                while ( ( len = in.read( buf ) ) > 0 )
//                    out.write( buf, 0, len );
//            } catch ( Exception e ) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    in.close();
//                    out.close();
//                } catch ( Exception e ) {
//                    e.printStackTrace();
//                }
//            }
//            return "0";
//        }
//
//        @Override
//        protected void onPostExecute( String s ) {
//            super.onPostExecute( s );
//            removeLoading();
//            Log.d( "Tag", "copy result : " + s );
//            Intent itt = new Intent();
//            itt.putExtra( "filePath", pathTarget );
//            setResult( RESULT_OK, itt );
//            finish();
//        }
//    }
//
//    /**
//     * 이동한 폴더를 저장한다. 나중에 재실행하면 여기로 간다
//     */
//    private void saveLastPath() {
//        SharedPreferences p = getSharedPreferences( "img_ex", 0 );
//        SharedPreferences.Editor e = p.edit();
//        e.putString( "last_path", currentFolder.getPath() );
//        e.commit();
//    }
//
//    /**
//     * 마지막으로 갔던 폴더 경로
//     *
//     * @return
//     */
//    private String getLastPath() {
//        SharedPreferences p = getSharedPreferences( "img_ex", 0 );
//        return p.getString( "last_path", rootDirectory );    // 저장해둔 게 없으면 루트로 감
//    }

}
