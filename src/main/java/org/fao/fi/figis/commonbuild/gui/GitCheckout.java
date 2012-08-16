package org.fao.fi.figis.commonbuild.gui;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildLogger;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitCheckout {
	
	private boolean execute;
	private String gitDir ; 
	private String gitSrcDir ; 
	private String allTarget ; 
	private BuildLogger buildLogger ; 
	

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
		if(execute){
			FileRepositoryBuilder builder = new FileRepositoryBuilder();
			try {
				File gitDireFile = new File(gitDir); 
				builder.setGitDir(gitDireFile).readEnvironment().findGitDir().build();

				//Git git = new Git(repository);              
				CloneCommand clone = Git.cloneRepository();
				clone.setBare(false);
				clone.setCloneAllBranches(true);
				File cloneDir = new File(gitSrcDir); 
				clone.setDirectory(cloneDir).setURI("git://github.com/erikvaningen/figistry.git");
				clone.call();  
			} catch (IOException e) {
				throw new BuildException(e);
			} catch (InvalidRemoteException e) {
				throw new BuildException(e);
			} catch (TransportException e) {
				throw new BuildException(e);
			} catch (GitAPIException e) {
				throw new BuildException(e);
			}
		}
	}

}
