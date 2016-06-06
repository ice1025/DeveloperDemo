package com.bing.developer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ice on 2016/6/1.
 */
public class CircleImageView extends ImageView{

    private Bitmap mask;
    private Paint paint;

    private int borderColor = 0xffffffff;
    private int roundWidth = 0;
    private int roundHeight=0;
    private int borderWidth=0;

    private static final Xfermode MASK_XFERMODE;
    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE= new PorterDuffXfermode(localMode);
    }


    public CircleImageView(Context context) {
        super(context);
        this.setScaleType(ScaleType.CENTER_CROP);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setScaleType(ScaleType.CENTER_CROP);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setScaleType(ScaleType.CENTER_CROP);
    }

    private Bitmap makeDst(int w,int h){
        Bitmap bm = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_4444);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.parseColor("#ffffffff"));
        c.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),w,h,p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable locaDrawable = getDrawable();
        if(locaDrawable==null){
            return;
        }
        try {
            initPaint();
            //获取drawable的宽和高
            int dWidth = locaDrawable.getIntrinsicWidth();
            int dHeight = locaDrawable.getIntrinsicHeight();

            float scale = 1.0f;

            scale = Math.max(getWidth()*1.0f / dWidth,getHeight()*1.0f /dHeight);

            locaDrawable.setBounds(0,0,(int)(scale*dWidth),(int)(scale*dHeight));

            float f1 = getWidth();
            float f2 = getHeight();
            int i = canvas.saveLayer(0.0F,0.0F,f1,f2,null,Canvas.ALL_SAVE_FLAG);
            int j = getWidth();
            int k = getHeight();
            locaDrawable.draw(canvas);
            if((this.mask==null)||(this.mask.isRecycled())){
                if (this.roundHeight==0&&this.roundWidth==0){
                    this.roundHeight = getHeight();
                    this.roundWidth = getWidth();
                }
                this.mask = makeDst(roundWidth,roundHeight);
            }
            canvas.drawBitmap(this.mask,0.0F,0.0F,this.paint);
            drawBorder(canvas,j,k);
            drawPercent(canvas,paint);
            canvas.restoreToCount(i);
            return;
        }catch (Exception localException){
            localException.printStackTrace();
        }
    }
    public void setRound(int roundWidthDP,int roundHeightDP){
        this.roundHeight=roundHeightDP;
        this.roundWidth = roundWidthDP;

        float density = this.getContext().getResources().getDisplayMetrics().density;
        roundWidth = (int)(roundWidth*density);
        roundHeight = (int)(roundHeight*density);

    }
    public  void setBorder(int color,int widthPx){
        if(color==-1){
            borderColor=0xffffffff;
        }else{
            this.borderColor=color;
        }
        this.borderWidth=widthPx;

    }
    public void setBorder(int widthPX){
        borderColor=0xffececec;
        this.borderWidth = widthPX;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        super.setColorFilter(cf);
        initPaint();
        paint.setColorFilter(cf);
    }

    private void drawBorder(Canvas canvas, final int width, final int height) {
        if(this.borderWidth==0){
            return;
        }
        Paint mBorPaint = new Paint();
        mBorPaint.setStyle(Paint.Style.STROKE);
        mBorPaint.setAntiAlias(true);
        mBorPaint.setColor(this.borderColor);
        mBorPaint.setStrokeWidth(borderWidth);
        int step = borderWidth/2;
        canvas.drawRoundRect(new RectF(0+step,step,getWidth()-step,getHeight()-step),width-borderWidth,height-borderWidth,mBorPaint);
    }

    private void initPaint() {
        if(this.paint==null){
            Paint localPaint1 = new Paint();
            this.paint = localPaint1;
            this.paint.setFilterBitmap(false);
            Paint locaPaint2 = this.paint;
            Xfermode localXfermode1 = MASK_XFERMODE;
            @SuppressWarnings("unused")
            Xfermode localXfermode2 = locaPaint2.setXfermode(localXfermode1);
        }
    }

/////////////////////////////////////////////////////////////////////////////
    public enum Status{
        RUNNING,NONE
    }

    private static final int DEFAULT_TEXTCOLOT = 0xff0074a2;
    private static final int DEFAULT_TEXTSIZE = 32;
    private float mPercent;
    private int percent;
    private Bitmap mScaledBitmap;
    private float mLeft;
    private int mSpeed=10;
    private int mRepeatCount=0;
    private Status mFlag = Status.NONE;
    private int mTextColot = DEFAULT_TEXTCOLOT;
    private int mTextSize = DEFAULT_TEXTSIZE;

    public void setmTextColot(int mTextColot) {
        this.mTextColot = mTextColot;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public void setPercent(float percent){
        mFlag = Status.RUNNING;
        mPercent = percent;
        this.percent= (int) percent*100;
        postInvalidate();
    }

    public void setStatus(Status status){
        mFlag=status;
    }

    public void clear(){
        mFlag = Status.NONE;
        if(mScaledBitmap!=null){
            mScaledBitmap.recycle();
            mScaledBitmap=null;
        }
        postInvalidate();
    }

    protected void drawPercent(Canvas canvas, Paint paint) {
        if(mFlag==Status.RUNNING){
           if(mScaledBitmap==null){
               Bitmap mBitmap = BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.percent_wave);
               mScaledBitmap= Bitmap.createScaledBitmap(mBitmap,mBitmap.getWidth(),mBitmap.getHeight(),false);
               mBitmap.recycle();
               mBitmap=null;
               mRepeatCount = (int) Math.ceil(getWidth()/mScaledBitmap.getWidth()+0.5)+1;
           }
            for (int i = 0; i < mRepeatCount; i++) {
                canvas.drawBitmap(mScaledBitmap,mLeft+(i-1)*mScaledBitmap.getWidth(),-mPercent*getHeight(),null);
            }
            if(percent<=100){
                String str = percent+"%";
                paint.setColor(mTextColot);
                paint.setTextSize(mTextSize);
                canvas.drawText(str,(getWidth()-paint.measureText(str))/2,getHeight()/2+mTextSize/2,paint);

                mLeft+=mSpeed;
                if(mLeft>=mScaledBitmap.getWidth())
                    mLeft=0;
                postInvalidateDelayed(30);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    /**
     * 绘制形状
     */
    public  Bitmap getBitMap(){
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if(getWidth()!=getHeight()){
            canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),roundWidth,roundHeight,paint);
        }else{
            canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2,paint);
        }

        return bitmap;
    }

}
