package cb.itsource.aigou.controller;

import cb.itsource.aigou.repository.ProductDocRepository;
import cn.itsource.aigou.domain.ProductDoc;
import org.springframework.beans.factory.annotation.Autowired;
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

}
