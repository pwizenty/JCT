package org.jolie;

import jolie.Interpreter;
import jolie.cli.CommandLineException;
import jolie.cli.CommandLineParser;
import jolie.lang.CodeCheckingException;
import jolie.lang.parse.ParserException;
import jolie.lang.parse.SemanticVerifier;
import jolie.lang.parse.ast.InterfaceDefinition;
import jolie.lang.parse.ast.Program;
import jolie.lang.parse.ast.types.TypeDefinition;
import jolie.lang.parse.module.ModuleException;
import jolie.lang.parse.util.ParsingUtils;
import org.utils.Interface;
import org.utils.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

public class StructParser {

 public static org.utils.Program parse( String[] args ) throws IOException, CommandLineException, ParserException, CodeCheckingException, ModuleException {
   Interpreter.Configuration interpreterConfiguration =
     new CommandLineParser( args, StructParser.class.getClassLoader() ).getInterpreterConfiguration();
   SemanticVerifier.Configuration configuration =
     new SemanticVerifier.Configuration( interpreterConfiguration.executionTarget() );
   configuration.setCheckForMain( false );
   final InputStream sourceIs;
   sourceIs = interpreterConfiguration.inputStream();
   Program program = ParsingUtils.parseProgram(
     sourceIs,
     interpreterConfiguration.programFilepath().toURI(),
     interpreterConfiguration.charset(),
     new String[ 0 ], // includesPath
     new String[ 0 ], // packagePath
     interpreterConfiguration.jolieClassLoader(),
     interpreterConfiguration.constants(),
     configuration,
     true );

   Set< Interface > interfaces = program.children().stream()
     .filter( c -> c instanceof InterfaceDefinition )
     .map( node -> new JolieVisitor().visit( ( InterfaceDefinition ) node ) )
     .collect( Collectors.toSet() );

   Set< Type > types = program.children().stream()
     .filter( c -> c instanceof TypeDefinition )
     .map( node -> new JolieVisitor().visit( ( TypeDefinition ) node ) )
     .collect( Collectors.toSet() );

   return new org.utils.Program(
     interpreterConfiguration.programFilepath().toPath(),
     types,
     interfaces
   );

 }

}
