package com.example.procurement_method_service.procurementMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/procurement_method")
public class ProcurementMethodController {


    private final ProcurementMethodService service;

    @Autowired
    public ProcurementMethodController(ProcurementMethodService service) {
        this.service = service;
    }


    @PostMapping
    public Mono<ProcurementMethod> save(@RequestBody ProcurementMethod procurementMethod) {

        return service.save(procurementMethod);
    }


    @PutMapping
    public Mono<ProcurementMethod> update(@RequestBody ProcurementMethod method) {

        return service.update(method);

    }


    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {

        return service.delete(id);

    }


    @GetMapping("/{id}")
    public Mono<ProcurementMethod> get(@PathVariable Integer id) {

        System.out.println("id = " + id);
        return service.get(id);

    }

    @GetMapping("/list")
    public Flux<ProcurementMethod> getAll() {

        return service.getAll();
    }


    @GetMapping
    public Flux<ProcurementMethod> getAllSort(@RequestParam(required = false, defaultValue = "id") String fieldName) {

        return service.getAllSort(fieldName);
    }

}
