package br.gov.pr.pc.dp.sistema_delegacia_civil.validators.documentos;

public class CpfValidator {
    public static boolean isValidCPF(String cpf) {
        if (cpf == null) return false;

        // Remove qualquer caractere que não seja dígito
        cpf = cpf.replaceAll("[^\\d]", "");

        // CpfValidator precisa ter 11 dígitos
        if (cpf.length() != 11) return false;

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) return false;

        // Cálculo do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) digito1 = 0;

        // Verifica o primeiro dígito
        if (digito1 != (cpf.charAt(9) - '0')) return false;

        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) digito2 = 0;

        // Verifica o segundo dígito
        return digito2 == (cpf.charAt(10) - '0');
    }

    public static void main(String[] args) {
        String cpfExemplo = "123.456.789-09";

        if (isValidCPF(cpfExemplo)) {
            System.out.println("CpfValidator válido.");
        } else {
            System.out.println("CpfValidator inválido.");
        }
    }

}
