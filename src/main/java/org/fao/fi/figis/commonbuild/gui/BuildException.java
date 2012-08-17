package org.fao.fi.figis.commonbuild.gui;

public class BuildException extends RuntimeException {

	public BuildException(Exception e) {
		super(e);
	}

	public BuildException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051536769426717890L;

}
