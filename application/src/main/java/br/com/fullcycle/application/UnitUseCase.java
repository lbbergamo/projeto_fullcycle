package br.com.fullcycle.application;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN anIN);
}
