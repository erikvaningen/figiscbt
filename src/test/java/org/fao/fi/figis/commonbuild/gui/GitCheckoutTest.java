package org.fao.fi.figis.commonbuild.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class GitCheckoutTest {

	GitCheckout g = new GitCheckout();
	String gitDir = "figisGit";
	String srcGitDir = "src/paart";
	boolean execute = true;

	@Test
	public void testInit() {
		g.setExecute(execute);
		g.setGitDir(gitDir);
		g.setGitSrcDir(srcGitDir);
		g.init();

		File srcGitDirFile = new File(srcGitDir);
		System.out.println(srcGitDirFile.getAbsolutePath());
		assertTrue(srcGitDirFile.exists());

	}

}
