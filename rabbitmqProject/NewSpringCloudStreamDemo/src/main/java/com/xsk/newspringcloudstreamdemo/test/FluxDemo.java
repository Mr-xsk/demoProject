package com.xsk.newspringcloudstreamdemo.test;

import reactor.core.publisher.Flux;

public class FluxDemo {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a","b","c");
        Flux<String> flux2 = Flux.just("1","2","3");
//        flux1.subscribe(System.out::println);
//        Flux res = Flux.merge(flux1, flux2);
        Flux res = Flux.combineLatest(flux1,flux2,(v1,v2)-> v1+":"+v2);

        res.subscribe(System.out::println);
    }
}
