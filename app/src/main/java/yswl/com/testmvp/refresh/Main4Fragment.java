package yswl.com.testmvp.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.nostra13.universalimageloader.utils.L;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import yswl.com.klibrary.base.MFragment;
import yswl.com.klibrary.http.CallBack.HttpCallback;
import yswl.com.klibrary.http.HttpClientProxy;
import yswl.com.testmvp.R;

public class Main4Fragment extends MFragment implements OnRefreshListener, OnLoadMoreListener, HttpCallback<JSONObject> {
    private static final String TAG = Main4Fragment.class.getSimpleName();
    private int mPageNum = 1;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerCharactersAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new RecyclerCharactersAdapter(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_style, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setLoadingMore(true);
                            }
                        }, 100);

                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
        mAdapter.stop();
    }

    @Override
    public void onRefresh() {
        String url = "https://raw.githubusercontent.com/Aspsine/SwipeToLoadLayout/dev/app/src/main/assets/characters.json";
        L.e(TAG, "onRefresh ");
        HttpClientProxy.getInstance().getAsyn(url, 10, null, this);
    }

    @Override
    public void onLoadMore() {
        String url = "https://raw.githubusercontent.com/Aspsine/SwipeToLoadLayout/dev/app/src/main/assets/characters.json?" + new Date().toString();
        HttpClientProxy.getInstance().getAsyn(url, 11, null, this);
    }

    @Override
    public void onSucceed(int requestId, JSONObject result) {
        if (result == null) return;
        final SectionCharacters characters = SectionCharacters.jsonToObj(result);
        if (requestId == 10) {
            swipeToLoadLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<Character> characters1 = characters.getCharacters();
                    List<Section> sections = characters.getSections();
                    mPageNum = 0;
                    mAdapter.setList(characters1, sections.subList(0, mPageNum + 1));
                    swipeToLoadLayout.setRefreshing(false);
                }
            }, 3000);
        } else if (requestId == 11) {
            swipeToLoadLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mPageNum < 3) {
                        List<Section> sections = characters.getSections();
                        mPageNum++;
                        mAdapter.append(sections.subList(mPageNum, mPageNum + 1));
                    } else {
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                    }
                    swipeToLoadLayout.setLoadingMore(false);
                }
            }, 3000);
        }
    }

    @Override
    public void onFail(int requestId, String errorMsg) {
        swipeToLoadLayout.setRefreshing(false);
    }


}
