package org.jolie;

import jolie.Interpreter;
import jolie.cli.CommandLineException;
import jolie.cli.CommandLineParser;
import jolie.lang.parse.Scanner;
import jolie.lang.parse.SemanticVerifier;
import org.utils.annotations.BoundedContext;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundedContextParser {

  public static Optional< BoundedContext > parse( String[] args ) {
    try {
      Interpreter.Configuration interpreterConfiguration =
          new CommandLineParser( args, StructParser.class.getClassLoader() ).getInterpreterConfiguration();
      SemanticVerifier.Configuration configuration =
          new SemanticVerifier.Configuration( interpreterConfiguration.executionTarget() );
      configuration.setCheckForMain( false );
      configuration.setCheckForMain( false );
      Scanner s = new Scanner( interpreterConfiguration.inputStream(),
          interpreterConfiguration.programFilepath().toURI(),
          interpreterConfiguration.charset(), true );
      Scanner.Token firstToken = s.getToken();
      if ( firstToken.type().equals( Scanner.TokenType.DOCUMENTATION_FORWARD ) ) {
        return parseAnnotation( firstToken.content() );
      }
    } catch ( CommandLineException | IOException ignored ) {}
    return Optional.empty();
  }

  public static Optional< BoundedContext > parseAnnotation( String s ){
    Matcher m = Pattern.compile( "@ctx\\(\\s*([^)]+)\\s*\\)" ).matcher( s );
    if(  m.find() ){
      return Optional.of( new BoundedContext( m.group( 1 ) ) );
    } else {
      return Optional.empty();
    }
  }

}
