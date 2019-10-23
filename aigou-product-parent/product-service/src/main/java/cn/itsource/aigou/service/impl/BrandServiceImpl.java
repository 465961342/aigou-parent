package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.Brand;
import cn.itsource.aigou.mapper.BrandMapper;
import cn.itsource.aigou.query.BrandQuery;
import cn.itsource.aigou.service.IBrandService;
import cn.itsource.aigou.vo.BrandVo;
import cn.itsource.basic.util.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author LJWQ
 * @since 2019-10-12
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    /**
     * 分页高级查询
     *
     * @param query
     * @return
     */
    @Override
    public PageList<Brand> queryPage(BrandQuery query) {
        IPage<Brand> brandIPage =
                baseMapper.queryPage(new Page(query.getPage(), query.getRows()), query);
        PageList<Brand> pageList = new PageList<>(brandIPage.getTotal(),brandIPage.getRecords());

        return pageList;
    }


    /**
     * 根据类型加载品牌信息
     *
     * @param productTypeId
     * @return
     */
    @Override
    public BrandVo getByProductTypeId(Long productTypeId) {
        BrandVo vo = new BrandVo();
        List<Brand> brands = baseMapper.selectList(new QueryWrapper<Brand>().eq("product_type_id", productTypeId));
        vo.setBrands(brands);
        //首字母  排序  去重
        Set<String> letters = new TreeSet<>();
        for (Brand brand : brands) {
            letters.add(brand.getFirstLetter());
        }
        vo.setLetters(letters);
        return vo;
    }
}
