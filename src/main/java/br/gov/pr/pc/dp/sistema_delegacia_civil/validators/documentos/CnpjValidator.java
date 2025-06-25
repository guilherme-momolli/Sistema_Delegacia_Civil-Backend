package br.gov.pr.pc.dp.sistema_delegacia_civil.validators.documentos;

public class CnpjValidator {

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null) return false;

        // Remove qualquer caractere não numérico
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) return false;

        // Verifica se todos os dígitos são iguais (ex: 11.111.111/1111-11)
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito  = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosPrimeiroDigito[i];
        }
        int digito1 = soma % 11;
        digito1 = (digito1 < 2) ? 0 : 11 - digito1;

        // Verifica o primeiro dígito
        if (digito1 != (cnpj.charAt(12) - '0')) return false;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosSegundoDigito[i];
        }
        int digito2 = soma % 11;
        digito2 = (digito2 < 2) ? 0 : 11 - digito2;

        // Verifica o segundo dígito
        return digito2 == (cnpj.charAt(13) - '0');
    }

    public static void main(String[] args) {
        String cnpjExemplo = "45.543.915/0001-40";

        if (isValidCNPJ(cnpjExemplo)) {
            System.out.println("CNPJ válido.");
        } else {
            System.out.println("CNPJ inválido.");
        }
    }
}
