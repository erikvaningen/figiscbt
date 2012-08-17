package org.fao.fi.figis.commonbuild.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FigisCheckoutTest {

	FigisCheckout g = new FigisCheckout();
	String gitDir = "../localRepository/hond";
	String srcGitDir = gitDir + "src";
	String tag = "0.0.1";
	boolean execute = true;

	@Test
	public void testInit() {
		g.setExecute(execute).setGitDir(gitDir).setGitSrcDir(srcGitDir).setTag(tag);
		g.init();
		File gitDirFile = new File(gitDir);
		System.out.println(gitDirFile.getAbsolutePath());
		assertTrue(gitDirFile.exists());

	}

}
