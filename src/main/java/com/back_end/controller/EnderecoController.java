package com.back_end.controller;

import com.back_end.model.Endereco;
import com.back_end.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("endereco")
@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Boolean> salvaEndereco(@RequestBody Endereco endereco){
        return ResponseEntity.ok( enderecoService.salvarEndereco(endereco));
    }
}
