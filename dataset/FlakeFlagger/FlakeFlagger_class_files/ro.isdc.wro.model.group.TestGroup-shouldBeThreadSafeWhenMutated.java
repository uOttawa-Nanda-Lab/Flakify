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
  @Test public void shouldBeThreadSafeWhenMutated() throws Exception{final Group group=new Group("group");final List<Resource> resources=new ArrayList<Resource>();final Resource r1=Resource.create("/some.css",ResourceType.CSS);resources.add(r1);WroTestUtils.runConcurrently(new Callable<Void>(){public Void call() throws Exception{if (new Random().nextBoolean()){group.setResources(resources);} else {group.addResource(r1);group.replace(r1,resources);}return null;}});}
}
