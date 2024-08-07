/******************************************************************************
 * Copyright (C) 2024 by X                                                    *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU Library General Public License as            *
 * published by the Free Software Foundation; either version 2 of the         *
 * License, or (at your option) any later version.                            *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU Library General Public          *
 * License along with this program; if not, write to the                      *
 * Free Software Foundation, Inc.,                                            *
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.                  *
 *                                                                            *
 * For details about the authors of this software, see the AUTHORS file.      *
 ******************************************************************************/

package org.jolie;

import jolie.lang.parse.ast.*;
import jolie.lang.parse.ast.types.*;
import org.utils.Interface;
import org.utils.Operation;
import org.utils.Type;
import org.utils.TypeWithCardinality;
import org.utils.annotations.Annotation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JolieVisitor {

  public JolieVisitor() {
  }

  public Type visit( TypeDefinition typeDefinition ) {
    return switch ( typeDefinition ) {
      case TypeInlineDefinition typeInlineDefinition -> _visit( typeInlineDefinition );
      case TypeDefinitionLink typeDefinitionLink -> _visit( typeDefinitionLink );
      case TypeChoiceDefinition typeChoiceDefinition -> _visit( typeChoiceDefinition );
      case null, default -> throw new RuntimeException( "Unrecognized " + typeDefinition.getClass() );
    };
  }

  public Type _visit( TypeInlineDefinition typeInlineDefinition ) {
    String name = typeInlineDefinition.name();
    Set< Annotation > annotations = typeInlineDefinition.getDocumentation().stream()
        .flatMap( a -> AnnotationParser.parse( a ).stream() )
        .collect( Collectors.toSet() );
    Set< TypeWithCardinality > subnodes = new HashSet<>();
    if ( typeInlineDefinition.subTypes() != null ) {
      subnodes = typeInlineDefinition.subTypes().stream()
          .map( e ->
              visit( e.getValue() ).toTypeWithCardinality( e.getValue().cardinality().min(), e.getValue().cardinality()
                .max() )
          ).collect( Collectors.toSet() );
    }
    String value = typeInlineDefinition.basicType().nativeType().name();
    return new Type( name, annotations, subnodes, true, value ).setLine( typeInlineDefinition.context().line() );
  }

  public Type _visit( TypeDefinitionLink typeDefinitionLink ) {
   Set< Annotation > annotations = typeDefinitionLink.getDocumentation().stream()
       .flatMap( a -> AnnotationParser.parse( a ).stream() )
       .collect( Collectors.toSet() );
    return new Type( typeDefinitionLink.name(), annotations, Collections.emptySet(), false,
        typeDefinitionLink.linkedTypeName()
    ).setLine(typeDefinitionLink.context().line());
  }

  public Type _visit( TypeChoiceDefinition typeChoiceDefinition ) {
    Set< Annotation > annotations = typeChoiceDefinition.getDocumentation().stream()
        .flatMap( a -> AnnotationParser.parse( a ).stream() )
        .collect( Collectors.toSet() );
    return new Type( typeChoiceDefinition.name(),
        annotations, Collections.emptySet(), false,
        typeChoiceDefinition.left().name() + " + " + typeChoiceDefinition.right().name()
    ).setLine(typeChoiceDefinition.context().line());
  }

  public Interface visit( InterfaceDefinition interfaceDefinition ) {
    return new Interface(
        interfaceDefinition.name(),
        interfaceDefinition.getDocumentation().stream()
            .flatMap( a -> AnnotationParser.parse( a ).stream() )
            .collect( Collectors.toSet() ),
        interfaceDefinition.operationsMap().values().stream()
            .map( this::visit ).collect( Collectors.toSet() )
    ).setLine( interfaceDefinition.context().line() );
  }

  public Operation visit( OperationDeclaration operationDeclaration ) {
    if ( operationDeclaration instanceof OneWayOperationDeclaration ) {
      return visit( ( OneWayOperationDeclaration ) operationDeclaration );
    } else {
      return visit( ( RequestResponseOperationDeclaration ) operationDeclaration );
    }
  }

  public Operation visit( OneWayOperationDeclaration oneWayOperationDeclaration ) {
    return new Operation(
        oneWayOperationDeclaration.id(),
        oneWayOperationDeclaration.getDocumentation().stream()
            .flatMap( a -> AnnotationParser.parse( a ).stream() )
            .collect( Collectors.toSet() ),
        oneWayOperationDeclaration.requestType().name()
    ).setLine( oneWayOperationDeclaration.context().line() );
  }

  public Operation visit( RequestResponseOperationDeclaration requestResponseOperationDeclaration ) {
    return new Operation(
        requestResponseOperationDeclaration.id(),
        requestResponseOperationDeclaration.getDocumentation().stream()
            .flatMap( a -> AnnotationParser.parse( a ).stream() )
            .collect( Collectors.toSet() ),
        requestResponseOperationDeclaration.requestType().name(),
        requestResponseOperationDeclaration.responseType().name()
    ).setLine( requestResponseOperationDeclaration.context().line() );
  }
}