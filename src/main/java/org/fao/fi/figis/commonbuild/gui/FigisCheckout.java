package org.fao.fi.figis.commonbuild.gui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;

public class FigisCheckout {

	public static final String URI_PRE = "git://github.com/erikvaningen/";
	public static final String URI_POST = ".git";

	private boolean execute;
	private File gitDirFile;
	private String tag;
	private String moduleName;
	private FileAndUILogger fileAndUILogger;

	public void setFileAndUILogger(FileAndUILogger fileAndUILogger) {
		this.fileAndUILogger = fileAndUILogger;
	}

	/**
	 * Most probably this is the process step where it is all happening.
	 * 
	 * 
	 */
	public void init() {
		System.out.println("init");
		if (execute) {
			try {
				if (tag == null) {
					String message = "tag is null";
					fileAndUILogger.sendMessage(message);
					throw new BuildException(message);
				}
				if (gitDirFile == null) {
					String message = "gitDirFile is null";
					fileAndUILogger.sendMessage(message);
					throw new BuildException(message);
				}
				CloneCommand clone = Git.cloneRepository();
				String refTag = "refs/tags/" + tag;
				String uri = URI_PRE + moduleName + URI_POST;
				FileUtils.deleteDirectory(gitDirFile);
				fileAndUILogger.sendMessage("checking out tag " + refTag);
				clone.setURI(uri).setDirectory(gitDirFile).setBranch(refTag);
				Git db = clone.call();
				if (!db.getRepository().getTags().containsKey(tag)) {
					FileUtils.deleteDirectory(gitDirFile);
					String message = "the tag " + refTag + " does not exist";
					fileAndUILogger.sendMessage(message);
					throw new BuildException(message);
				}
			} catch (InvalidRemoteException e) {
				e.printStackTrace();
				fileAndUILogger.sendMessage(e.getMessage());
				throw new BuildException(e);
			} catch (GitAPIException e) {
				e.printStackTrace();
				fileAndUILogger.sendMessage(e.getMessage());
				throw new BuildException(e);
			} catch (IOException e) {
				e.printStackTrace();
				fileAndUILogger.sendMessage(e.getMessage());
				throw new BuildException(e);
			}
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
		// this.gitDir = gitDir;
		this.gitDirFile = new File(gitDir);
		System.out.println("setGitDir");
		return this;
	}

	public void executeTarget(String allTarget) {
		System.out.println("executeTarget");
	}

	public FigisCheckout setModuleName(String moduleName) {
		this.moduleName = moduleName;
		return this;
	}

}
