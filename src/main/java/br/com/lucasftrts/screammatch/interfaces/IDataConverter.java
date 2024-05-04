package br.com.lucasftrts.screammatch.interfaces;

public interface IDataConverter
{
    <T> T getData(String json, Class<T> classe);
}
