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

import jolie.lang.parse.OLVisitor;
import jolie.lang.parse.ast.*;
import jolie.lang.parse.ast.courier.CourierChoiceStatement;
import jolie.lang.parse.ast.courier.CourierDefinitionNode;
import jolie.lang.parse.ast.courier.NotificationForwardStatement;
import jolie.lang.parse.ast.courier.SolicitResponseForwardStatement;
import jolie.lang.parse.ast.expression.*;
import jolie.lang.parse.ast.types.*;

public class MyVisitor implements OLVisitor< Void, Void > {

	public MyVisitor() {}

	public void visit( TypeDefinition typeDefinition ) {
		if ( typeDefinition instanceof TypeInlineDefinition ) {
			visit( ( TypeInlineDefinition ) typeDefinition, null );
		} else if ( typeDefinition instanceof TypeDefinitionLink ) {
			visit( ( TypeDefinitionLink ) typeDefinition, null );
		} else if ( typeDefinition instanceof TypeChoiceDefinition ) {
			visit( ( TypeChoiceDefinition ) typeDefinition, null );
		} else {
			throw new RuntimeException( "Unrecognized " + typeDefinition.getClass() );
		}
	}

	public void visit( InterfaceDefinition interfaceDefinition ){
		visit( interfaceDefinition, null );
	}

	@Override
	public Void visit( Program program, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OneWayOperationDeclaration oneWayOperationDeclaration, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RequestResponseOperationDeclaration requestResponseOperationDeclaration, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DefinitionNode definitionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ParallelStatement parallelStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SequenceStatement sequenceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NDChoiceStatement ndChoiceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OneWayOperationStatement oneWayOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RequestResponseOperationStatement requestResponseOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotificationOperationStatement notificationOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SolicitResponseOperationStatement solicitResponseOperationStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( LinkInStatement linkInStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( LinkOutStatement linkOutStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AssignStatement assignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AddAssignStatement addAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SubtractAssignStatement subtractAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( MultiplyAssignStatement multiplyAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DivideAssignStatement divideAssignStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( IfStatement ifStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DefinitionCallStatement definitionCallStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( WhileStatement whileStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OrConditionNode orConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( AndConditionNode andConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotExpressionNode notExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CompareConditionNode compareConditionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantIntegerExpression constantIntegerExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantDoubleExpression constantDoubleExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantBoolExpression constantBoolExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantLongExpression constantLongExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ConstantStringExpression constantStringExpression, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ProductExpressionNode productExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SumExpressionNode sumExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VariableExpressionNode variableExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NullProcessStatement nullProcessStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( Scope scope, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstallStatement installStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CompensateStatement compensateStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ThrowStatement throwStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ExitStatement exitStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ExecutionInfo executionInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CorrelationSetInfo correlationSetInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InputPortInfo inputPortInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( OutputPortInfo outputPortInfo, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PointerStatement pointerStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( DeepCopyStatement deepCopyStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( RunStatement runStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( UndefStatement undefStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ValueVectorSizeExpressionNode valueVectorSizeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PreIncrementStatement preIncrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PostIncrementStatement postIncrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PreDecrementStatement preDecrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( PostDecrementStatement postDecrementStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForStatement forStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForEachSubNodeStatement forEachSubNodeStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ForEachArrayItemStatement forEachArrayItemStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SpawnStatement spawnStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( IsTypeExpressionNode isTypeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstanceOfExpressionNode instanceOfExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeCastExpressionNode typeCastExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SynchronizedStatement synchronizedStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CurrentHandlerStatement currentHandlerStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( EmbeddedServiceNode embeddedServiceNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InstallFixedVariableExpressionNode installFixedVariableExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VariablePathNode variablePathNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeInlineDefinition typeInlineDefinition, Void unused ) {
		System.out.println( "TypeInlineDefinition: " + typeInlineDefinition.name() );
		typeInlineDefinition.getDocumentation().ifPresent( System.out::println );
		if ( typeInlineDefinition.hasSubTypes() ){
			typeInlineDefinition.subTypes().stream().forEach( e -> visit( e.getValue() ) );
		}
		return null;
	}

	@Override
	public Void visit( TypeDefinitionLink typeDefinitionLink, Void unused ) {
		System.out.println( "TypeDefinitionLink: " + typeDefinitionLink.name() );
		typeDefinitionLink.getDocumentation().ifPresent( System.out::println );
		return null;
	}

	@Override
	public Void visit( InterfaceDefinition interfaceDefinition, Void unused ) {
		System.out.println( "Interface: " + interfaceDefinition.name() );
		interfaceDefinition.getDocumentation().ifPresent( System.out::println );
		return null;
	}

	@Override
	public Void visit( DocumentationComment documentationComment, Void unused ) {
		return null;
	}

	@Override
	public Void visit( FreshValueExpressionNode freshValueExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CourierDefinitionNode courierDefinitionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( CourierChoiceStatement courierChoiceStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( NotificationForwardStatement notificationForwardStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( SolicitResponseForwardStatement solicitResponseForwardStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InterfaceExtenderDefinition interfaceExtenderDefinition, Void unused ) {
		return null;
	}

	@Override
	public Void visit( InlineTreeExpressionNode inlineTreeExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( VoidExpressionNode voidExpressionNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ProvideUntilStatement provideUntilStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( TypeChoiceDefinition typeChoiceDefinition, Void unused ) {
		System.out.println( "Type Choice: " + typeChoiceDefinition.name() );
		typeChoiceDefinition.getDocumentation().ifPresent( System.out::println );
		return null;
	}

	@Override
	public Void visit( ImportStatement importStatement, Void unused ) {
		return null;
	}

	@Override
	public Void visit( ServiceNode serviceNode, Void unused ) {
		return null;
	}

	@Override
	public Void visit( EmbedServiceNode embedServiceNode, Void unused ) {
		return null;
	}
}