package app.util;

import app.model.Product;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import javax.annotation.Nullable;
import java.util.List;

public class FunctionRepo {

    public static Function<List<Product>, Long> findBiggestProduct = new Function<List<Product>, Long>() {

        @Override
        public Long apply(@Nullable List<Product> products) {
//            long idBiggestProduct = Integer.MIN_VALUE;
            long idBiggestProduct = -1L;
            Ordering<Product> orderByWeight = new OrderProductsByWeight();
            int weightBiggestProduct = Integer.MIN_VALUE;

            List<Product> notNullList = Lists.newArrayList(FluentIterable.from(products)
                                                      .filter(Predicates.notNull())
                                                      .toList());
            notNullList.sort(orderByWeight);
            idBiggestProduct = notNullList.get(0).getId();
//            if(products == null) idBiggestProduct = -1L;
//            else {
//
//                for (Product product : products) {
//                    if(weightBiggestProduct < product.getWeight()) {
//                        weightBiggestProduct = product.getWeight();
//                        idBiggestProduct = product.getId();
//                    }
//                }
//            }
            return idBiggestProduct;
        }

    };

    public static Function<List<Product>, Integer> findTotalWeight = new Function<List<Product>, Integer>() {

        @Override
        public Integer apply(@Nullable List<Product> products) {
            int totalWeight = 0;

            products = FluentIterable.from(products)
                                     .filter(Predicates.notNull())
                                     .toList();
            for (Product product : products) {totalWeight += product.getWeight();}
//            if (products == null) {}
//            else {
//                for (Product product : products) {
//                    totalWeight += product.getWeight();
//                }
//            }
            return totalWeight;
        }
    };

}