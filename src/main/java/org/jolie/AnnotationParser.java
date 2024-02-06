/******************************************************************************
 * Copyright (C) 2024 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>  *
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

import jolie.lang.parse.ast.InterfaceDefinition;
import jolie.lang.parse.ast.types.TypeChoiceDefinition;
import jolie.lang.parse.ast.types.TypeDefinitionLink;
import jolie.lang.parse.ast.types.TypeInlineDefinition;
import org.utils.Interface;
import org.utils.Type;
import org.utils.TypeWithCardinality;
import org.utils.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class AnnotationParser {

	public AnnotationParser() {}

	public static Set< Annotation > parse( String annotation ) {
		return Arrays.stream( annotation.split( "\n" ) )
				.map( String::trim )
				.map( AnnotationParser::parseSingleAnnotation )
				.collect( Collectors.toSet());
	}

	private static Annotation parseSingleAnnotation( String s ) {
		try {
			HashMap< String, Class< ? extends Annotation > > aMap = new HashMap<>();
			aMap.put( "Entity", Entity.class );
			aMap.put( "Identifier", Identifier.class );
			aMap.put( "Part", Part.class );
			aMap.put( "Aggregate", Aggregate.class );
			Optional< Class< ? extends Annotation > > c = aMap.entrySet().stream()
					.filter( e -> s.toLowerCase().contains( e.getKey().toLowerCase() ) )
					.findAny().map( Map.Entry::getValue );
			if ( c.isPresent() ) {
				return c.get().getConstructor().newInstance();
			}
		} catch ( NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e ){
			e.printStackTrace();
		}
		return new UnrecognizedAnnotation( s );
	}

}