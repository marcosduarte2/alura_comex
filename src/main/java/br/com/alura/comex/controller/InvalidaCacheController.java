package br.com.alura.comex.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
public class InvalidaCacheController {

    @GetMapping
    @CacheEvict(value = "listaDePedidos", allEntries = true)
    public void invalidateCacheListaDePedidos() { }

}

