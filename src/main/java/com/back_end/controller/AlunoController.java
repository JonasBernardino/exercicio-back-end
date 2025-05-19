package com.back_end.controller;

import com.back_end.model.Aluno;
import com.back_end.model.Endereco;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private List<Aluno> alunos = new ArrayList<>(List.of(
            new Aluno(3L, "Pedro", "pedro@email.com", 5.0),
            new Aluno(1L, "João", "joao@email.com", 8.5),
            new Aluno(2L, "Maria", "maria@email.com", 10.0)
    ));

    private List<Endereco> enderecos = new ArrayList<>();

    // 1. Boas-vindas
    @GetMapping("/bem-vindo/{nome}")
    public String exibirMensagemBoasVindas(@PathVariable String nome) {
        return "Bem vindo " + nome + "!";
    }

    // 2. CRUD de Alunos
    @PostMapping
    public Boolean cadastrarAluno(@RequestBody Aluno aluno) {
        alunos.add(aluno);
        return true;
    }

    @PutMapping("/{id}/nota")
    public String atualizarNotaAluno(@PathVariable Long id, @RequestBody Double nota) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                aluno.setNota(nota);
                return "Nota atualizada com sucesso!";
            }
        }
        return "Aluno não encontrado.";
    }

    @DeleteMapping("/{id}")
    public String removerAlunoPorId(@PathVariable Long id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                alunos.remove(aluno);
                return "Aluno removido com sucesso!";
            }
        }
        return "Aluno não encontrado.";
    }

    // 3. Consultas e filtros
    @GetMapping("/email/{email}")
    public Aluno buscarAlunoPorEmail(@PathVariable String email) {
        email = email.toLowerCase();
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().toLowerCase().equals(email)) {
                return aluno;
            }
        }
        return null;
    }

    @GetMapping("/aprovados")
    public List<Aluno> listarAlunosAprovados() {
        List<Aluno> aprovados = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getNota() >= 7.0) {
                aprovados.add(aluno);
            }
        }
        return aprovados;
    }

    @GetMapping("/ordenados")
    public List<Aluno> listarAlunosOrdenadosPorNome() {
        return alunos.stream()
                .sorted(Comparator.comparing(Aluno::getNome)).toList();
    }

    @GetMapping("/media")
    public double calcularMediaNotas() {
        double soma = 0;
        for (Aluno aluno : alunos) {
            soma += aluno.getNota();
        }
        return soma / alunos.size();
    }

    @GetMapping("/quantidade")
    public int contarTotalDeAlunos() {
        return alunos.size();
    }

    @GetMapping("/relatorio")
    public Map<String, Object> gerarRelatorioNotas() {
        Map<String, Object> relatorio = new HashMap<>();
        long aprovados = alunos.stream().filter(a -> a.getNota() >= 7).count();
        long reprovados = alunos.size() - aprovados;

        relatorio.put("total", alunos.size());
        relatorio.put("aprovados", aprovados);
        relatorio.put("reprovados", reprovados);
        relatorio.put("maiorNota", alunos.stream().mapToDouble(Aluno::getNota).max().orElse(0));
        relatorio.put("menorNota", alunos.stream().mapToDouble(Aluno::getNota).min().orElse(0));
        return relatorio;
    }

    // 4. Endereços
    @PostMapping("/enderecos")
    public Endereco cadastrarEndereco(@RequestBody Endereco endereco) {
        enderecos.add(endereco);
        return endereco;
    }

    @PutMapping("/{id}/endereco")
    public Aluno atualizarEnderecoDoAluno(@PathVariable Long id, @RequestBody Endereco novoEndereco) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                aluno.setEndereco(novoEndereco);
                return aluno;
            }
        }
        return null;
    }

    @GetMapping("/{id}/endereco")
    public Endereco consultarEnderecoDoAluno(@PathVariable Long id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                return aluno.getEndereco();
            }
        }
        return null;
    }

    @GetMapping("/cidade/{nomeCidade}")
    public List<Aluno> buscarAlunosPorCidade(@PathVariable String nomeCidade) {
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getEndereco().getCidade().equals(nomeCidade)) {
                resultado.add(aluno);
            }
        }
        return resultado;
    }

    @GetMapping("/cep/{cep}")
    public List<Aluno> buscarAlunosPorCep(@PathVariable String cep) {
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno aluno : alunos) {
            Endereco endereco = aluno.getEndereco();
            if (endereco != null && endereco.getCep().equals(cep)) {
                resultado.add(aluno);
            }
        }
        return resultado;
    }

    @PutMapping("/{idAluno}/endereco/{idEndereco}")
    public String associarEnderecoExistenteAoAluno(@PathVariable Long idAluno, @PathVariable Long idEndereco) {
        Aluno alunoEncontrado = null;
        Endereco enderecoEncontrado = null;

        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(idAluno)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        for (Endereco endereco : enderecos) {
            if (endereco.getId().equals(idEndereco)) {
                enderecoEncontrado = endereco;
                break;
            }
        }

        if (alunoEncontrado == null) {
            return "Aluno não encontrado.";
        }

        if (enderecoEncontrado == null) {
            return "Endereço não encontrado.";
        }

        alunoEncontrado.setEndereco(enderecoEncontrado);
        return "Endereço associado ao aluno com sucesso!";
    }

}
