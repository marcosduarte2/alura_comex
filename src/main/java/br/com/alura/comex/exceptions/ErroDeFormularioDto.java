package br.com.alura.comex.exceptions;

public class ErroDeFormularioDto extends DefaultErrorDto {

    private String campo = "";

    public ErroDeFormularioDto(){super("");}
    public ErroDeFormularioDto(String campo, String erro) {
        super(erro);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}