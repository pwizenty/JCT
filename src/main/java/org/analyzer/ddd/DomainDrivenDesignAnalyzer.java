package org.analyzer.ddd;

import org.utils.Program;
import org.utils.annotations.Entity;
import org.utils.annotations.Identifier;

import org.analyzer.Analyzer;
import org.analyzer.Result;
import org.analyzer.ResultStatus;
import org.utils.Interface;
import org.utils.Type;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainDrivenDesignAnalyzer implements Analyzer {
  private final Set< Type > types;
  private final Set< Interface > interfaces;

  public DomainDrivenDesignAnalyzer( Program program ) {
    this.types = program.getTypes();
    this.interfaces = program.getInterfaces();
  }

  @Override
  public List< Result > analyze() {
    var results = new LinkedList< Result >();
    // Analyze types
    types.forEach( type -> {
      results.addAll( analyzeTypes( type ) );
    } );

    // Analyze interfaces
    interfaces.forEach( anInterface -> {
      results.addAll( analyzeInterface( anInterface ) );
    } );

    return results;
  }

  private List< Result > analyzeTypes( Type type ) {
    var results = new LinkedList< Result >();
    results.addAll( checkForEntity( type ) );
    return results;
  }

  private List< Result > checkForEntity( Type type ) {
    var results = new LinkedList< Result >();
    if ( type.getAnnotations().stream().findAny().get().getClass() == Entity.class ) {
      results.add( checkEntityConstrains( type ) );
    }
    return results;
  }

  private Result checkEntityConstrains( Type type ) {
    var identity = type.getSubnodes().stream().flatMap( subnode -> subnode.getAnnotations()
            .stream() ).collect( Collectors.toSet() )
        .stream().anyMatch( annotation -> annotation.getClass() == Identifier.class );
    if ( identity ) {
      return new Result( type.getName(), type.getLine(), "Entity has an identifier", ResultStatus.CORRECT );
    } else {
      return new Result( type.getName(), type.getLine(), "Entity has no identifier", ResultStatus.VIOLATION );
    }
  }

  private List< Result > analyzeInterface( Interface anInterface ) {
    var results = new LinkedList< Result >();
    return results;
  }
}
