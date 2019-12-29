package com.rishabh.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.funtoolearn.model.ApiResponse
import com.rishabh.news.adapter.NewsAdapter
import com.rishabh.news.model.TopHeadlines
import com.rishabh.news.repository.NewsRepository
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val newsRepository: NewsRepository) : ViewModel() {
    private var newsList: MutableList<TopHeadlines>
    private var compositeDisposable = CompositeDisposable()
    var showProgress: MutableLiveData<Boolean> = MutableLiveData()
    var newsAdapter: NewsAdapter

    init {
        newsAdapter = NewsAdapter()
        newsList = ArrayList<TopHeadlines>()
        getNewsList()
    }


    fun getNewsList() {
        showProgress.value = true
        newsRepository.getTopHeadlines("in").observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ApiResponse<List<TopHeadlines>>> {
                override fun onComplete() {
                    showProgress.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ApiResponse<List<TopHeadlines>>) {
                    t.let {
                        it.articles?.let {
                            newsList.addAll(it)
                            newsAdapter.notifyData(newsList)

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}