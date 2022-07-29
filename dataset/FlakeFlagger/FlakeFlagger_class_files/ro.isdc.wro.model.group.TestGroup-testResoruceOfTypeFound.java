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
  @Test public void testResoruceOfTypeFound(){final Group group=new Group("group");final List<Resource> resources=new ArrayList<Resource>();resources.add(Resource.create("/some.css",ResourceType.CSS));group.setResources(resources);Assert.assertEquals(true,group.hasResourcesOfType(ResourceType.CSS));Assert.assertEquals(false,group.hasResourcesOfType(ResourceType.JS));}
}
