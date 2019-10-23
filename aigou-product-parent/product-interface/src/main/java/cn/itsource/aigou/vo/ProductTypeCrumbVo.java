package cn.itsource.aigou.vo;

import cn.itsource.aigou.domain.ProductType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductTypeCrumbVo {
    /**
       * 当前类型
       */
    private ProductType currentType;
    /**
       * 同级别的其他类型
       */
    private List<ProductType> otherTypes = new ArrayList<>();
}
