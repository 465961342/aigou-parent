package cb.itsource.aigou.repository;

import cn.itsource.aigou.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,Long> {

}
