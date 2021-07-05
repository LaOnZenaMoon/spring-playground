package me.lozm.job.paritioning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.global.common.CommonJobConfiguration;
import me.lozm.global.develop.UniqueRunIdIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PartitionLocalConfiguration extends CommonJobConfiguration {

    public static final String JOB_NAME = "PARTITION_LOCAL";
    public static final String BEAN_PREFIX = JOB_NAME + "_";
    public static final String TEST_STEP1 = "TEST_STEP1";


    private int poolSize;

    @Value("${poolSize:1}")
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Bean(name = JOB_NAME+"_taskPool")
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadNamePrefix("partition-thread");
        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.initialize();
        return executor;
    }

    @Bean(name = JOB_NAME+"_partitionHandler")
    public TaskExecutorPartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setStep(firstStep());
        partitionHandler.setTaskExecutor(executor());
        partitionHandler.setGridSize(poolSize);
        return partitionHandler;
    }


    @Bean(name = JOB_NAME +"_step1Manager")
    public Step step1Manager() {
        return stepBuilderFactory.get("step1.manager")
                .partitioner(TEST_STEP1, partitioner(null, null))
                .step(firstStep())
                .partitionHandler(partitionHandler())
                .build();
    }

    @Bean(name = JOB_NAME +"_partitioner")
    @StepScope
    public IdRangePartitioner partitioner(
            @Value("#{jobParameters['startDate']}") String startDate,
            @Value("#{jobParameters['endDate']}") String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new IdRangePartitioner(startLocalDate, endLocalDate);
    }

    @Bean(name = JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new UniqueRunIdIncrementer()) // 개발 재실행 용도
                .start(step1Manager())
                .preventRestart()
                .build();
    }

    @Bean(BEAN_PREFIX + TEST_STEP1)
    public Step firstStep() {
        return stepBuilderFactory.get(BEAN_PREFIX + TEST_STEP1)
                .tasklet((contribution, chunkContext) -> {
                    log.info(TEST_STEP1 + " is launched");

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
