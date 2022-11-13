package com.example.Excel.service;

import java.util.List;

public interface BaseService<Entity, ID> {
    Entity save(Entity entity);

    Entity update(Entity entity) throws Exception;

    void deleteByIds(ID... ids);

    List<Entity> getDataByIds(ID... ids);

    List<Entity> getData();
}

