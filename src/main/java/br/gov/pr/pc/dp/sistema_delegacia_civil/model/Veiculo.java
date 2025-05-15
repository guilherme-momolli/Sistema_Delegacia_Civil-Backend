//package br.gov.pr.pc.dp.sistema_delegacia_civil.model;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Carroceria;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Combustivel;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.sql.Timestamp;
//
//@Data
//@Entity
//@Table(name = "veiculo")
//public class Veiculo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String marca;
//
//    private String modelo;
//
//    private String cor;
//
//    private Double precoTotal;
//
//    @Enumerated(EnumType.STRING)
//    private Carroceria carroceria;
//
//    @Enumerated(EnumType.STRING)
//    private Combustivel combustivel;
//
//    private String placa;
//
//    private String renavam;
//
//    private String codigoSegurancaCla;
//
//    private String cat;
//
//    private Timestamp anoFabricacao;
//
//    private Timestamp anoModelo;
//
//    private Double pesoBurtoTotal;
//
//    private String observacao;
//
//    private int lotacao;
//
//    private Double capacidade;
//
//    private String cmt;
//
//    private String eixos;
//
//    private int potencia;
//
//}
