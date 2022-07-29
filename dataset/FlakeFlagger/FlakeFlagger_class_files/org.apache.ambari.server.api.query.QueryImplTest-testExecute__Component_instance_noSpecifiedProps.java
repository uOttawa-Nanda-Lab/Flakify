package org.apache.ambari.server.api.query;

import org.apache.ambari.server.api.resources.ResourceDefinition;
import org.apache.ambari.server.api.util.TreeNode;
import org.apache.ambari.server.api.util.TreeNodeImpl;
import org.apache.ambari.server.controller.predicate.AndPredicate;
import org.apache.ambari.server.controller.predicate.BasePredicate;
import org.apache.ambari.server.controller.spi.*;
import org.apache.ambari.server.controller.utilities.PredicateBuilder;
import org.apache.ambari.server.api.resources.ResourceInstance;
import org.apache.ambari.server.api.services.Result;
import org.apache.ambari.server.controller.utilities.PropertyHelper;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import static org.easymock.EasyMock.*;

import static org.easymock.EasyMock.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


//todo: add assertions for temporal info
public class QueryImplTest {

  ClusterController m_controller = createNiceMock(ClusterController.class);

  @Test public void testExecute__Component_instance_noSpecifiedProps() throws Exception{Result result=createNiceMock(Result.class);ResourceInstance componentResourceInstance=createNiceMock(ResourceInstance.class);ResourceDefinition componentResourceDefinition=createNiceMock(ResourceDefinition.class);ResourceInstance hostResourceInstance=createNiceMock(ResourceInstance.class);ResourceDefinition hostResourceDefinition=createNiceMock(ResourceDefinition.class);Schema componentSchema=createNiceMock(Schema.class);Resource componentResource=createNiceMock(Resource.class);String componentPropertyId="componentId";Query hostComponentQuery=createStrictMock(Query.class);Result hostComponentQueryResult=createNiceMock(Result.class);TreeNode<Resource> tree=new TreeNodeImpl<Resource>(null,null,null);TreeNode<Resource> hostComponentResultNode=new TreeNodeImpl<Resource>(null,null,null);List<Resource> listResources=Collections.singletonList(componentResource);Map<Resource.Type, String> mapResourceIds=new HashMap<Resource.Type, String>();mapResourceIds.put(Resource.Type.Cluster,"clusterName");mapResourceIds.put(Resource.Type.Service,"serviceName");mapResourceIds.put(Resource.Type.Component,"componentName");Map<String, ResourceInstance> mapChildren=new HashMap<String, ResourceInstance>();mapChildren.put("host_components",hostResourceInstance);PredicateBuilder pb=new PredicateBuilder();Predicate predicate=pb.property("clusterId").equals("clusterName").and().property("serviceId").equals("serviceName").and().property("componentId").equals("componentName").toPredicate();expect(componentResourceInstance.getResourceDefinition()).andReturn(componentResourceDefinition).anyTimes();expect(componentResourceInstance.getSubResources()).andReturn(mapChildren).anyTimes();expect(componentResourceInstance.getIds()).andReturn(mapResourceIds).anyTimes();expect(componentResourceDefinition.getType()).andReturn(Resource.Type.Component).anyTimes();expect(componentResource.getType()).andReturn(Resource.Type.Component).anyTimes();expect(componentResource.getPropertyValue(componentPropertyId)).andReturn("keyVal");expect(m_controller.getSchema(Resource.Type.Component)).andReturn(componentSchema).anyTimes();expect(componentSchema.getCategoryProperties()).andReturn(Collections.<String,Set<String>>emptyMap()).anyTimes();expect(componentSchema.getKeyPropertyId(Resource.Type.Cluster)).andReturn("clusterId");expect(componentSchema.getKeyPropertyId(Resource.Type.Service)).andReturn("serviceId");expect(componentSchema.getKeyPropertyId(Resource.Type.Component)).andReturn(componentPropertyId).atLeastOnce();expect(m_controller.getResources(eq(Resource.Type.Component),eq(PropertyHelper.getReadRequest(Collections.<String>emptySet())),eq(predicate))).andReturn(listResources);expect(result.getResultTree()).andReturn(tree).anyTimes();Map<Resource.Type, String> mapResourceIdsSet=new HashMap<Resource.Type, String>(mapResourceIds);mapResourceIdsSet.put(Resource.Type.Component,"keyVal");hostResourceInstance.setIds(mapResourceIdsSet);expect(hostResourceInstance.getResourceDefinition()).andReturn(hostResourceDefinition).anyTimes();expect(hostResourceInstance.getQuery()).andReturn(hostComponentQuery).anyTimes();expect(hostResourceDefinition.getType()).andReturn(Resource.Type.Host);expect(hostComponentQuery.execute()).andReturn(hostComponentQueryResult);expect(hostComponentQueryResult.getResultTree()).andReturn(hostComponentResultNode);replay(m_controller,result,componentResourceInstance,componentResourceDefinition,hostResourceInstance,componentSchema,componentResource,hostComponentQuery,hostComponentQueryResult);QueryImpl query=new TestQuery(componentResourceInstance,result);query.execute();verify(m_controller,result,componentResourceInstance,componentResourceDefinition,hostResourceInstance,componentSchema,componentResource,hostComponentQuery,hostComponentQueryResult);assertEquals(1,tree.getChildren().size());TreeNode<Resource> componentNode=tree.getChild("Component:1");assertEquals("Component:1",componentNode.getName());assertEquals(componentResource,componentNode.getObject());assertEquals(1,componentNode.getChildren().size());assertSame(hostComponentResultNode,componentNode.getChild("host_components"));assertEquals("false",hostComponentResultNode.getProperty("isCollection"));}

  

  //todo: sub-resource with property and with sub-path

//  @Test
//  public void testAddProperty__invalidProperty() {
//
//  }

  private class TestQuery extends QueryImpl {

    private Result m_result;

    public TestQuery(ResourceInstance ResourceInstance, Result result) {
      super(ResourceInstance);
      m_result = result;
    }

    @Override
    ClusterController getClusterController() {
      return m_controller;
    }

    @Override
    Result createResult() {
      return m_result;
    }
  }
}
