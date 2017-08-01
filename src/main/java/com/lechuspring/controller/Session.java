package com.lechuspring.controller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class Session {

	private int TpCarta = 5;

	public int getTpCarta() {
		return TpCarta;
	}

	public void setTpCarta(int tpCarta) {
		TpCarta = tpCarta;
	}
	
	
}
