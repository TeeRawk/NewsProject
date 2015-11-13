package com.technical.saion.newsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technical.saion.newsapp.R;
import com.technical.saion.newsapp.contract.NewsView;
import com.technical.saion.newsapp.contract.Presenter;
import com.technical.saion.newsapp.model.NewsItem;
import com.technical.saion.newsapp.presenter.NewsPresenter;
import com.technical.saion.newsapp.ui.adapter.NewsDecoration;
import com.technical.saion.newsapp.ui.adapter.NewsRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by saion on 09.11.2015.
 */
public class MainFragment extends Fragment implements NewsView {
    private RecyclerView mRecyclerView;
    private Presenter mPresenter = new NewsPresenter();
    private ArrayList<NewsItem> mNewsItems;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mPresenter.attachView(this);
        mPresenter.fetchNews(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_main_recyclerView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_main_progressBar);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_main_swipeRefresh);

        mNewsItems = new ArrayList<NewsItem>();
        mNewsItems.addAll(mPresenter.getListOfNews(getContext()));
        showProgress();
        showNews();
        hideProgress();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                mPresenter.fetchNews(getContext());
                mNewsItems.addAll(mPresenter.getListOfNews(getContext()));
                showNews();
                mRefreshLayout.setRefreshing(false);

            }
        });

        return view;
    }

    @Override
    public void showNews() {
        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter(getContext(), mNewsItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new NewsDecoration(20));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }


}
