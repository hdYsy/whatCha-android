package com.lianjie.andorid.ui;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lianjie.andorid.R;
import com.lianjie.andorid.bean.LoadScrosses;
import com.lianjie.andorid.utils.BitmapUtil;
import com.lianjie.andorid.utils.DialogUtils;
import com.lianjie.andorid.utils.GlideUtils;
import com.lianjie.andorid.utils.GlobalInfoUtils;
import com.lianjie.andorid.utils.SPUtil;
import com.lianjie.andorid.utils.SystemDateUtils;
import com.lianjie.andorid.utils.internetutils.ComsentUtils;
import com.lianjie.andorid.utils.internetutils.URLConstant;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static java.lang.String.valueOf;

public class CameraActivity extends AppCompatActivity {
    private TextureView tv;
    private ImageView btn;
    private String mCameraId = "1";//摄像头id（通常0代表后置摄像头，1代表前置摄像头）
    private final int RESULT_CODE_CAMERA = 1;//判断是否有拍照权限的标识码
    private CameraDevice cameraDevice;
    private CameraCaptureSession mPreviewSession;
    private CaptureRequest.Builder mCaptureRequestBuilder, captureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private ImageReader imageReader;
    private int height = 0, width = 0;
    private Size previewSize;
    //    private ImageView iv;
    private boolean br_btcamera = true; //防止多次点击按钮
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0,0 );
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    private CameraActivity mContext;
    private String id;
    private LoadScrosses imageBean;
    private Intent intent;
    private ImageView img;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personface);
        ImageView mCallbaclk = findViewById(R.id.callbaclk);
        TextView mTitle = findViewById(R.id.title);
        mCallbaclk.setVisibility(View.GONE);
        mTitle.setText("人脸识别");
        //视频
        tv = (TextureView) findViewById(R.id.tv);
        img = (ImageView) findViewById(R.id.img); // 人脸大图
        btn = (ImageView) findViewById(R.id.btn);
//        iv= (ImageView) findViewById(R.id.iv);
        mContext = this;
        intent = getIntent();
        id = intent.getStringExtra("id");
        //添加 防止多次点击bt的逻辑
        if (br_btcamera) {
            br_btcamera = false;
            btn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View v) {
                    GlideUtils.getInstance().setImageWrap(btn, getResources().getDrawable(R.mipmap.pzh));
                    takePicture();
                }
            });
        } else {
            btn.setOnClickListener(null);
        }
        //拍照

        //设置TextureView监听
        tv.setSurfaceTextureListener(surfaceTextureListener);
