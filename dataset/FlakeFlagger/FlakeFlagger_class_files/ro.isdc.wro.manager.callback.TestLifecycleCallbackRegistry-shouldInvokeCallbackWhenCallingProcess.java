/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.manager.callback;

import java.io.StringWriter;
import java.util.concurrent.Callable;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.http.support.DelegatingServletOutputStream;
import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.util.ObjectFactory;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;

/**
 * @author Alex Objelean
 */
public class TestLifecycleCallbackRegistry {
  private LifecycleCallbackRegistry registry;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    registry = new LifecycleCallbackRegistry();
  }

  private ObjectFactory<LifecycleCallback> factoryFor(final LifecycleCallback callback) {
    return new ObjectFactory<LifecycleCallback>() {
      public LifecycleCallback create() {
        return callback;
      }
    };
  }

  /**
 * TODO: Simplify the test and move common usage to utility method.
 */@Test public void shouldInvokeCallbackWhenCallingProcess() throws Exception{final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);final HttpServletResponse response=Mockito.mock(HttpServletResponse.class);;Mockito.when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(new WriterOutputStream(new StringWriter())));Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer(""));Mockito.when(request.getRequestURI()).thenReturn("");Mockito.when(request.getServletPath()).thenReturn("");Context.set(Context.webContext(request,response,Mockito.mock(FilterConfig.class)));final LifecycleCallback callback=Mockito.mock(LifecycleCallback.class);final String groupName="group";final GroupExtractor groupExtractor=Mockito.mock(GroupExtractor.class);Mockito.when(groupExtractor.getGroupName(Mockito.any(HttpServletRequest.class))).thenReturn(groupName);Mockito.when(groupExtractor.getResourceType(Mockito.any(HttpServletRequest.class))).thenReturn(ResourceType.JS);final Group group=new Group(groupName);group.addResource(Resource.create("classpath:1.js"));final WroModelFactory modelFactory=WroUtil.factoryFor(new WroModel().addGroup(group));final WroManagerFactory managerFactory=new BaseWroManagerFactory().setGroupExtractor(groupExtractor).setModelFactory(modelFactory);final WroManager manager=managerFactory.create();manager.registerCallback(new ObjectFactory<LifecycleCallback>(){public LifecycleCallback create(){return callback;}});manager.process();Mockito.verify(callback).onBeforeModelCreated();Mockito.verify(callback).onAfterModelCreated();Mockito.verify(callback,Mockito.atLeastOnce()).onBeforePreProcess();Mockito.verify(callback,Mockito.atLeastOnce()).onAfterPreProcess();Mockito.verify(callback).onBeforeMerge();Mockito.verify(callback).onAfterMerge();Mockito.verify(callback).onProcessingComplete();}
}
