package org.fao.fi.figis.commonbuild.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FigisCheckoutTest {

	FigisCheckout g = new FigisCheckout();
	String gitDir = "../localRepository/hond";
	String srcGitDir = gitDir + "src";
	String tag = "0_0_1";
	String moduleName = "figistry";
	boolean execute = true;
	FileAndUILogger log = new FileAndUILogger();

	@Test
	public void testInit() {
		g.setFileAndUILogger(log);
		g.setModuleName(moduleName).setExecute(execute).setGitDir(gitDir).setGitSrcDir(srcGitDir).setTag(tag);
		g.init();
		File gitDirFile = new File(gitDir);
		System.out.println(gitDirFile.getAbsolutePath());
		assertTrue(gitDirFile.exists());

	}

}
