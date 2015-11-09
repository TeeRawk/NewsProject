package com.technical.saion.newsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technical.saion.newsapp.R;
import com.technical.saion.newsapp.contract.NewsContract;
import com.technical.saion.newsapp.model.NewsItem;
import com.technical.saion.newsapp.presenter.NewsPresenter;
import com.technical.saion.newsapp.ui.adapter.NewsDecoration;
import com.technical.saion.newsapp.ui.adapter.NewsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saion on 09.11.2015.
 */
public class MainFragment extends Fragment implements NewsContract.View {
    private RecyclerView mRecyclerView;
    private  NewsPresenter mNewsPresenter=new NewsPresenter();
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

        mNewsPresenter.attachView(this);
        mNewsPresenter.fetchNews(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNewsPresenter.detachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.fragment_main_recyclerView);
        mProgressBar=(ProgressBar) view.findViewById(R.id.fragment_main_progressBar);

        mRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.fragment_main_swipeRefresh);

        mNewsItems=new ArrayList<NewsItem>();
        mNewsItems.addAll(mNewsPresenter.getListOfNews(getContext()));

        showNews(mNewsItems);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                mNewsPresenter.fetchNews(getContext());
                mNewsItems.addAll(mNewsPresenter.getListOfNews(getContext()));
                showNews(mNewsItems);
                mRefreshLayout.setRefreshing(false);

            }
        });

        return view;
    }

    @Override
    public void showNews(List<NewsItem> repositoryList) {
        NewsRecyclerAdapter adapter=new NewsRecyclerAdapter(getContext(),mNewsItems);
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

    @Override
    public void showInfoMessage(@NonNull String message) {

    }

    @Override
    public void showError() {

    }
}
