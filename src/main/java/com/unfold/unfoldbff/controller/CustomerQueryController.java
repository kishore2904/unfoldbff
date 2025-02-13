package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.CustomerQueryDto;
import com.unfold.unfoldbff.service.impl.CustomerQueryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class CustomerQueryController {

    private final CustomerQueryServiceImpl queryService;

    public CustomerQueryController(CustomerQueryServiceImpl queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/customer_query/submit")
    public ResponseEntity<Void> submitQuery(@RequestBody CustomerQueryDto dto) {
        queryService.submitQuery(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/customer_query/all")
    public ResponseEntity<List<CustomerQueryDto>> getAllQueries() {
        return ResponseEntity.ok(queryService.getAllQueries());
    }

    @GetMapping("/customer_query/{userId}")
    public ResponseEntity<List<CustomerQueryDto>> getQueriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(queryService.getQueriesByUserId(userId));
    }

    @DeleteMapping("/customer_query/")
    public ResponseEntity<String> deleteQuery(@PathVariable Long id) {
        boolean deleted = queryService.deleteQuery(id);
        return deleted ? ResponseEntity.ok("Query deleted successfully.") :
                ResponseEntity.badRequest().body("Query not found.");
    }
}