//
    }
    //
    @Override
    protected void onPause() {
        super.onPause();
        if (cameraDevice != null) {
            stopCamera();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageReader != null) {
            imageReader.close();
            imageReader = null;
        }
        if (mPreviewSession != null) {
            mPreviewSession.close();
            mPreviewSession = null;
        }
        if (cameraDevice != null) {
            stopCamera();
        }

//
//        cameraDevice.close();
//        mPreviewSession.close();
//        imageReader.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    /**
     * TextureView的监听
     */
    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        //可用
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            CameraActivity.this.width = width;
            CameraActivity.this.height = height;
            openCamera();
        }


        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        //释放
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            stopCamera();
            return true;
        }

        //更新
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };


    /**
     * 打开摄像头
     */
    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        //设置摄像头特性
        setCameraCharacteristics(manager);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //提示用户开户权限
                String[] perms = {"android.permission.CAMERA"};
                RxPermissions rxPermissions = new RxPermissions(this);
                //动态申请权限
                rxPermissions.requestEach(perms).subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            // 用户已经同意该权限
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没选中不再询问,再次启动时，还会提示请求权限的对话框
                            Log.d("TAG", permission.name + " is denied. More info should be provided.");
                        } else {
                            finish();
                        }
                    }
                });

            } else {
                manager.openCamera(mCameraId, stateCallback, null);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置摄像头的参数
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setCameraCharacteristics(CameraManager manager) {
//        try {
            // 获取指定摄像头的特性
//            CameraCharacteristics characteristics
//                    = manager.getCameraCharacteristics(mCameraId);
        previewSize = getMatchingSize2(manager, mCameraId);
            // 获取摄像头支持的配置属性
//            StreamConfigurationMap map = characteristics.get(
//                    CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            // 获取摄像头支持的最大尺寸
//            Size largest = Collections.max(
//                    Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
            // 创建一个ImageReader对象，用于获取摄像头的图像数据
            imageReader = ImageReader.newInstance(previewSize.getWidth(),previewSize.getHeight(),
                    ImageFormat.JPEG, 2);
            //设置获取图片的监听
            imageReader.setOnImageAvailableListener(imageAvailableListener, null);
            // 获取最佳的预览尺寸

//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Size getMatchingSize2(CameraManager mCameraManager, String mCurrentCameraId){
        /**
         * 获取匹配的大小 这里是Camera2获取分辨率数组的方式,Camera1获取不同,计算一样
         * @return
         */
            Size selectSize = null;
            float selectProportion = 0;
            try {
                float viewProportion = (float)tv.getWidth() / (float)tv.getHeight();//计算View的宽高比
                CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(mCurrentCameraId);
                StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Size[] sizes = streamConfigurationMap.getOutputSizes(ImageFormat.JPEG);
                for (int i = 0; i < sizes.length; i++){
                    Size itemSize = sizes[i];
                    float itemSizeProportion = (float)itemSize.getHeight() / (float)itemSize.getWidth();//计算当前分辨率的高宽比
                    float differenceProportion = Math.abs(viewProportion - itemSizeProportion);//求绝对值
                    if (i == 0){
                        selectSize = itemSize;
                        selectProportion = differenceProportion;
                        continue;
                    }
                    if (differenceProportion <= selectProportion){ //判断差值是不是比之前的选择的差值更小
                        if (differenceProportion == selectProportion){ //如果差值与之前选择的差值一样
                            if (selectSize.getWidth() + selectSize.getHeight() < itemSize.getWidth() + itemSize.getHeight()){//选择分辨率更大的Size
                                selectSize = itemSize;
                                selectProportion = differenceProportion;
                            }

                        }else {
                            selectSize = itemSize;
                            selectProportion = differenceProportion;
                        }
                    }
                }

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            return selectSize;
}



    /**
     * 摄像头状态的监听
     */
    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        // 摄像头被打开时触发该方法
        @Override
        public void onOpened(CameraDevice cameraDevice) {
            CameraActivity.this.cameraDevice = cameraDevice;
            // 开始预览
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                takePreview();
            }
        }

        // 摄像头断开连接时触发该方法
        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            CameraActivity.this.cameraDevice.close();
            CameraActivity.this.cameraDevice = null;

        }

        // 打开摄像头出现错误时触发该方法
        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            cameraDevice.close();
        }
    };

    /**
     * 开始预览
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePreview() {
        SurfaceTexture mSurfaceTexture = tv.getSurfaceTexture();
        //设置TextureView的缓冲区大小
        mSurfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
        //获取Surface显示预览数据
        Surface mSurface = new Surface(mSurfaceTexture);
        try {
            //创建预览请求
            mCaptureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            // 设置自动对焦模式
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            //设置Surface作为预览数据的显示界面
            mCaptureRequestBuilder.addTarget(mSurface);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            cameraDevice.createCaptureSession(Arrays.asList(mSurface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        //开始预览
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        mPreviewSession = session;
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {

                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }


    /**
     * 拍照
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePicture() {
        try {
            if (cameraDevice == null) {
                return;
            }
            // 创建拍照请求
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            // 设置自动对焦模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 将imageReader的surface设为目标
            captureRequestBuilder.addTarget(imageReader.getSurface());
            // 获取设备方向
            int rotation = 3;//上面给的方向是 1  前置摄像头 给的270 旋转 否则就是 倒着，;
            // 根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION
                    , ORIENTATIONS.get(rotation));
            // 停止连续取景
            mPreviewSession.stopRepeating();
            //拍照
            CaptureRequest captureRequest = captureRequestBuilder.build();
            //设置拍照监听
            mPreviewSession.capture(captureRequest, captureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听拍照结果
     */
    private CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        // 拍照成功
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            // 重设自动对焦模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            // 设置自动曝光模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
//            try {
//                //重新进行预览    停止预览diao
//                mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }

        }

        @Override
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            super.onCaptureFailed(session, request, failure);
        }
    };

    /**
     * 监听拍照的图片
     */
    private ImageReader.OnImageAvailableListener imageAvailableListener = new ImageReader.OnImageAvailableListener() {
        // 当照片数据可用时激发该方法
        @Override
        public void onImageAvailable(ImageReader reader) {

            //先验证手机是否有sdcard
            String status = Environment.getExternalStorageState();
            if (!status.equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(getApplicationContext(), "你的sd卡不可用。", Toast.LENGTH_SHORT).show();
                return;
            }
            // 获取捕获的照片数据
            Image image = reader.acquireNextImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

//            //手机拍照都是存到这个路径
//            String filePath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/";
//            fileName = System.currentTimeMillis() + ".jpeg";
////            File fileWithByte = createFileWithByte(data);
////
//
//            File file = new File(filePath, picturePath);

            try {
                //存到本地相册    are  you  ready?   //拍照默认不保存到本地
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
//                fileOutputStream.write(data);
//                fileOutputStream.close();


                //显示图片
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);

                Bitmap bitmap1 = BitmapUtil.compressImage(bitmap);
                String face = bitmapToBase64(bitmap1);

                onRequestIMG(face);


            } finally {
                image.close();
            }
        }


    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case RESULT_CODE_CAMERA:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    //授权成功之后，调用系统相机进行拍照操作等
                    openCamera();
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    Toast.makeText(CameraActivity.this, "请开启应用拍照权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 启动拍照
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startCamera() {
        if (tv.isAvailable()) {
            if (cameraDevice == null) {
                openCamera();
            }
        } else {
            tv.setSurfaceTextureListener(surfaceTextureListener);
        }
    }

    /**
     * 停止拍照释放资源
     */
    private void stopCamera() {
        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }

    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 上传图片的
     *
     * @param
     * @param
     * @param
     */
    private void onRequestIMG(final String file) {
        if (cameraDevice != null) {
            stopCamera();
        }
        Runnable requestTask = new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                Map<String, Object> map = ComsentUtils.onSendDelatileFace(id, GlobalInfoUtils.Userid);
                try {
                    OkHttpClient client = new OkHttpClient();
                    // form 表单形式上传
                    MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    if (file != null) {
                        requestBody.addFormDataPart("face", file);
                    }
                    if (map != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : map.entrySet()) {
                            requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url(URLConstant.Base_startServer).post(requestBody.build()).tag(mContext).build();
                    Call call = client.newBuilder().readTimeout(GlobalInfoUtils.SECONDS, TimeUnit.SECONDS).build().newCall(request);
                    Response execute = call.execute();
                    Gson gson = new Gson();
                    imageBean = gson.fromJson(execute.body().string(), LoadScrosses.class);
                    if (imageBean != null) {
                        if (imageBean.getCode() == 1000) {
                            msg.what = GlobalInfoUtils.REQUEST_SUCCESS;
                        } else if (imageBean.getCode() == 1001) {
                            if (imageBean.getMsg().contains("请先完成")) {
                                msg.what = 111;
                            } else {
                                msg.what = GlobalInfoUtils.REQUEST_FAIL;
                            }
                        } else {
                            msg.what = GlobalInfoUtils.REQUEST_FAIL;
                        }

                    }

                } catch (IOException ex) {
                    msg.what = GlobalInfoUtils.REQUEST_FAIL;
                } finally {
                    msg.sendToTarget();

                }
            }
        };
        Thread requestThread = new Thread(requestTask);
        requestThread.start();


    }


    /**
     * 上传图片
     */
    @SuppressLint("HandlerLeak")
    Handler requestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalInfoUtils.REQUEST_SUCCESS:
                    btn.setImageResource(R.mipmap.icon_success);
                    String days = SystemDateUtils.getNowDate();
                    SPUtil.addSP("date", days);
                    GlobalInfoUtils.ServerState = 2;
                    setResult(GlobalInfoUtils.Camera_ReSultCode);
                    finish();


                    break;
                case GlobalInfoUtils.REQUEST_FAIL:
                    setResult(2, intent); //上传失 败
                    img.setImageResource(R.mipmap.shibai);
                    btn.setImageResource(R.mipmap.icon_fail);
                    if (imageBean != null) {
                        if (imageBean.getMsg() != null) {
                            DialogUtils.showToast(mContext, imageBean.getMsg());
                            finish();
                        } else {
                            finish();
                            break;
                        }
                    } else {
                        finish();
                        break;
                    }


                case 111:
                    btn.setVisibility(View.GONE);
                    setResult(111, intent); //上传失 败
                    DialogUtils.showToast(mContext, imageBean.getMsg());
                    try {
                        Thread.currentThread().sleep(500);
                        finish();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    };


}
