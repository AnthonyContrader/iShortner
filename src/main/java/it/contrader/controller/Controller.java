package it.contrader.controller;

import java.net.MalformedURLException;

/*
 * interfaccia Controller
 */
public interface Controller {
	public void doControl(Request request) throws MalformedURLException;
}
