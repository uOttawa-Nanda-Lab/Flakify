/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Test;

import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test class for {@link Group}.
 *
 * @author Alex Objelean
 */
public class TestGroup {
  @Test public void shouldCollectCorrectNumberOfResourcesByType(){final Group group=new Group("group");group.addResource(Resource.create("1.js"));group.addResource(Resource.create("2.js"));group.addResource(Resource.create("3.js"));group.addResource(Resource.create("4.js"));group.addResource(Resource.create("5.js"));group.addResource(Resource.create("6.js"));group.addResource(Resource.create("1.css"));Assert.assertEquals(6,group.collectResourcesOfType(ResourceType.JS).getResources().size());Assert.assertEquals(1,group.collectResourcesOfType(ResourceType.CSS).getResources().size());}
}
