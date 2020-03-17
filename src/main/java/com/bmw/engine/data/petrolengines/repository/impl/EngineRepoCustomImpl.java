package com.bmw.engine.data.petrolengines.repository.impl;

import com.bmw.engine.data.petrolengines.model.EngineData;
import com.bmw.engine.data.petrolengines.repository.EngineRepoCustom;
import com.bmw.engine.data.petrolengines.repository.dbmodel.PetrolEngine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EngineRepoCustomImpl implements EngineRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<PetrolEngine> searchByData(EngineData data) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PetrolEngine> query = cb.createQuery(PetrolEngine.class);
        Root<PetrolEngine> engineRoot = query.from(PetrolEngine.class);
        List<Predicate> predicates = predicates(data, cb, engineRoot);
        if (predicates.size() == 0){
            return new ArrayList<>();
        }

        Predicate[] pInput = new Predicate[predicates.size()];
        pInput = predicates.toArray(pInput);

        query.where(cb.and(pInput));

        return entityManager.createQuery(query.select(engineRoot)).getResultList();
    }

    private List<Predicate> predicates(EngineData data, CriteriaBuilder cb, Root<PetrolEngine> root){
        List<Predicate> predicates = new ArrayList<>();
        addPredicate(predicates, root, cb, "layout", data.getLayout());
        addPredicate(predicates, root, cb, "engineCode", data.getEngineCode());
        addPredicate(predicates, root, cb, "engineYears", data.getEngineYears());
        addPredicate(predicates, root, cb, "displacement", data.getDisplacement());
        return predicates;
    }

    private void addPredicate(List<Predicate> predicates,Root<PetrolEngine> root, CriteriaBuilder cb,  String rootName, Object value){
        if (value == null){
            return;
        }
        if (value instanceof PetrolEngine.Layout){
            Predicate layout = cb.equal(root.get(rootName), ((PetrolEngine.Layout)value).name());
            predicates.add(layout);
            return;
        }
        if (((String)value).isEmpty()){
            return;
        }
        Predicate layout1 = cb.like(root.get(rootName), "%" + value);
        Predicate layout2 = cb.like(root.get(rootName), value + "%" );
        Predicate p = cb.or(layout1, layout2);
        predicates.add(p);
    }
}
