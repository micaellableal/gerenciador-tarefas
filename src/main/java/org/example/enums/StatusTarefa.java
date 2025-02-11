package org.example.enums;

public enum StatusTarefa {
    PENDETE(1,"Pendente"),
    EM_ANDAMENTO(2,"Em andamento"),
    CONCLUIDA(3,"Concluída");

    private final int codigo;
    private final String descricao;

    StatusTarefa(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusTarefa fromCodigo(int codigo){
        for(StatusTarefa status : StatusTarefa.values()){
            if(status.getCodigo()==codigo){
                return status;
            }

        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}
