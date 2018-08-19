package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：tqzhang  on 18/8/2 10:52
 */
public class QaRepository extends BaseRepository {
    public void loadQAList(String lastId, String rn, final OnResultCallBack<QaListVo> listener) {
        apiService.getQAList(lastId, rn)
                .compose(RxSchedulers.<QaListVo>io_main())
                .subscribe(new RxSubscriber<QaListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(QaListVo qaListObject) {
                        listener.onNext(qaListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }
}