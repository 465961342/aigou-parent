package cn.itsource.aigou.service;

import cn.itsource.aigou.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author LJWQ
 * @since 2019-10-12
 */
public interface IProductTypeService extends IService<ProductType> {
    /**
     * 加载类型树
     * @return
     */
    List<ProductType> loadTypeTree();

    void genHomePage();
}
