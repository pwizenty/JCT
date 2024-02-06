package org.jolie;

import jolie.cli.CommandLineException;
import jolie.lang.CodeCheckingException;
import jolie.lang.parse.ParserException;
import jolie.lang.parse.module.ModuleException;

import java.io.IOException;

public class Main {

 public static void main( String[] args ) {
  try {
   System.out.println(
     StructParser.parse( new String[]{ "src/test/jolie/MyInterface.ol" } )
   );
  } catch ( IOException | CommandLineException | ParserException | CodeCheckingException | ModuleException e ) {
   e.printStackTrace();
  }
 }

}
