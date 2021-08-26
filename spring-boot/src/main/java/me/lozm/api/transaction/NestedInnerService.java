package me.lozm.api.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.NESTED)
@RequiredArgsConstructor
public class NestedInnerService {



    public void log(String s) {

    }

    public void throwRuntimeException() {
        throw new RuntimeException();
    }

}
