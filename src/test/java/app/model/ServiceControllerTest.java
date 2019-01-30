package app.model;

import app.controller.ServiceController;
import app.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceControllerTest {

    @Mock
    Product product;

    @Mock
    ProductService productServiceMock;

    @InjectMocks
    ServiceController serviceController;

    List<Product> productList;

    @Before
    public void setUp() throws Exception {
          productList = Arrays.asList(new Product(1L, "Apple", 100),
                new Product(2L, "Orange", 150),
                new Product(3L, "Lemon", 75));
    }

    @Spy
    final List<Product> spyList = new ArrayList<>();

    @Test
    public void shouldReturnProductList() {
        when(productServiceMock.findAll()).thenReturn(productList);
        assertThat(productList, is(serviceController.getProducts()));
    }

    @Test
    public void shouldReturnSingleProduct() {
        when(productServiceMock.findById(2L)).thenReturn(Optional.of(productList.get(1)));
        assertThat(Optional.of(productList.get(1)), is(serviceController.getProductById(2L)));
    }

    @Test
    public void shouldSaveNewProduct() {
        when(productServiceMock.save(product)).thenReturn(product);
        assertThat(product, is(serviceController.storeProduct(product)));
    }

    @Test
    public void shouldModifyProductNotNull() {
        when(productServiceMock.findById(2L)).thenReturn(Optional.of(productList.get(1)));
        when(productServiceMock.save(productList.get(1))).thenReturn(productList.get(1));
        assertThat(productList.get(1), equalTo(serviceController.modifyProduct(productList.get(1))));
    }

    @Test
    public void shouldModifyProductNull() {
        when(productServiceMock.findById(0L)).thenReturn(Optional.of(product));
//        when(productServiceMock.save(productList.get(1))).thenReturn(productList.get(1));
        assertThat(product, equalTo(serviceController.modifyProduct(product)));
    }

    @Test
    public void shouldDeleteProduct() {
        assertThat("{\"message\":\"Successfully deleted\"}", equalTo(serviceController.deleteProductById(2L)));
    }

    @Test
    public void shouldReturnBiggestProduct() {
        when(productServiceMock.findAll()).thenReturn(productList);
        when(productServiceMock.findById(2L)).thenReturn(Optional.of(productList.get(1)));
        Assert.assertSame(productList.get(1), serviceController.getBiggestProduct());
        assertThat(productList.get(1), equalTo(serviceController.getBiggestProduct()));
        verify(productServiceMock, atLeastOnce()).findAll();
    }

    @Test
    public void shouldReturnTotalWeight() {
        spyList.add(new Product(1L, "Apple", 100));
        spyList.add(new Product(2L, "Orange", 150));
        spyList.add(new Product(3L, "Lemon", 75));
        when(productServiceMock.findAll()).thenReturn(productList);
        Assert.assertEquals("{\"totalWeight\":\"325\"}", serviceController.getTotalWeightOfProducts());
        Assert.assertEquals(3, spyList.size());
        verify(spyList, times(3)).add(any(Product.class));
        verify(spyList, times(1)).size();
    }

    @Test
    public void shouldReturnTrueOnCompleteProductCheckRequest() {
        when(productServiceMock.findAll()).thenReturn(productList);
        Assert.assertTrue(serviceController.checkProductDescriptionsComplete());
        verify(productServiceMock, times(1)).findAll();
    }


}
