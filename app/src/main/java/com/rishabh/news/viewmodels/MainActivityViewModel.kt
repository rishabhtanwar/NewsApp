package com.rishabh.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rishabh.news.adapter.NewsAdapter
import com.rishabh.news.model.ApiResponse
import com.rishabh.news.model.TopHeadlines
import com.rishabh.news.repository.NewsRepository
import com.rishabh.news.util.Util
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val newsRepository: NewsRepository) : ViewModel() {
    private var newsList: MutableList<TopHeadlines>
    private var compositeDisposable = CompositeDisposable()
    var showProgress: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<String> = MutableLiveData()
    var newsAdapter: NewsAdapter


    init {
        newsAdapter = NewsAdapter()
        newsList = ArrayList()
        getNewsList()
    }

    fun setObserver(newsClickObserver: SingleObserver<TopHeadlines>) {
        newsAdapter.setObserver(newsClickObserver)

    }


    private fun getNewsList() {
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
                    showProgress.value = false
                    showError.value = Util.getErrorMessage(e)
                }

            })
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}