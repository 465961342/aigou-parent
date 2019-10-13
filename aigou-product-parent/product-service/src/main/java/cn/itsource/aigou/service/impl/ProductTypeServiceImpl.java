package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author LJWQ
 * @since 2019-10-12
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    /**
     * 加载类型树
     *
     * @return
     */
    @Override
    public List<ProductType> loadTypeTree() {
        //先查所有的一级类型
        //List<ProductType> productTypes = loadTypeTreeRecursive(0L);

        return loadTypeTreeLoop2();
    }

    /**
     * 递归
     * @param parentId
     * @return
     */
    private List<ProductType> loadTypeTreeRecursive(Long parentId){
        List<ProductType> productTypes =
                baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid",parentId));
        if(productTypes==null){
            return null;
        }
        for (ProductType productType : productTypes) {
            List<ProductType> children =loadTypeTreeRecursive(productType.getId());
            if(children!=null){
                productType.setChildren(children);
            }
        }
        return productTypes;
    }

    /**
     * 循环一   n*n次循环
     * @return
     */
    private List<ProductType> loadTypeTreeLoop(){
        //先查询所有
        List<ProductType> allProductTypes = baseMapper.selectList(null); //1000
        //再组装数据
        List<ProductType> firstLevelTypes = new ArrayList<>();
        //  1000
        for (ProductType productType : allProductTypes) {

            if(productType.getPid()==0){
                //如果你是一级类型，则放入firstLevelTypes
                firstLevelTypes.add(productType);
            }else{
                //如果不是，放入父类型的children属性中   1000
                for (ProductType parent : allProductTypes) {
                    if(parent.getId().equals(productType.getPid())){
                        parent.getChildren().add(productType);
                    }
                }
            }

        }
        return firstLevelTypes;
    }

    /**
     * 循环二 n+n次循环
     * @return
     */
    private List<ProductType> loadTypeTreeLoop2(){
        //先查询所有
        List<ProductType> allProductTypes = baseMapper.selectList(null); //1000
        //再组装数据
        List<ProductType> firstLevelTypes = new ArrayList<>();

        Map<Long,ProductType> productTypeMap = new HashMap<>();

        //将所有的productType存入map中
        for (ProductType productType : allProductTypes) {
            productTypeMap.put(productType.getId(),productType);
            productType.setChildren(null);
        }

        //再循环组装数据
        for (ProductType productType : allProductTypes) {
            //如果是一级
            if(productType.getPid()==0){
                firstLevelTypes.add(productType);
            }else{
                //如果不是一级
                ProductType parent = productTypeMap.get(productType.getPid());

                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                //将该类型放到其父级类型的子级类型中
                parent.getChildren().add(productType);

            }
        }
        return firstLevelTypes;
    }


}
