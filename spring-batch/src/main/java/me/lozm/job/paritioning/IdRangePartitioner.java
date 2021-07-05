package me.lozm.job.paritioning;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class IdRangePartitioner implements Partitioner {

    private final LocalDate startDate;
    private final LocalDate endDate;


    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<>();

        long partitionNumber = 0;
        LocalDate targetDate = startDate;
        while (targetDate.isBefore(endDate)) {
            ExecutionContext value = new ExecutionContext();
            value.put("requestDate", targetDate);
            result.put("partition" + partitionNumber, value);

            targetDate = targetDate.plusDays(gridSize);
            partitionNumber++;
        }

        return result;
    }
}
