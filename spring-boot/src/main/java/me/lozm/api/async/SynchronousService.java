package me.lozm.api.async;

import lombok.extern.slf4j.Slf4j;
import me.lozm.global.utils.SleepUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SynchronousService {

    public String run(AsyncDto.Request requestDto) {
        int randomInteger = SleepUtils.getRandomInteger(3);
        SleepUtils.sleepThread(randomInteger);
        return requestDto.getName() + randomInteger;
    }

    public String run(AsyncDto.Request requestDto, int seconds) {
        SleepUtils.sleepThread(seconds);
        return requestDto.getName() + seconds;
    }

}
