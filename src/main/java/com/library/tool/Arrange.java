package com.library.tool;

import com.library.model.Work;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinTi on 2016/7/18.
 */
public class Arrange {

    public List<List<String>> work(List<Work> works) {
        String s[] = {"第1-2节", "第3-4节", "午班", "第5-6节", "第7-8节", "晚班"};
        String s1[] = {"星期一 ","星期二 ","星期三 ","星期四 ","星期五 ","星期六 ","星期天 "};
        List<List<String>> row = new ArrayList<List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("");
        for (int y = 0; y < s1.length; y++) {
            list1.add(s1[y]);
        }
        row.add(list1);
        for (int i = 0; i < s.length; i++) {
            List<String> list = new ArrayList<String>();
            list.add(s[i]);
            for (int o = 0; o < s1.length; o++) {
                boolean b = false;
                for (int p = 0; p < works.size(); p++) {
                    if (o < 5) {
                        if ((s1[o] + s[i]).equals(works.get(p).getwWorkTime().getWtTime())) {
                            list.add(works.get(p).getwWorkContent().getWcCon());
                            b = true;
                        }
                    } else {
                        if (i == 0) {
                            if ((s1[o] + "上午").equals(works.get(p).getwWorkTime().getWtTime())) {
                                list.add(works.get(p).getwWorkContent().getWcCon());
                                b = true;
                            }
                        } else if (i == 3) {
                            if ((s1[o] + "下午").equals(works.get(p).getwWorkTime().getWtTime())) {
                                list.add(works.get(p).getwWorkContent().getWcCon());
                                b = true;
                            }
                        } else if (i == 2) {
                            if ((s1[o] + s[i]).equals(works.get(p).getwWorkTime().getWtTime())) {
                                list.add(works.get(p).getwWorkContent().getWcCon());
                                b = true;
                            }
                        } else if (i == 5) {
                            if ((s1[o] + s[i]).equals(works.get(p).getwWorkTime().getWtTime())) {
                                list.add(works.get(p).getwWorkContent().getWcCon());
                                b = true;
                            }
                        }
                    }
                }
                if (!b) {
                    list.add("");
                }
                if (i != 0 && i != 3 && i != 2 && i != 5 && o >= 4){
                    o = o + 2;
                }
            }
            row.add(list);
        }
        return row;
    }
}
