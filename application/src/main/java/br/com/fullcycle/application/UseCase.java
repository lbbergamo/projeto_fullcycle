package br.com.fullcycle.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIN);
}
