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
import java.util.List;

public class Main {

  public static void main( String[] args ) {
    try {
      /**
      System.out.println(
          StructParser.parse( new String[]{ "src/test/jolie/MyInterface.ol" } )
      );*/

      // Start program analysis
      // Check for entity violations
      //var program = StructParser.parse( new String[]{ "src/test/jolie/EntityTest.ol" } );
      //var program = StructParser.parse( new String[]{ "src/test/jolie/AggregateTest.ol" } );

      var program = StructParser.parse( new String[]{ "src/test/jolie/RepositoryTest.ol" } );
      var results = new DomainDrivenDesignAnalyzer( program ).analyze();
      printDDDResults( results );

      var modelProgram = StructParser.parse( new String[]{ "src/test/jolie/sample-2.ol" } );
      var jolieProgram = StructParser.parse( new String[]{ "src/test/jolie//sample-2-edit.ol" } );

      var jolieComparator = new JolieComparator( modelProgram, jolieProgram );
      var compareResults = jolieComparator.compare();

      printCompareResults(compareResults);


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
    for ( CompareResult compareResult : compareResults) {
      System.out.printf( "| %-25s | %-11d | %-40s | %-9s | %-13s |\n",
        compareResult.getModelName(), compareResult.getLineNumber(), compareResult.getMessage(),
          compareResult.getStatus(), compareResult.getType() );
    }
  }
}
