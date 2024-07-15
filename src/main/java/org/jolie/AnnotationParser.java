/******************************************************************************
 * Copyright (C) 2024 by XXX                                                  *
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

import org.utils.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class AnnotationParser {

  private static HashMap< String, Class< ? extends Annotation > > aMap = new HashMap<>();

  static {
    aMap.put( "Entity", Entity.class );
    aMap.put( "Identifier", Identifier.class );
    aMap.put( "Part", Part.class );
    aMap.put( "Aggregate", Aggregate.class );
    aMap.put( "Repository", Repository.class );
    aMap.put( "Value_Object", ValueObject.class );
    aMap.put( "ctz", BoundedContext.class );
  }

  public AnnotationParser() {
  }

  public static Set< Annotation > parse( String annotation ) {
    return Arrays.stream( annotation.split( "\n" ) )
        .map( String::trim )
        .map( AnnotationParser::parseSingleAnnotation )
        .filter( Optional::isPresent )
				.map( Optional::get )
        .collect( Collectors.toSet() );
  }

  private static Optional< Annotation > parseSingleAnnotation( String s ) {
    if ( s.isEmpty() || s.isBlank() ) {
			return Optional.empty();
    } else {
      try {
        Optional< Class< ? extends Annotation > > c = aMap.entrySet().stream()
            .filter( e -> s.toLowerCase().contains( e.getKey().toLowerCase() ) )
            .findAny().map( Map.Entry::getValue );
        if ( c.isPresent() ) {
          return Optional.of( c.get().getConstructor().newInstance() );
        }
      } catch ( NoSuchMethodException | InvocationTargetException | InstantiationException |
                IllegalAccessException e ) {
        e.printStackTrace();
      }
      return Optional.of( new UnrecognizedAnnotation( s ) );
    }
  }
}