package yswl.com.testmvp;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yswl.com.klibrary.base.MActivity;
import yswl.com.testmvp.base.BaseRecyclerAdapter;
import yswl.com.testmvp.base.DividerItemDecoration;
import yswl.com.testmvp.bean.BannerBean;
import yswl.com.testmvp.view.LoginV;

public class MainActivity extends MActivity {

    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    RefreshRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<BannerBean> bannerBeens = BannerBean.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findView(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");

        refreshLayout = findView(R.id.refresh);
        recyclerView = findView(R.id.recycler);

        linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager manager = new GridLayoutManager(this,2);

        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new RefreshRecyclerAdapter(this, bannerBeens);
        adapter.setItemLayoutId(R.layout.recycle_item_normal);
        adapter.setOnLoaderMoreListener(new BaseRecyclerAdapter.OnLoaderMoreListener() {
            @Override
            public void onLoadermore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<BannerBean> newDatas = new ArrayList<>();
                        newDatas.add(new BannerBean("https://pic4.zhimg.com/c9fd80f49fd8ed0791b5c7d215d98627_b.jpg", "long leg"));
                        adapter.addMoreItem(newDatas);
                        adapter.loaderComplete();
                    }
                }, 2500);
            }
        });
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<BannerBean> newDatas = new ArrayList<>();
                        newDatas.add(
                                new BannerBean("http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-19-17881407_1845958195665029_1132383288824954880_n.jpg", "新增"));
                        newDatas.add(
                                new BannerBean("http://imgsrc.baidu.com/forum/w%3D580/sign=35cfde4859ee3d6d22c687c373146d41/d5bd36738bd4b31ce3f3595487d6277f9c2ff8e7.jpg", "新增"));
                        adapter.addItem(newDatas);
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.loading();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });


    }


    int lastVisibleItem;

    class MyDecoration extends RecyclerView.ItemDecoration {
        public MyDecoration() {
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, 2);
        }
    }
}
