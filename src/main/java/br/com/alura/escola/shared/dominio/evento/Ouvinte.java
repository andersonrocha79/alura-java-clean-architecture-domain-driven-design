package br.com.alura.escola.shared.dominio.evento;

public abstract class Ouvinte
{

    public void processa(Evento evento)
    {
        if (this.deveProcessar(evento))
        {
            this.reageAo(evento);
        }
    }

    // método abstrato
    // será definido nas classes filhas
    protected abstract boolean deveProcessar(Evento evento);

    // método abstrato
    // será definido nas classes filhas
    protected abstract void reageAo(Evento evento);

}
