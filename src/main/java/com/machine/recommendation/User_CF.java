package com.machine.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class User_CF {
    public static void main(String[] args) throws IOException, TasteException {
        RandomUtils.useTestSeed();
        InputStream inputStream = User_CF.class.getClassLoader().getResourceAsStream("data/ratings.dat");
        final DataModel model = new GroupLensDataModel(new File(User_CF.class.getClassLoader().getResource("data/ratings.dat").getPath()));

        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

        RecommenderBuilder builder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);

        System.out.println(""+score);

        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3,similarity,model);
        Recommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);

        List<RecommendedItem>  recommendedItems = recommender.recommend(1,2);

        for(RecommendedItem recommendedItem : recommendedItems){
            System.out.print(recommendedItem);
        }
        System.out.print(""+model.getNumItems());
    }

}
