package com.stein.myenergi.database.datastore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.stein.myenergi.database.HistoryTable;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

public class HistoryDatastore implements HistoryTable {

    private final static String HISTORY_DATASTORE = "history";
    private final Datastore datastore;

    @Autowired
    public HistoryDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public HistoryEntity save(HistoryEntity entity) {
        FullEntity<IncompleteKey> fullEntity = HistoryEntityFactory.create(entity);
        datastore.put(fullEntity);
        return null;
    }

    @Override
    public Optional<Collection<HistoryEntity>> findByPeriod(String serial, Date start, Date end) {
        QueryResults<Entity> result = runSearchQuery(dateRangeFilterFilter(serial, start, end));
        return convertQueryResults(result);
    }

    @Override
    public Optional<HistoryEntity> findById(HistoryId historyId) {
        QueryResults<Entity> result = runSearchQuery(historyIdFilter(historyId));
        return convertQueryResult(result);
    }

    private QueryResults<Entity> runSearchQuery(Filter filter) {
        EntityQuery query = Query.newEntityQueryBuilder()
                .setKind(HISTORY_DATASTORE)
                .setFilter(filter)
                .setOrderBy(OrderBy.asc("date"))
                .build();

        return datastore.run(query);
    }

    private Filter historyIdFilter(HistoryId historyId) {
        return CompositeFilter.and(
                PropertyFilter.eq("date", Timestamp.of(historyId.getDate())),
                PropertyFilter.eq("serial", historyId.getSerial()));
    }

    private Filter dateRangeFilterFilter(String serial, Date startDate, Date endDate) {
        return CompositeFilter.and(
                PropertyFilter.eq("serial", serial),
                PropertyFilter.gt("date", Timestamp.of(startDate)),
                PropertyFilter.lt("date", Timestamp.of(endDate)));
    }

    private Optional<HistoryEntity> convertQueryResult(QueryResults<Entity> q) {
        if (!q.hasNext()) {
            return Optional.empty();
        }

        Entity e = q.next();
        HistoryEntity historyEntity = HistoryEntityFactory.create(e);
        return Optional.of(historyEntity);
    }

    private Optional<Collection<HistoryEntity>> convertQueryResults(QueryResults<Entity> q) {
        List<HistoryEntity> historyEntities = new ArrayList();
        while (q.hasNext()) {
            Entity e = q.next();
            HistoryEntity historyEntity = HistoryEntityFactory.create(e);
            historyEntities.add(historyEntity);
        }
        return Optional.of(historyEntities);
    }



}
