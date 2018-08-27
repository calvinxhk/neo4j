/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compiler.v3_5.planner.logical.steps

import org.neo4j.cypher.internal.compiler.v3_5.planner.logical.{LeafPlanner, LogicalPlanningContext}
import org.neo4j.cypher.internal.ir.v3_5.{QueryGraph, RequiredOrder}
import org.neo4j.cypher.internal.v3_5.logical.plans.LogicalPlan

object allNodesLeafPlanner extends LeafPlanner {
  def apply(queryGraph: QueryGraph, requiredOrder: RequiredOrder, context: LogicalPlanningContext): Seq[LogicalPlan] =
    queryGraph.patternNodes
    .filter(!queryGraph.argumentIds.contains(_))
    .map(context.logicalPlanProducer.planAllNodesScan(_, queryGraph.argumentIds, context)).toIndexedSeq
}
