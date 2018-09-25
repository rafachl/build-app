package com.sysmo.builder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, GitAPIException {

		Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File("E:/Build/.git/")).build();

		Git git = new Git(existingRepo);

		Scanner scanner = new Scanner(System.in);
		UsernamePasswordCredentialsProvider cp = new UsernamePasswordCredentialsProvider("rafa_chl@hotmail.com",
				"@rafaelchl723210");

		System.out.println("INFORME A BRANCH:");
		String branchName = scanner.next();

		CheckoutCommand command = git.checkout();
		command.setName(branchName);
		try {
			System.out.println("REALIZANDO CHECKOUT LOCAL");
			command.call();
		} catch (Exception e) {
			System.out.println("FALHA AO REALIZAR CHECKOUT LOCAL");
			System.out.println("REALIZANDO CHECKOUT REMOTO");
			command.setCreateBranch(true);
			command.setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK);
			command.setStartPoint("origin/" + branchName).call();

		}
		System.out.println("REALIZANDO PULL");

		PullResult aa = git.pull().call();
		if (aa.isSuccessful()) {
			System.out.println("SUCESSO AO REALIZAR PUll");
			Runtime.getRuntime().exec("cmd /c start C:/teste.bat");

		}else {
			System.out.println("FALHA AO REALIZAR PUll");

		}

	}
}
