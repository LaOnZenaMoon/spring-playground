package me.lozm.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.global.common.CommonJobConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestJobConfiguration extends CommonJobConfiguration {

    public static final String JOB_NAME = "TEST";
    public static final String BEAN_PREFIX = JOB_NAME + "_";



    @Bean(JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(test1())
                .next(test2())
                .build();
    }

    @Bean(BEAN_PREFIX + "TEST1_TASKLET")
    @JobScope
    public Step test1() {
        return stepBuilderFactory.get(BEAN_PREFIX + "TEST1_TASKLET")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Test 1 Tasklet");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean(BEAN_PREFIX + "TEST2_TASKLET")
    @JobScope
    public Step test2() {
        return stepBuilderFactory.get(BEAN_PREFIX + "TEST2_TASKLET")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Test 2 Tasklet");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
