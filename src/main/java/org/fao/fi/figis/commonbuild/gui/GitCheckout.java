package org.fao.fi.figis.commonbuild.gui;

import org.apache.tools.ant.BuildLogger;

public class GitCheckout {

	public void setExecute(boolean b) {
		System.out.println("setExecute");
		
	}

	public void setGitDir(String git_dir) {
		System.out.println("setGitDir");
	}

	public void setGitSrcDir(String string) {
		System.out.println("setGitSrcDir");
	}

	public void executeTarget(String allTarget) {
		System.out.println("executeTarget");
	}

	public void addBuildListener(BuildLogger logger) {
		System.out.println("addBuildListener");
	}

	public void init() {
		System.out.println("init");
		
	}

}
