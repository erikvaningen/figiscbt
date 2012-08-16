package org.fao.fi.figis.commonbuild.gui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class GitCheckoutTest {
	
	GitCheckout g = new GitCheckout(); 
	String gitDir = "paart"; 
	String srcGitDir = "scr/paart"; 
	
	
	
	@Test
	public void testInit() {
		g.init();
		File gitDirFile = new File(gitDir);
		File srcGitDirFile = new File(srcGitDir);
		assertTrue(gitDirFile.exists());
		assertTrue(srcGitDirFile.exists());
		
		
	}

}
