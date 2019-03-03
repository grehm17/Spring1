package com.geekbrains.springbootproject.repositories.Specs;

import com.geekbrains.springbootproject.entities.Good;
import org.springframework.data.jpa.domain.Specification;

public class GoodSpec {
    public static Specification<Good> titleLike(String pattern){
        return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+pattern+"%");
    }

    public static Specification<Good> costGreaterorEq(double from){
        return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),from);
    }

    public static Specification<Good> costLesserOrEq(double to){
        return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"),to);
    }

    public static Specification<Good> createSpec(String specType, String fieldName, String value){

        if (specType.equals("like")){
            return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.like(root.get(fieldName), "%"+value+"%");
        }else if (specType.equals("GTOE")){
                return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), Double.parseDouble(value));
        }else if (specType.equals("LTOE")){
            return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), Double.parseDouble(value));
        }else if (specType.equals("eq")){
            return (Specification<Good>) (root,criterialQuery,criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), Integer.parseInt(value));
        }else{
            return null;
        }
    }

}
