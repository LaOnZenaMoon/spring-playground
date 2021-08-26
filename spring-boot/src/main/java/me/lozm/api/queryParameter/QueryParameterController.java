package me.lozm.api.queryParameter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RequestMapping("query-parameter")
@RestController
public class QueryParameterController {

    @GetMapping("individual")
    public ResponseEntity getQueryParametersIndividually(@RequestParam("name") String name,
                                                         @RequestParam("age") String age,
                                                         @RequestParam("interest") String interest) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("interest", interest);

        return ResponseEntity.ok(map);
    }

    @GetMapping("model")
    public ResponseEntity getQueryParametersUsingModel(@Valid QueryParameterModel queryParameterModel) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", queryParameterModel.getName());
        map.put("age", queryParameterModel.getAge());
        map.put("interest", queryParameterModel.getInterest());

        return ResponseEntity.ok(map);
    }

}
