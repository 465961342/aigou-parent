package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.ProductApplication;
import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.service.IProductTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class ProductTypeServiceImplTest {
    @Autowired
    private IProductTypeService productTypeService;
    @Test
    public void loadTypeTree() {
        List<ProductType> productTypes = productTypeService.loadTypeTree();

    }
}