package cn.itsource.aigou.mapper;


import cn.itsource.aigou.ProductApplication;
import cn.itsource.aigou.domain.Brand;
import cn.itsource.aigou.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void queryPage() {
        BrandQuery query = new BrandQuery();
        query.setPage(1);
        query.setRows(10);
        query.setKeyword("Q");
        /*List<Brand> brands = brandMapper.queryPage(query);
        for (Brand brand : brands) {
            System.out.println(brand);
        }*/
        IPage<Brand> iPage = brandMapper.queryPage(new Page(query.getPage(),query.getRows()),query);
        System.out.println(iPage.getTotal());
        for (Brand brand : iPage.getRecords()) {
            System.out.println(brand);
        }


    }
}