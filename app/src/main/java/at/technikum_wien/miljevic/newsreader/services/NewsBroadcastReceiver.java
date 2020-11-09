package at.technikum_wien.miljevic.newsreader.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import at.technikum_wien.miljevic.newsreader.dao.NewsEntity;
import at.technikum_wien.miljevic.newsreader.news.NewsViewModel;

public class NewsBroadcastReceiver extends BroadcastReceiver {

    private final NewsViewModel mViewModel;

    public NewsBroadcastReceiver(NewsViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        List<NewsEntity> newsEntities = (List<NewsEntity>) intent.getSerializableExtra(NewsIntentService.DOWNLOAD_TASK_OUT);
        mViewModel.updateNewsData(newsEntities);
    }
}
