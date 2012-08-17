package org.fao.fi.figis.commonbuild.gui;

import java.io.PrintStream;

import javax.swing.JTextArea;

public class FileAndUILogger {

	private PrintStream printStream;
	private JTextArea outputTextArea;

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setOutputTextArea(JTextArea outputTextArea) {
		this.outputTextArea = outputTextArea;
	}

	public void sendMessage(String message) {
		message = message + '\n';
		if (printStream != null) {
			printStream.print(message);
		}
		outputTextArea.append(message);
	}

}
