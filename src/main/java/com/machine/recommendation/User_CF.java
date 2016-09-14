package com.machine.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class User_CF {
    public static void main(String[] args) throws IOException, TasteException {
        InputStream inputStream = User_CF.class.getClassLoader().getResourceAsStream("data/ratings.dat");
        DataModel model = new GroupLensDataModel(new File(User_CF.class.getClassLoader().getResource("data/ratings.dat").getPath()));
        System.out.print(""+model.getNumItems());
    }

}
