package org.fao.fi.figis.commonbuild.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FigisCheckoutTest {

	FigisCheckout g = new FigisCheckout();
	String tag = "0_0_3";
	String gitDir = "../localRepository/hond/" + tag;
	String moduleName = "figistry";
	boolean execute = true;
	FileAndUILogger log = new FileAndUILogger();

	@Test
	public void testInit() {
		g.setFileAndUILogger(log);
		g.setModuleName(moduleName).setExecute(execute).setGitDir(gitDir).setTag(tag);
		g.init();
		File gitDirFile = new File(gitDir);
		System.out.println(gitDirFile.getAbsolutePath());
		assertTrue(gitDirFile.exists());

	}

}
