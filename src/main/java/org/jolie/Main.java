package org.jolie;

import jolie.cli.CommandLineException;
import jolie.lang.CodeCheckingException;
import jolie.lang.parse.ParserException;
import jolie.lang.parse.module.ModuleException;
import org.analyzer.DDDResult;
import org.analyzer.ddd.DomainDrivenDesignAnalyzer;
import org.comparator.CompareResult;
import org.comparator.jolie.JolieComparator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

  public static void main( String[] args ) {
    try {
      var plugins = new LinkedList<String>(Arrays.stream(args).filter(plugin ->
        plugin.equals("D3PC") || plugin.startsWith("LEMMA4Jolie") || plugin.startsWith("--help")).toList());

      // help
      if (plugins.contains("--help")) {
        printHelp();
      }

      // D§PC
      if (plugins.contains("D3PC")) {
        var jolieProgram = args[args.length - 1];
        var program = StructParser.parse( new String[]{ jolieProgram } );
        var results = new DomainDrivenDesignAnalyzer( program ).analyze();
        printDDDResults( results );
      }

      // LEMMA4Jolie
      if (plugins.stream().anyMatch(p -> p.startsWith("LEMMA4Jolie"))) {
        var jolieProgram = args[args.length - 1];
        var program = StructParser.parse( new String[]{ jolieProgram } );
        var path = plugins.stream().filter(p -> p.startsWith("LEMMA4Jolie")).findFirst().get();

        var regex = "source=([^&]*)";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(path);

        if (matcher.find()) {
          String modelPath = matcher.group(1).replace("}", "");
          var modelProgram = StructParser.parse( new String[]{ modelPath } );
          var jolieComparator = new JolieComparator( modelProgram, program );
          var compareResults = jolieComparator.compare();
          printCompareResults(compareResults);

        } else {
          System.out.println("Jolie model path not present");
        }
      }
    } catch ( IOException | CommandLineException | ParserException | CodeCheckingException | ModuleException e ) {
      e.printStackTrace();
    }
  }

  public static void printDDDResults( List<DDDResult> DDDResults) {
    System.out.println( "+------------------------------------------------------------------------------------------------+" );
    System.out.println( "| DDD                       | Line Number | Description                              | Status    |" );
    System.out.println( "+------------------------------------------------------------------------------------------------+" );
    for ( DDDResult DDDResult : DDDResults) {
      System.out.printf( "| %-25s | %-11d | %-40s | %-9s |\n",
        DDDResult.getViolationName(), DDDResult.getLineNumber(), DDDResult.getDescription(), DDDResult.getStatus() );
    }
  }

  public static void printCompareResults( List<CompareResult> compareResults) {
    System.out.println( "+----------------------------------------------------------------------------------------------------------------+" );
    System.out.println( "| Name                      | Line Number | Description                              | Status    | Type          |" );
    System.out.println( "+----------------------------------------------------------------------------------------------------------------+" );
    var sortedResults = compareResults.stream().sorted(Comparator.comparingInt(CompareResult::getLineNumber)).toList();
    for ( CompareResult compareResult : sortedResults) {
      System.out.printf( "| %-25s | %-11d | %-40s | %-9s | %-13s |\n",
        compareResult.getModelName(), compareResult.getLineNumber(), compareResult.getMessage(),
          compareResult.getStatus(), compareResult.getType() );
    }
  }

  public static void printHelp() {
    System.out.println("Jolie Checker Toolchain Help (JCT):");
    System.out.println("To execute JCT via terminal use the following parameters:");
    System.out.println("  -p D3PC -  Enables the DDD consistency checks.");
    System.out.println("  -p LEMMA4Jolie={source=<lemma_model.ol>} -  Enables compliance check with the lemma_model.ol file.");
    System.out.println("  File path to the Jolie source file 'example-3-jolie.ol'");
  }
}
