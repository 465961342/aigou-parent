package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.Sku;
import cn.itsource.aigou.mapper.SkuMapper;
import cn.itsource.aigou.service.ISkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author kakarotto
 * @since 2019-10-17
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    /**
     * 查询sku价格
     *
     * @param productId
     * @return
     */
    @Override
    public List<Sku> getPrice(Long productId) {
        return baseMapper.selectList(new QueryWrapper<Sku>().eq("product_id", productId));
    }


    /**
     * @param productId
     * @param indexs
     * @return
     */
    @Override
    public Map<String, Object> skuChange(Long productId, String indexs) {
        Sku sku = baseMapper.selectOne(new QueryWrapper<Sku>().eq("product_id", productId).eq("indexs", indexs));
        HashMap<String, Object> map = new HashMap<>();
        map.put("price", sku.getPrice());
        map.put("store", sku.getAvailableStock());
        return map;
    }
}
