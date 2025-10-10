package br.gov.pr.pc.dp.sistema_delegacia_civil.validators.pessoa;

public class CpfValidator {

    public static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int num = cpf.charAt(i) - '0';
                soma1 += num * (10 - i);
                soma2 += num * (11 - i);
            }

            int digito1 = (soma1 * 10) % 11;
            digito1 = (digito1 == 10) ? 0 : digito1;

            soma2 += digito1 * 2;
            int digito2 = (soma2 * 10) % 11;
            digito2 = (digito2 == 10) ? 0 : digito2;

            return (digito1 == cpf.charAt(9) - '0') && (digito2 == cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }
}
