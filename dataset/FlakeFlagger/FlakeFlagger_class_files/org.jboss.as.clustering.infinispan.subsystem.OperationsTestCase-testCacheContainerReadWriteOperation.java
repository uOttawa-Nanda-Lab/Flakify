package org.jboss.as.clustering.infinispan.subsystem;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OUTCOME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUCCESS;

import java.util.Collections;

import org.jboss.as.clustering.controller.Attribute;
import org.jboss.as.clustering.controller.Operations;
import org.jboss.as.clustering.controller.SimpleAttribute;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.controller.operations.common.Util;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Test case for testing individual management operations.
 *
 *  These test cases are based on the XML config in subsystem-infinispan-test,
 *  a non-exhaustive subsystem configuration.
 *
 * @author Richard Achmatowicz (c) 2011 Red Hat Inc.
*/
public class OperationsTestCase extends OperationTestCaseBase {

    @Test public void testCacheContainerReadWriteOperation() throws Exception{String subsystemXml=getSubsystemXml();KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();ModelNode result=servicesA.executeOperation(getCacheContainerReadOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());Assert.assertEquals("local",result.get(RESULT).asString());result=servicesA.executeOperation(getCacheContainerWriteOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE,"new-default-cache"));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());result=servicesA.executeOperation(getCacheContainerReadOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());Assert.assertEquals("new-default-cache",result.get(RESULT).asString());}

    @SuppressWarnings("deprecation")
    private static ModelNode createStringKeyedTable() {

        // create a string-keyed-table complex attribute
        ModelNode stringKeyedTable = new ModelNode().setEmptyObject();
        stringKeyedTable.get(StringTableResourceDefinition.Attribute.PREFIX.getName()).set("ispn_bucket");
        stringKeyedTable.get(TableResourceDefinition.DeprecatedAttribute.BATCH_SIZE.getName()).set(100);
        stringKeyedTable.get(TableResourceDefinition.Attribute.FETCH_SIZE.getName()).set(100);

        ModelNode idColumn = stringKeyedTable.get(TableResourceDefinition.ColumnAttribute.ID.getName()).setEmptyObject();
        idColumn.get(TableResourceDefinition.ColumnAttribute.ID.getColumnName().getName()).set("id");
        idColumn.get(TableResourceDefinition.ColumnAttribute.ID.getColumnType().getName()).set("VARCHAR");

        ModelNode dataColumn = stringKeyedTable.get(TableResourceDefinition.ColumnAttribute.DATA.getName()).setEmptyObject();
        dataColumn.get(TableResourceDefinition.ColumnAttribute.DATA.getColumnName().getName()).set("datum");
        dataColumn.get(TableResourceDefinition.ColumnAttribute.DATA.getColumnType().getName()).set("BINARY");

        ModelNode timestampColumn = stringKeyedTable.get(TableResourceDefinition.ColumnAttribute.TIMESTAMP.getName()).setEmptyObject();
        timestampColumn.get(TableResourceDefinition.ColumnAttribute.TIMESTAMP.getColumnName().getName()).set("version");
        timestampColumn.get(TableResourceDefinition.ColumnAttribute.TIMESTAMP.getColumnType().getName()).set("BIGINT");

        return stringKeyedTable;
    }
}