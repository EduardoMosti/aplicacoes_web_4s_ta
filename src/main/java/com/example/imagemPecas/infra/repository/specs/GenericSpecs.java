package com.example.imagemPecas.infra.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecs {
    private GenericSpecs() {
    }
    public static Specification conjunction(Specification... specs) {
        return ((root, query, cb) -> {
            var predicate = cb.conjunction();

            for (Specification spec : specs) {
                if (spec != null) {
                    predicate = cb.and(predicate, spec.toPredicate(root, query, cb));
                }
            }

            return predicate;
        });
    }
}
