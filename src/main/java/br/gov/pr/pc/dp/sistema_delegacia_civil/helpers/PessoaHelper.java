package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.pessoa.CpfValidator;

import java.time.LocalDate;
import java.time.Period;

public class PessoaHelper {

        public static int calcularIdade(LocalDate dataNascimento) {
            if (dataNascimento == null) {
                throw new IllegalArgumentException("Data de nascimento não pode ser nula.");
            }
            return Period.between(dataNascimento, LocalDate.now()).getYears();
        }

        public static String formatarNome(String nome) {
            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("Nome inválido.");
            }
            return nome.trim().substring(0, 1).toUpperCase() + nome.trim().substring(1).toLowerCase();
        }

        public static String formatarNomeCompleto(String nome) {
            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("Nome inválido.");
            }
            String[] partes = nome.trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String parte : partes) {
                sb.append(parte.substring(0, 1).toUpperCase())
                        .append(parte.substring(1).toLowerCase())
                        .append(" ");
            }
            return sb.toString().trim();
        }

        public static boolean isMaiorDeIdade(Pessoa pessoa) {
            if (pessoa.getDataNascimento() == null) {
                throw new IllegalArgumentException("Data de nascimento não informada.");
            }
            return calcularIdade(pessoa.getDataNascimento()) >= 18;
        }

        public static boolean validarCpf(String cpf) {
            return CpfValidator.isValidCpf(cpf);
        }

        public static void validarPessoa(Pessoa pessoa) {
            if (pessoa == null) {
                throw new IllegalArgumentException("Pessoa não pode ser nula.");
            }

            if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
                throw new IllegalArgumentException("Nome é obrigatório.");
            }

            if (pessoa.getCpf() == null || !validarCpf(pessoa.getCpf())) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            if (pessoa.getEmail() != null && !pessoa.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("Email inválido.");
            }
        }
    }
