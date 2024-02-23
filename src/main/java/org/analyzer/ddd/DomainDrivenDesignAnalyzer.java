package org.analyzer.ddd;

import org.utils.Program;
import org.utils.annotations.Aggregate;
import org.utils.annotations.Entity;
import org.utils.annotations.Identifier;

import org.analyzer.Analyzer;
import org.analyzer.DDDResult;
import org.utils.ResultStatus;
import org.utils.Interface;
import org.utils.Type;
import org.utils.annotations.Part;
import org.utils.annotations.Repository;
import org.utils.annotations.Service;
import org.utils.annotations.ValueObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DomainDrivenDesignAnalyzer implements Analyzer {
  private final Set< Type > types;
  private final Set< Interface > interfaces;

  public DomainDrivenDesignAnalyzer( Program program ) {
    this.types = program.getTypes();
    this.interfaces = program.getInterfaces();
  }

  @Override
  public List<DDDResult> analyze() {
    var results = new LinkedList<DDDResult>();
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

  private List<DDDResult> analyzeTypes(Type type ) {
    var results = new LinkedList<DDDResult>();
    results.addAll( checkForEntity( type ) );
    results.addAll( checkForAggregate( type ));
    results.addAll( checkForRepository( type ));
    results.addAll( checkForService( type ) );
    results.addAll( checkForValueObject( type ) );
    return results;
  }

  private List<DDDResult> checkForEntity(Type type ) {
    var results = new LinkedList<DDDResult>();
    if ( type.getAnnotations().stream().anyMatch(clazz -> clazz.getClass() == Entity.class)) {
      results.add( checkEntityConstrains( type ) );
    }
    return results;
  }

  private List<DDDResult> checkForAggregate(Type type ) {
    var results = new LinkedList<DDDResult>();
    if ( type.getAnnotations().stream().anyMatch(clazz -> clazz.getClass() == Aggregate.class)) {
      results.addAll( checkAggregateConstrains( type ) );
    }
    return results;
  }

  private List<DDDResult> checkForRepository(Type type ) {
    var results = new LinkedList<DDDResult>();
    if ( type.getAnnotations().stream().anyMatch(clazz -> clazz.getClass() == Repository.class)) {
      results.addAll( checkAggregateConstrains( type ) );
    }
    return results;
  }

  private List<DDDResult> checkForService(Type type ) {
    var results = new LinkedList<DDDResult>();
    if ( type.getAnnotations().stream().anyMatch(clazz -> clazz.getClass() == Service.class)) {
      results.addAll( checkServiceConstrains( type ) );
    }
    return results;
  }

  private List<DDDResult> checkForValueObject(Type type ) {
    var results = new LinkedList<DDDResult>();
    if ( type.getAnnotations().stream().anyMatch(clazz -> clazz.getClass() == ValueObject.class)) {
      results.addAll( checkValueObjectConstrains( type ) );
    }
    return results;
  }

  /**
   * Entity - Checks C7: One Operation or at least one Property defines the identity
   *
   * @param type - Jolie type with @Entity annotation
   * @return results of the check
   */
  private DDDResult checkEntityConstrains(Type type ) {
    var identity = type.getSubnodes().stream().flatMap( subNode -> subNode.getAnnotations().stream() )
        .anyMatch( annotation -> annotation.getClass() == Identifier.class);
    if ( identity ) {
      return new DDDResult( type.getName(), type.getLine(), "Entity has an identifier.", ResultStatus.CORRECT );
    } else {
      return new DDDResult( type.getName(), type.getLine(), "Entity has no identifier.", ResultStatus.VIOLATION );
    }
  }

  /**
   * Aggregate   - C5: Only Entities may be Aggregate roots
   *              - C6: Aggregate must contain at least one part
   *
   * @param type - Jolie type with @Entity annotation
   * @return results of the check
   */
  private List<DDDResult> checkAggregateConstrains (Type type ) {
    var results = new LinkedList<DDDResult>();
    var hasEntity = type.getAnnotations().stream().anyMatch( annotation -> annotation.getClass() == Entity.class );
    var hasPart = type.getSubnodes().stream().flatMap(subNode -> subNode.getAnnotations().stream())
        .anyMatch(annotation -> annotation.getClass() == Part.class);

    // Check for entity
    if ( hasEntity ) {
      results.add(new DDDResult( type.getName(), type.getLine(), "Aggregate is an entity.", ResultStatus.CORRECT ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Aggregate is not an entity.",
        ResultStatus.VIOLATION ));
    }

    // Check for parts
    if ( hasPart ) {
      results.add(new DDDResult( type.getName(), type.getLine(), "Aggregate has parts.", ResultStatus.CORRECT ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Aggregate misses a part.", ResultStatus.VIOLATION ));
    }
    return results;
  }

  /**
   * Repository - C8: Class has no other stereotypes
   *            - C9: Class contains only Operations and at least one
   *            - C10: Outgoing Associations must point to Entities or Value Objects
   *
   * @param type - Jolie type with @Entity annotation
   * @return results of the check
   */
  private List<DDDResult> checkRepositoryConstrains(Type type ) {
    var results = new LinkedList<DDDResult>();
    var repositoryType = type.getAnnotations().stream().anyMatch( annotation -> annotation.getClass()
      == Repository.class )
      && type.getAnnotations().size() == 1;

    // C8: Class has no other stereotypes
    if ( repositoryType ) {
      results.add(new DDDResult( type.getName(), type.getLine(), "Repository is the only stereotype.",
        ResultStatus.CORRECT ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Repository has additional stereotypes.",
        ResultStatus.VIOLATION ));
    }

    // To-Do: C9: Class contains only Operations and at least one

    // To-Do: C10: Outgoing Associations must point to Entities or Value Objects
    var hasEntityOrValueObject = type.getSubnodes().stream().flatMap(subNode ->subNode.getAnnotations().stream())
            .anyMatch(a -> a.getClass() == Entity.class || a.getClass() == ValueObject.class);
    // C8: Class has no other stereotypes
    if ( hasEntityOrValueObject ) {
      results.add(new DDDResult( type.getName(), type.getLine(), "Repository has entity or value object.",
        ResultStatus.CORRECT ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Repository has np entity or value object.",
        ResultStatus.VIOLATION ));
    }
    return results;
  }

  /**
   * Service  - C11: Class has no other stereotypes
   *          - C12: Class contains only Operations and at least one
   *
   * @param type - Jolie type with @Entity annotation
   * @return results of the check
   */
  private List<DDDResult> checkServiceConstrains(Type type ) {
    var results = new LinkedList<DDDResult>();
    var serviceType = type.getAnnotations().stream().anyMatch( annotation -> annotation.getClass() == Service.class )
      && type.getAnnotations().size() == 1;

    // C8: Class has no other stereotypes
    if ( serviceType ) {
      results.add(new DDDResult( type.getName(), type.getLine(), "Service is the only stereotype.",
        ResultStatus.CORRECT ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Service has additional stereotypes.",
        ResultStatus.VIOLATION ));
    }
    return results;
  }

  /**
   * Value Object - X01: No entity annotation
   *              - X02: Side effect free operations
   *              - X03: Immutable attributes
   *
   * @param type - Jolie type with @Entity annotation
   * @return results of the check
   */
  private List<DDDResult> checkValueObjectConstrains(Type type ) {
    var results = new LinkedList<DDDResult>();
    var noEntity = type.getAnnotations().stream().anyMatch( annotation -> annotation.getClass() == Entity.class )
            && type.getAnnotations().size() == 1;

    // X01: No entity annotation
    if ( noEntity ) {
      results.add(new DDDResult( type.getName(), type.getLine(),
        "Value Object and Entity can't be used for the same type.", ResultStatus.VIOLATION ));
    } else {
      results.add(new DDDResult( type.getName(), type.getLine(), "Value Object has no entity annotation.",
        ResultStatus.CORRECT ));
    }
    return results;
  }

  private List<DDDResult> analyzeInterface(Interface anInterface ) {
    var results = new LinkedList<DDDResult>();
    return results;
  }
}
