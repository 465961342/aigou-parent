package cn.itsource.aigou.vo;

import cn.itsource.aigou.domain.Brand;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class BrandVo {

    private Set<String> letters = new TreeSet<>();

    private List<Brand> brands = new ArrayList<>();
}
