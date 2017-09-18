package servicioweb;

public class ValueObject {

    String valorTexto;
    int valorEntero;

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public int getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(int valorEntero) {
        this.valorEntero = valorEntero;
    }

    @Override
    public String toString() {
        return "ValueObject{" + "valorTexto=" + valorTexto + ", valorEntero=" + valorEntero + '}';
    }

}
