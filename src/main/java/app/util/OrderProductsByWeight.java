package app.util;

import app.model.Product;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderProductsByWeight extends Ordering<Product> {

    @Override
    public int compare(Product firstProduct, Product secondProduct) {
        return Ints.compare(secondProduct.getWeight(), firstProduct.getWeight());
    }

}
