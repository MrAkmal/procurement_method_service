package com.example.procurement_method_service.procurementMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Supplier;

@Service
public class ProcurementMethodService {


    private final ProcurementMethodRepository repository;


    @Autowired
    public ProcurementMethodService(ProcurementMethodRepository repository) {
        this.repository = repository;
    }


    public Mono<ProcurementMethod> save(ProcurementMethod procurementMethod) {

        return repository.findProcurementMethodByName(procurementMethod.getName())
                .flatMap(Mono::just)
                .switchIfEmpty(repository.save(procurementMethod));
    }


    public Mono<ProcurementMethod> update(ProcurementMethod method) {

//        return repository.findById(method.getId())
//                .flatMap(procurementMethod -> repository.findProcurementMethodByIdAndName(method.getName(), method.getId())
//                        .flatMap(p -> repository.save(method))
//                        .switchIfEmpty(
//                                repository.findProcurementMethodByIdAndNameNot(method.getName(), method.getId())
//                                        .flatMap(Mono::just)
//                                        .switchIfEmpty(repository.save(method))
//                        ))
//                .switchIfEmpty(Mono.just(method));

        return repository.findById(method.getId())
                .flatMap(procurementMethod -> {
                    if (Objects.equals(procurementMethod.getName(), method.getName())) {
                        return repository.save(method);
                    } else {
                        return repository.findProcurementMethodByIdAndNameNot(method.getName(), method.getId())
                                .flatMap(Mono::just)
                                .switchIfEmpty(repository.save(method));
                    }
                })
                .switchIfEmpty(Mono.just(method));


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


    public Flux<ProcurementMethod> getAllSort(String fieldName) {

        Flux<ProcurementMethod> list = repository.findAll(Sort.by(Sort.Direction.ASC, fieldName));

        System.out.println("list = " + list);

        return list;

    }


}
