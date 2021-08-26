package me.lozm.api.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OuterService {

    private final NestedInnerService nestedInnerService;


    public void test1() {
        nestedInnerService.log("");
//        nestedInnerService.throwRuntimeException(RuntimeException.class);
    }

}
