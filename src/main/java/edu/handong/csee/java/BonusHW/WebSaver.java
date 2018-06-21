package edu.handong.csee.java.BonusHW;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class WebSaver {

	String urlPath;
	String dirPath;
	boolean help;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebSaver ws = new WebSaver();
		ws.run(args);
	}
	
	private void run(String[] args) {
	Options options = createOptions();
	
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
		}
		System.out.println("url:"+urlPath);
		System.out.println("dir:"+dirPath);
		
		URLReader ur = new URLReader(urlPath);
		ArrayList<String> linesToWrite = new ArrayList<String>();
		try {
			linesToWrite = ur.read();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		DataWriter writer = new DataWriter();
		writer.writeAFile(linesToWrite, dirPath);
		System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");

	
	
	}
	
	
	
	
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			urlPath = cmd.getOptionValue("u");
			dirPath = cmd.getOptionValue("d");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("d").longOpt("DirectoryPath")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("a directory path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("u").longOpt("URL")
				.desc("a URL")
				.hasArg()     // this option is intended not to have an option value but just an option
				.argName("a URL")
				.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
}
