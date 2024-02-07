package org.jolie;

import jolie.cli.CommandLineException;
import jolie.lang.CodeCheckingException;
import jolie.lang.parse.ParserException;
import jolie.lang.parse.module.ModuleException;
import org.analyzer.Result;
import org.analyzer.ddd.DomainDrivenDesignAnalyzer;

import java.io.IOException;
import java.util.List;

public class Main {

 public static void main( String[] args ) {
  try {
   System.out.println(
     StructParser.parse( new String[]{ "src/test/jolie/MyInterface.ol" } )
   );

   // Start program analysis
   // Check for entity violations
   var program = StructParser.parse( new String[]{ "src/test/jolie/EntityTest.ol" } );
   var results = new DomainDrivenDesignAnalyzer(program).analyze();
   printResults(results);

  } catch ( IOException | CommandLineException | ParserException | CodeCheckingException | ModuleException e ) {
   e.printStackTrace();
  }
 }

 public static void printResults(List<Result> results) {
  System.out.println("+--------------------------------------------------------------------------------------+");
  System.out.println("| Entity                    | Line Number | Description                              | Status    |");
  System.out.println("+--------------------------------------------------------------------------------------+");
  for (Result result : results) {
   System.out.printf("| %-25s | %-11d | %-40s | %-9s |\n",
           result.getViolationName(), result.getLineNumber(), result.getDescription(), result.getStatus());
  }
 }

}
