package br.com.alura.comex.exceptions;

public class DefaultErrorDto {
    protected String erro = "";

    public DefaultErrorDto() {}
    public DefaultErrorDto(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}