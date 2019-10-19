package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.Product;
import cn.itsource.aigou.domain.ProductExt;
import cn.itsource.aigou.domain.Specification;
import cn.itsource.aigou.mapper.ProductExtMapper;
import cn.itsource.aigou.mapper.ProductMapper;
import cn.itsource.aigou.mapper.SpecificationMapper;
import cn.itsource.aigou.query.ProductQuery;
import cn.itsource.aigou.service.IProductService;
import cn.itsource.basic.util.PageList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author kakarotto
 * @since 2019-10-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Override
    @Transactional
    public boolean save(Product product) {
        //创建时间
        product.setCreateTime(System.currentTimeMillis());
        //t_product
        baseMapper.insert(product);//mybatis-plus自动返回生成的主键，主键生成到了product对象中
        //t_product_ext
        ProductExt ext = product.getExt();
        ext.setProductId(product.getId());
        productExtMapper.insert(ext);
        return true;
    }
    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage iPage = baseMapper.queryPage(new Page(query.getPage(),
                query.getRows()), query);
        return new PageList<>(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public boolean removeById(Serializable id) {
        baseMapper.deleteById(id);

        productExtMapper.deleteByProId(id);

        return super.removeById(id);
    }

    @Override
    @Transactional
    public boolean updateById(Product product) {
        //1.先保存product
        baseMapper.updateById(product);
        //2.在修改ext表的数据
        productExtMapper.updateById(product.getExt());
        return true;
    }

    /**
     *   * 根据商品ID查询商品的显示属性
     *   * @param productId
     *   * @return
     *   
     *
     * @param productId
     */
    @Override
    public List<Specification> getViewProperties(Long productId) {
        List<Specification> specifications = null;
        //查询商品表中的viewProperties
        Product product = baseMapper.selectById(productId);
        String viewProperties = product.getViewProperties();
        //判断是否为null

        if(StringUtils.isEmpty(viewProperties)){
            //根据商品类型查询属性表
            Long productTypeId = product.getProductTypeId();
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", productTypeId)
                    .eq("isSku", 0));
        }else{
            //转成List<Specification>
            specifications = JSONArray.parseArray(viewProperties,Specification.class);
        }
        return specifications;
    }

    /**
     * 保存显示属性
     * @param productId
     * @param specifications
     */
    @Override
    public void saveViewProperties(Long productId, List<Specification> specifications) {
        String viewProperties = JSON.toJSONString(specifications);
        baseMapper.updateViewProperties(productId,viewProperties);
    }

    /**
     *   * 根据商品ID查询商品的sku属性
     *   * @param productId
     *   * @return
     *   
     *
     * @param productId
     */
    @Override
    public List<Specification> getSkuProperties(Long productId) {
        List<Specification> specifications = null;
        //查询商品表中的skuProperties
        Product product = baseMapper.selectById(productId);
        String skuProperties = product.getSkuProperties();
        //判断是否为null

        if(StringUtils.isEmpty(skuProperties)){
            //根据商品类型查询属性表
            Long productTypeId = product.getProductTypeId();
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", productTypeId)
                    .eq("isSku", 1));
        }else{
            //转成List<Specification>
            specifications = JSONArray.parseArray(skuProperties,Specification.class);
        }
        return specifications;
    }
}
