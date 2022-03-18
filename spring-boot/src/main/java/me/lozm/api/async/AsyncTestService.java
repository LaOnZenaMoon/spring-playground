package me.lozm.api.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncTestService {

    private final SynchronousService synchronousService;
    private final AsynchronousService asynchronousService;
    private final CompletableFutureService completableFutureService;


    public String testSynchronousLogic(AsyncDto.Request requestDto) {
        String result1 = synchronousService.run(requestDto, 1);
        String result2 = synchronousService.run(requestDto, 2);
        String result3 = synchronousService.run(requestDto, 3);
        return String.format("%s, %s, %s", result1, result2, result3);
    }

    public String testAsynchronousLogic(AsyncDto.Request requestDto) {
        String result1 = asynchronousService.run(requestDto, 1);
        String result2 = asynchronousService.run(requestDto, 2);
        String result3 = asynchronousService.run(requestDto, 3);
        return String.format("%s, %s, %s", result1, result2, result3);
    }

    public String testSynchronousAndCompletableFutureLogic(AsyncDto.Request requestDto) {
        CompletableFuture<String> result1Future = completableFutureService.run(requestDto, 1);
        CompletableFuture<String> result2Future = completableFutureService.run(requestDto, 2);
        String result3 = synchronousService.run(requestDto, 3);
        try {
            return String.format("%s, %s, %s", result1Future.get(), result2Future.get(), result3);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return null;
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
