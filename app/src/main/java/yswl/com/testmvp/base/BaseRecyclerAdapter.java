package yswl.com.testmvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yswl.com.testmvp.R;

/**
 * base RecyclerView 适合加载如listview的数据 适配器
 * 上拉加载更多。
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {

    private static final String TAG = "BaseRecyclerAdapter";
    private Context context;//上下文
    private List<T> list;//数据源

    public List<T> getList() {
        return list;
    }

    private LayoutInflater inflater;//布局器
    private int itemLayoutId;//布局id
    private OnItemClickListener listener;
    private OnLoaderMoreListener loaderMoreListener;
    private OnItemLongClickListener longClickListener;//长按监听器
    private RecyclerView recyclerView;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status = PULLUP_LOAD_MORE;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnLoaderMoreListener(OnLoaderMoreListener listener) {
        this.loaderMoreListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnLoaderMoreListener {
        void onLoadermore();
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.e(TAG, "onAttachedToRecyclerView" );
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Log.e(TAG, "onDetachedFromRecyclerView" );
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

//    public abstract List<T> initData();

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        Log.e(TAG, "BaseRecyclerAdapter" );
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder viewType:" + viewType);
        View view = null;
        if (viewType == TYPE_NORMAL) {
            view = inflater.inflate(itemLayoutId, parent, false);
        }
        if (viewType == TYPE_FOOT) {
            view = inflater.inflate(R.layout.recycle_item_foot, parent, false);
        }
        return BaseRecyclerHolder.getRecyclerHolder(context, view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder position:" + position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItemViewType(position) == TYPE_NORMAL) {
                    if (listener != null && view != null && recyclerView != null) {
                        int position = recyclerView.getChildAdapterPosition(view);
                        listener.onItemClick(recyclerView, view, position);
                    }
                } else if (getItemViewType(position) == TYPE_FOOT) {
                    if (loaderMoreListener != null)
                        loaderMoreListener.onLoadermore();
                }

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (getItemViewType(position) == TYPE_NORMAL) {
                    if (longClickListener != null && view != null && recyclerView != null) {
                        int position = recyclerView.getChildAdapterPosition(view);
                        longClickListener.onItemLongClick(recyclerView, view, position);
                        return true;
                    }
                } else if (getItemViewType(position) == TYPE_FOOT) {
                }

                return false;
            }
        });
        if (getItemViewType(position) == TYPE_NORMAL) {
            convert(holder, position);
        }else  if (getItemViewType(position) == TYPE_FOOT) {
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    holder.setText(R.id.loader_more_tip, "上拉加载更多...");
                    holder.setProgress(R.id.loader_more_pb, View.GONE);
                    break;
                case LOADING_MORE:
                    holder.setText(R.id.loader_more_tip, "正在加载中...");
                    holder.setProgress(R.id.loader_more_pb, View.VISIBLE);
                    if (loaderMoreListener != null)
                        loaderMoreListener.onLoadermore();
                    break;
            }
        }

    }
    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder      ViewHolder
     * @param position    位置
     */
    public abstract void convert(BaseRecyclerHolder holder, int position);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size()+1;
    }

    public static final int TYPE_TOP = 0;
    public static final int TYPE_FOOT = 2;
    public static final int TYPE_NORMAL = 1;

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType position:" + position);
        if (position == getItemCount()-1) {
            return TYPE_FOOT;
        } else {
            return TYPE_NORMAL;
        }
    }

    /**
     * 插入一项
     *
     * @param item
     * @param position
     */
    public void insert(T item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    //添加多条数据
    public void addItem(List<T> newDatas) {
        newDatas.addAll(list);
        list.clear();
        list.addAll(newDatas);
        notifyDataSetChanged();
    }

    //上拉加载更多
    public void addMoreItem(List<T> newDatas) {
        list.addAll(newDatas);
//        notifyDataSetChanged();
    }

    /**
     * 删除一项
     *
     * @param position 删除位置
     */
    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    public void loaderComplete() {
        load_more_status = PULLUP_LOAD_MORE;
        notifyDataSetChanged();
    }

    public void loading() {
        load_more_status = LOADING_MORE;
        notifyDataSetChanged();
    }


}