package com.stein.myenergi.database.datastore;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Value;
import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

public class HistoryEntityFactory {

    private static final String PROPERTY_GENERATED = "generated";
    private static final String PROPERTY_EXPORTED = "exported";
    private static final String PROPERTY_CONSUMED = "consumed";
    private static final String PROPERTY_CHARGED = "charged";
    private static final String PROPERTY_SERIAL = "serial";
    private static final String PROPERTY_DATE = "date";
    private static final String PROPERTY_HISTORY = "history";
    private static Entity historyDay;

    public static FullEntity<IncompleteKey> create(HistoryEntity entity) {
        return Entity.newBuilder()
                .set(PROPERTY_DATE, Timestamp.of(entity.getId().getDate()))
                .set(PROPERTY_SERIAL, entity.getId().getSerial())
                .set(PROPERTY_CHARGED, entity.getCharged())
                .set(PROPERTY_CONSUMED, entity.getConsumed())
                .set(PROPERTY_EXPORTED, entity.getExported())
                .set(PROPERTY_GENERATED, entity.getGenerated())
                .set(PROPERTY_HISTORY, new ArrayList())
                .build();
    }

    public static HistoryEntity create(Entity e) {
        return HistoryEntity.builder()
                .id(mapToHistoryId(e))
                .charged((int) e.getLong(PROPERTY_CHARGED))
                .consumed((int) e.getLong(PROPERTY_CONSUMED))
                .exported((int) e.getLong(PROPERTY_EXPORTED))
                .generated((int) e.getLong(PROPERTY_GENERATED))
                .history(mapToHistoryDays(e))
                .build();
    }

    private static HistoryId mapToHistoryId(Entity e) {
        return new HistoryId(
                e.getTimestamp(PROPERTY_DATE).toDate(),
                e.getString(PROPERTY_SERIAL));
    }

    private static HistoryDay[] mapToHistoryDays(Entity e){
        List<HistoryDay> history = new ArrayList<>();
        List<Value<Entity>> list = e.getList(PROPERTY_HISTORY);
        for(Value<Entity> value : list) {
            historyDay = value.get();
            HistoryDay d = HistoryDay.builder()
            .minute((int)historyDay.getLong("min"))
            .dayOfMonth((int)historyDay.getLong("dom"))
            .dayOfWeek(historyDay.getString("dow"))
            .month((int)historyDay.getLong("mon"))
            .year((int)historyDay.getLong("yr"))
            .importedJoules((int)historyDay.getLong("imp"))
            .exportedJoules((int)historyDay.getLong("exp"))
            .frequency((int)historyDay.getLong("frq"))
            .generated((int)historyDay.getLong("gen"))
            .generatedJoules((int)historyDay.getLong("gep"))
            .importedJoulesCt1((int)historyDay.getLong("nect1"))
            .importedJoulesCt2((int)historyDay.getLong("nect2"))
            .phase1JoulesForCharging((int)historyDay.getLong("h1d"))
            .phase2JoulesForCharging((int)historyDay.getLong("h2d"))
            .phase3JoulesForCharging((int)historyDay.getLong("h3d"))
            .voltage((int)historyDay.getLong("v1"))
            .build();
            history.add(d);
        }
        return history.toArray(HistoryDay[]::new);
    }
}