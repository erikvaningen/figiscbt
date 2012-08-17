package org.fao.fi.figis.commonbuild.gui;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildLogger;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.transport.RefSpec;

public class GitCheckout {

	public static final String URI = "git://github.com/erikvaningen/figistry.git";

	private boolean execute;
	private String gitDir;
	private String gitSrcDir;
	private String allTarget;
	private String tag;
	private BuildLogger buildLogger;
	private File gitSrcDirFile;

	public void setExecute(boolean execute) {
		this.execute = execute;
		System.out.println("setExecute = " + execute);
	}

	public void setGitDir(String gitDir) {
		this.gitDir = gitDir;
		System.out.println("setGitDir");
	}

	public void setGitSrcDir(String gitSrcDir) {
		this.gitSrcDir = gitSrcDir;
		this.gitSrcDirFile = new File(gitSrcDir);
		System.out.println("setGitSrcDir");
	}

	public void executeTarget(String allTarget) {
		System.out.println("executeTarget");
	}

	public void addBuildListener(BuildLogger logger) {
		this.buildLogger = logger;
		System.out.println("addBuildListener");
	}

	/**
	 * Most proably this is the process step where it is all happening.
	 * 
	 * 
	 */
	public void init() {
		System.out.println("init");
		if (execute) {
			cloneRepo();
			fetch();
			checkoutTag();
		}
	}

	/**
	 * Clone the repo when not yet exist.
	 */
	private void cloneRepo() {
		try {
			CloneCommand clone = Git.cloneRepository();
			clone.setBare(false);
			clone.setCloneAllBranches(false);
			if (!gitSrcDirFile.exists()) {
				clone.setDirectory(gitSrcDirFile).setURI(URI);
				clone.call();
			}
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	private void fetch() {
		try {
			FileRepository db = new FileRepository(gitDir);
			Git git = new Git(db);
			RefSpec spec = new RefSpec("refs/tags/*:refs/remotes/origin/tags/*");
			git.fetch().setRemote(URI).setRefSpecs(spec).call();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (TransportException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	private void checkoutTag() {
		try {
			FileRepository db = new FileRepository(gitDir);
			Git git = new Git(db);
			CheckoutCommand co = git.checkout();
			co.setName(tag).call();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (RefAlreadyExistsException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (RefNotFoundException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (InvalidRefNameException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (CheckoutConflictException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

}
