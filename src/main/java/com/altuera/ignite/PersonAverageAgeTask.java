package com.altuera.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.cache.Cache;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Посчитать средний возраст все персон в кеше Person
 */
//select avg(age) from persons
    //where FirstName = 'Maria'
public class PersonAverageAgeTask extends ComputeTaskAdapter<Void,Double> {
    @Override
    public @NotNull Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> subgrid, @Nullable Void arg) throws IgniteException {
        Map<ComputeJob, ClusterNode> res = new HashMap<>();

        for (ClusterNode clusterNode : subgrid) {
            res.put(new PersonAverageAgeJob(), clusterNode);
        }

        return res;
    }

    @Nullable
    @Override
    public Double reduce(List<ComputeJobResult> results) throws IgniteException {
        long sumYears = 0;
        long personCount = 0;
        for (ComputeJobResult res : results) {
            AgeInfo ageInfo = res.<AgeInfo>getData();

            sumYears += ageInfo.sumYears;
            personCount += ageInfo.personCount;;
        }

        return 1.0 * sumYears / personCount;
    }

    private static class AgeInfo {
        long sumYears = 0;
        long personCount = 0;
    }

    public static class PersonAverageAgeJob extends ComputeJobAdapter {
        @IgniteInstanceResource
        private Ignite ign;

        @Override
        public Object execute() throws IgniteException {
            AgeInfo ageInfo = new AgeInfo();

            LocalDate now = LocalDate.now();
            for (Cache.Entry<UUID, Person> e : ign.<UUID, Person>cache("Person").localEntries(CachePeekMode.PRIMARY)) {
                ageInfo.personCount++;
                ageInfo.sumYears += ChronoUnit.YEARS.between(e.getValue().getBirthday(), now);
            }

            return ageInfo;
        }
    }
}
