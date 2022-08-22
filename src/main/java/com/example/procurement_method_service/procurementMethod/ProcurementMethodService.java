package com.example.procurement_method_service.procurementMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProcurementMethodService {


    private final ProcurementMethodRepository repository;


    @Autowired
    public ProcurementMethodService(ProcurementMethodRepository repository) {
        this.repository = repository;
    }


    public Mono<ProcurementMethod> save(ProcurementMethod procurementMethod) {

        Mono<ProcurementMethod> save = repository.save(procurementMethod);
        return save;
    }


    public Mono<ProcurementMethod> update(ProcurementMethod method) {

        Mono<ProcurementMethod> mono = repository.findById(method.getId());

        Mono<ProcurementMethod> save = repository.save(method);

        return save;

    }


    public Mono<Void> delete(Integer id) {

        return repository.deleteById(id);

    }


    public Mono<ProcurementMethod> get(Integer id) {

        return repository.findById(id);

    }

    public Flux<ProcurementMethod> getAll() {

        Flux<ProcurementMethod> list = repository.findAll();

        System.out.println("list = " + list);

        return list;

    }


}
