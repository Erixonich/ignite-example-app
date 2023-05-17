package com.altuera.ignite;

import org.apache.ignite.IgniteException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Посчитать средний возраст все персон в кеше Person
 */
public class PersonAverageAgeTask extends ComputeTaskAdapter<?,?> {
    @Override
    public @NotNull Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> subgrid, @Nullable Object arg) throws IgniteException {
        return null;
    }

    @Nullable
    @Override
    public ? reduce(List<ComputeJobResult> results) throws IgniteException {
        return null;
    }


    public static class PersonAverageAgeJob extends ComputeJobAdapter {
        @Override
        public Object execute() throws IgniteException {
            return null;
        }
    }
}
