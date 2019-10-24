package cn.itsource.aigou.service;

import cn.itsource.aigou.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * SKU 服务类
 * </p>
 *
 * @author kakarotto
 * @since 2019-10-17
 */
public interface ISkuService extends IService<Sku> {

    /**
     * 查询sku价格
     * @param productId
     * @return
     */
    List<Sku> getPrice(Long productId);

    /**
     *
     * @param productId
     * @param indexs
     * @return
     */
    Map<String,Object> skuChange(Long productId, String indexs);
}
