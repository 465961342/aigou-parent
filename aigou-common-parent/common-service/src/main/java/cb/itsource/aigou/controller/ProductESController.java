package cb.itsource.aigou.controller;

import cb.itsource.aigou.repository.ProductDocRepository;
import cn.itsource.aigou.domain.ProductDoc;
import cn.itsource.aigou.domain.ProductParam;
import cn.itsource.basic.util.PageList;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductESController {
    @Autowired
    private ProductDocRepository repository;
    /**
       * 批量保存
       * @param productDocList
       */
    @PostMapping("/es/saveBath")
    public void saveBatch(@RequestBody List<ProductDoc> productDocList){
        repository.saveAll(productDocList);
    }
    /**
       * 批量删除
       * @param ids
       */
    @PostMapping("/es/deleteBath")
    public void deleteBatch(@RequestBody List<Long> ids){
        for (Long id : ids) {
            repository.deleteById(id);
        }
    }

    @PostMapping("/es/products")
    public PageList<ProductDoc> search(@RequestBody ProductParam param) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //查询与过滤
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //关键字查询
        if (StringUtils.isNotEmpty(param.getKeyword())) {
            boolQueryBuilder.must(new MatchQueryBuilder("all", param.getKeyword()));
        }
        //类型编号
        if (param.getProductTypeId() != null) {
            boolQueryBuilder.must(new
                    TermQueryBuilder("productTypeId", param.getProductTypeId()));
        }
        //品牌编号
        if (param.getBrandId() != null) {
            boolQueryBuilder.must(new TermQueryBuilder("brandId", param.getBrandId()));
        }
        //最高价格和最低价格
        if (param.getMinPrice() != null) {
        boolQueryBuilder.must(new
                RangeQueryBuilder("maxPrice").gte(param.getMinPrice()));
        }
        if (param.getMaxPrice() != null) {
        boolQueryBuilder.must(new
                            RangeQueryBuilder("minPrice").lte(param.getMaxPrice()));
        }
        builder.withQuery(boolQueryBuilder);
        //排序
        //排序列
        String sortColumn = "saleCount";
        if (StringUtils.isNotEmpty(param.getSortField())) {
            sortColumn = param.getSortField();
        }
        //排序方式
        SortOrder sortOrder = SortOrder.DESC;
        if ("asc".equals(param.getSortType())) {
            sortOrder = SortOrder.ASC;
        }
        builder.withSort(new FieldSortBuilder(sortColumn).order(sortOrder));
        //分页
        builder.withPageable(PageRequest.of(param.getPage() - 1, param.getRows()));
        Page<ProductDoc> page = repository.search(builder.build());
        return new PageList<>(page.getTotalElements(), page.getContent());
    }

}
