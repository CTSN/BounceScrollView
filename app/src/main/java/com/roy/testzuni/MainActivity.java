package com.roy.testzuni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BounceScrollView mSc;
    private BannerView mGlBanner;
    /**
     * 千兆灯饰欧式客厅水晶吊灯现代客厅灯简约复式楼酒店吊灯主卧室灯餐厅灯具
     */
    private TextView mTvTitle;
    /**
     * 【8.18周年庆】店内满999减300！人气客卧6件套立减500，再抢200元现金红包！
     */
    private TextView mTvContent;
    /**
     * 型号：8820-10
     */
    private TextView mTvType;
    /**
     * ¥
     */
    private TextView mTvSub;
    /**
     * 1400.00
     */
    private TextView mTvPrice;
    /**
     * 当前为预售活动商品 付款后7天后发货
     */
    private TextView mTvExplain;
    /**
     * 销量：5689
     */
    private TextView mTvSellCount;
    /**
     * 10头
     */
    private TextView mTvSelect;
    private LinearLayout mLlSelect;
    private View mV1;
    /**
     * 店铺单笔订单不满188元，收取23元快递费
     */
    private TextView mTvExpress;
    private LinearLayout mLlExpress;
    private View mV2;
    private LinearLayout mLlService;
    /**
     * 限时特卖
     */
    private TextView mTvCountTime;
    private CardView mC1;
    /**
     * 附近体验馆
     */
    private TextView mTvAddressTitle;
    /**
     * 到体验店看实体灯
     */
    private TextView mTvAddressGo;
    private View mV3;
    private ImageView mIvAddress;
    /**
     * 广东省中山市市区灯网体验店
     */
    private TextView mTvAddressName;
    /**
     * 广东省中山市市区灯网体验店
     */
    private TextView mTvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSc = (BounceScrollView) findViewById(R.id.sc);
        mSc.setCallBack(new BounceScrollView.Callback() {
            @Override
            public void callback(int distance) {
                Toast.makeText(MainActivity.this,distance+"---->回弹监听",Toast.LENGTH_SHORT).show();
            }
        });
        mGlBanner = (BannerView) findViewById(R.id.gl_banner);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mTvSub = (TextView) findViewById(R.id.tv_sub);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvExplain = (TextView) findViewById(R.id.tv_explain);
        mTvSellCount = (TextView) findViewById(R.id.tv_sell_count);
        mTvSelect = (TextView) findViewById(R.id.tv_select);
        mLlSelect = (LinearLayout) findViewById(R.id.ll_select);
        mV1 = (View) findViewById(R.id.v1);
        mTvExpress = (TextView) findViewById(R.id.tv_express);
        mLlExpress = (LinearLayout) findViewById(R.id.ll_express);
        mV2 = (View) findViewById(R.id.v2);
        mLlService = (LinearLayout) findViewById(R.id.ll_service);
        mTvCountTime = (TextView) findViewById(R.id.tv_count_time);
        mC1 = (CardView) findViewById(R.id.c1);
        mTvAddressTitle = (TextView) findViewById(R.id.tv_address_title);
        mTvAddressGo = (TextView) findViewById(R.id.tv_address_go);
        mV3 = (View) findViewById(R.id.v3);
        mIvAddress = (ImageView) findViewById(R.id.iv_address);
        mTvAddressName = (TextView) findViewById(R.id.tv_address_name);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mSc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sc:
                break;
        }
    }
}
