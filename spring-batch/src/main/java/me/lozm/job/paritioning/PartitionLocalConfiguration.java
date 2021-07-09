package me.lozm.job.paritioning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.domain.user.entity.User;
import me.lozm.global.common.CommonJobConfiguration;
import me.lozm.global.develop.UniqueRunIdIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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

    @Bean(name = JOB_NAME + "_TASK_POOL")
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadNamePrefix("partition-thread");
        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.initialize();
        return executor;
    }

    @Bean(name = JOB_NAME + "_PARTITION_HANDLER")
    public TaskExecutorPartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setStep(firstStep());
        partitionHandler.setTaskExecutor(executor());
        partitionHandler.setGridSize(poolSize);
        return partitionHandler;
    }


    @Bean(name = JOB_NAME + "_STEP1_MANAGER")
    public Step step1Manager() {
        return stepBuilderFactory.get(TEST_STEP1 + ".MANAGER")
                .partitioner(TEST_STEP1, partitioner(null, null))
                .step(firstStep())
                .partitionHandler(partitionHandler())
                .build();
    }

    @Bean(name = JOB_NAME + "_partitioner")
    @StepScope
    public RangePartitioner partitioner(
            @Value("#{jobParameters['startDate']}") String startDate,
            @Value("#{jobParameters['endDate']}") String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new RangePartitioner(startLocalDate, endLocalDate);
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
//        return stepBuilderFactory.get(BEAN_PREFIX + TEST_STEP1)
//                .tasklet((contribution, chunkContext) -> {
//                    innerStep(null);
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
        return stepBuilderFactory.get(BEAN_PREFIX + TEST_STEP1)
                .<User, User>chunk(CHUNK_AND_PAGE_SIZE)
                .reader(reader(null))
                .writer(writer(null))
                .build();
    }

    @Bean(name = BEAN_PREFIX + TEST_STEP1 +"_reader")
    @StepScope
    public JpaPagingItemReader<? extends User> reader(@Value("#{stepExecutionContext[requestDate]}") String requestDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("requestDate", requestDate);

        return new JpaPagingItemReaderBuilder<User>()
                .name(BEAN_PREFIX + TEST_STEP1 +"_reader_query")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_AND_PAGE_SIZE)
                .queryString("SELECT B FROM Board B ORDER BY B.id DESC")
                .build();
    }

    @Bean(name = BEAN_PREFIX + TEST_STEP1 +"_writer")
    @StepScope
    public ItemWriter<? super User> writer(@Value("#{stepExecutionContext[requestDate]}") String requestDate) {
        return items -> {
            log.info(requestDate);
        };
    }

//    @Bean(BEAN_PREFIX + TEST_STEP1 + "_innerStep")
//    @StepScope
//    public void innerStep(@Value("#{stepExecutionContext['requestDate']}") String requestDate) {
//        log.info(String.format("Step is launched. Request Param: %s", requestDate));
//    }

}
