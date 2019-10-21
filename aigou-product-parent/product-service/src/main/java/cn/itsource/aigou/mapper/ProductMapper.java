package cn.itsource.aigou.mapper;

import cn.itsource.aigou.domain.Product;
import cn.itsource.aigou.domain.Specification;
import cn.itsource.aigou.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author kakarotto
 * @since 2019-10-17
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage queryPage(Page page, @Param("query") ProductQuery query);

    /**
     * 修改显示属性
     * @param productId
     * @param viewProperties
     */
    void updateViewProperties(@Param("productId") Long productId, @Param("viewProperties")String viewProperties);


    /**
     * 修改sku属性
     * @param productId
     * @param skuPropertie
     */
    void updateSkuProperties(@Param("productId")Long productId, @Param("skuProperties")String skuPropertie);

    /**
     * 批量上架
     * @param ids
     * @param onSaleTime
     */
    void onSale(@Param("ids")List<Long> ids, @Param("onSaleTime")long onSaleTime);

    /**
     * 批量下架
     * @param ids
     * @param offSaleTime
     */
    void offSale(@Param("ids")List<Long> ids, @Param("offSaleTime")long offSaleTime);
}
