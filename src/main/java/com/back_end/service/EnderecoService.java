package com.back_end.service;

import com.back_end.model.Endereco;
import com.back_end.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public boolean salvarEndereco(Endereco endereco) {
         enderecoRepository.save(endereco);
         return true;
    }
}
