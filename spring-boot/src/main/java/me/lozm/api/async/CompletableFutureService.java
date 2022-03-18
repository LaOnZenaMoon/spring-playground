package me.lozm.api.async;

import lombok.extern.slf4j.Slf4j;
import me.lozm.global.utils.SleepUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CompletableFutureService {

    public CompletableFuture<String> run(AsyncDto.Request requestDto) {
        int randomInteger = SleepUtils.getRandomInteger(3);
        CompletableFuture<String> objectCompletableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                SleepUtils.sleepThread(randomInteger);
                objectCompletableFuture.complete(requestDto.getName() + randomInteger);
            } catch (Exception e) {
                log.error(e.getMessage());
                objectCompletableFuture.completeExceptionally(e);
            }
        }).start();
        return objectCompletableFuture;
    }

    public CompletableFuture<String> run(AsyncDto.Request requestDto, int seconds) {
        CompletableFuture<String> objectCompletableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                SleepUtils.sleepThread(seconds);
                objectCompletableFuture.complete(requestDto.getName() + seconds);
            } catch (Exception e) {
                log.error(e.getMessage());
                objectCompletableFuture.completeExceptionally(e);
            }
        }).start();
        return objectCompletableFuture;
    }

}
