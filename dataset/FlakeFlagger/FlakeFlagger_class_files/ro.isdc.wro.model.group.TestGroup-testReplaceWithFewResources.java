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
  @Test public void testReplaceWithFewResources(){final Group group=new Group("group");final Resource resource=Resource.create("/static/*",ResourceType.JS);resource.setMinimize(false);group.addResource(resource);group.replace(resource,Arrays.asList(Resource.create("/static/one.js",ResourceType.JS),Resource.create("/static/two.js",ResourceType.JS)));Assert.assertEquals(2,group.getResources().size());Assert.assertEquals(resource.isMinimize(),group.getResources().get(0).isMinimize());}
}
