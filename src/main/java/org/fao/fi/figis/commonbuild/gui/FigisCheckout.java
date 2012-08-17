package org.fao.fi.figis.commonbuild.gui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildLogger;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;

public class FigisCheckout {

	public static final String URI = "git://github.com/erikvaningen/figistry.git";

	private boolean execute;
	private String gitDir;
	private String gitSrcDir;
	private File gitSrcDirFile;
	private File gitDirFile;
	private String allTarget;
	private String tag;

	private BuildLogger buildLogger;

	/**
	 * Most probably this is the process step where it is all happening.
	 * 
	 * 
	 */
	public void init() {
		System.out.println("init");
		if (execute) {
			try {
				if (tag == null || gitDirFile == null) {
					throw new BuildException("tag or gitDirFile is null");
				}
				CloneCommand clone = Git.cloneRepository();
				FileUtils.deleteDirectory(gitDirFile);
				String refTag = "refs/tags/" + tag;
				System.out.println("checking out tag " + refTag);
				clone.setURI(URI).setDirectory(gitDirFile).setBranch(refTag);
				clone.call();
			} catch (InvalidRemoteException e) {
				e.printStackTrace();
				throw new BuildException(e);
			} catch (GitAPIException e) {
				e.printStackTrace();
				throw new BuildException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new BuildException(e);
			}
		}
	}

	/**
	 * Clone the repo when not yet exist.
	 */
	private void cloneRepo() {
		try {
			CloneCommand clone = Git.cloneRepository();
			FileUtils.deleteDirectory(gitDirFile);

			String refTag = "refs/tags/" + tag;
			clone.setURI(URI).setDirectory(gitDirFile).setBranch(refTag);
			// clone.setURI(URI).setDirectory(gitDirFile).setBare(false);
			clone.call();
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	public FigisCheckout setTag(String tag) {
		this.tag = tag;
		return this;
	}

	public FigisCheckout setExecute(boolean execute) {
		this.execute = execute;
		System.out.println("setExecute = " + execute);
		return this;
	}

	public FigisCheckout setGitDir(String gitDir) {
		this.gitDir = gitDir;
		this.gitDirFile = new File(gitDir);
		System.out.println("setGitDir");
		return this;
	}

	public FigisCheckout setGitSrcDir(String gitSrcDir) {
		this.gitSrcDir = gitSrcDir;
		this.gitSrcDirFile = new File(gitSrcDir);
		System.out.println("setGitSrcDir");
		return this;
	}

	public void executeTarget(String allTarget) {
		System.out.println("executeTarget");

	}

	public void addBuildListener(BuildLogger logger) {
		this.buildLogger = logger;
		System.out.println("addBuildListener");
	}

}
