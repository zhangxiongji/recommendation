package com.machine.recommendation;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class User_CF {
    public static void main(String[] args){
        InputStream inputStream = User_CF.class.getClassLoader().getResourceAsStream("data/ratings.dat");
        DataModel model = new GroupLensDataModel(new File(inputStream));

    }

}
