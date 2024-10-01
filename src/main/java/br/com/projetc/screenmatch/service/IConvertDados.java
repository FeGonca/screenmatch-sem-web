package br.com.projetc.screenmatch.service;

public interface IConvertDados {
    <T> T obterDados(String json, Class<T> classe);
}

// obterDados: é um metodo do tipo generico, deve receber o json e a classe que esse json deve ser usado.
