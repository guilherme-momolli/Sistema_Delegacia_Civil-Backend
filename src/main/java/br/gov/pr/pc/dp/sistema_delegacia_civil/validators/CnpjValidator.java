package br.gov.pr.pc.dp.sistema_delegacia_civil.validators;

public class CnpjValidator {

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) return false;

        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito  = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosPrimeiroDigito[i];
        }
        int digito1 = soma % 11;
        digito1 = (digito1 < 2) ? 0 : 11 - digito1;

        if (digito1 != (cnpj.charAt(12) - '0')) return false;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosSegundoDigito[i];
        }
        int digito2 = soma % 11;
        digito2 = (digito2 < 2) ? 0 : 11 - digito2;

        return digito2 == (cnpj.charAt(13) - '0');
    }

}
