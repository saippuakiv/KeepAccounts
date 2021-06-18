package com.olhiim.keepaccounts.frag_record;

import com.olhiim.keepaccounts.R;
import com.olhiim.keepaccounts.db.DBManager;
import com.olhiim.keepaccounts.db.TypeBean;

import java.util.List;

public class OutcomeFragment extends BaseRecordFragment {

    @Override
    public void loadDataToGV(){
        super.loadDataToGV();
        //获取数据库中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("其他");
        typeIv.setImageResource(R.mipmap.ic_qita_fs);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
