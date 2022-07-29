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
  @SuppressWarnings("unchecked") @Test public void testReplaceWithEmptyCollection(){final Group group=new Group("group");final Resource resource=Resource.create("/path",ResourceType.JS);group.addResource(resource);group.replace(resource,Collections.EMPTY_LIST);Assert.assertTrue(group.getResources().isEmpty());}
}
