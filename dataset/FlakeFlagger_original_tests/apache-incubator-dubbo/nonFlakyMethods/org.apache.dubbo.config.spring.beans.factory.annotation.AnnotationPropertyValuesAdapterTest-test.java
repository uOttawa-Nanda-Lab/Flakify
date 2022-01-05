@Test public void test(){
  MockEnvironment mockEnvironment=new MockEnvironment();
  mockEnvironment.setProperty("version","1.0.0");
  mockEnvironment.setProperty("url","   dubbo://localhost:12345");
  Field field=ReflectionUtils.findField(TestBean.class,"demoService");
  Reference reference=AnnotationUtils.getAnnotation(field,Reference.class);
  AnnotationPropertyValuesAdapter propertyValues=new AnnotationPropertyValuesAdapter(reference,mockEnvironment);
  ReferenceBean referenceBean=new ReferenceBean();
  DataBinder dataBinder=new DataBinder(referenceBean);
  dataBinder.setDisallowedFields("application","module","consumer","monitor","registry");
  DefaultConversionService conversionService=new DefaultConversionService();
  conversionService.addConverter(new Converter<String[],String>(){
    @Override public String convert(    String[] source){
      return arrayToCommaDelimitedString(source);
    }
  }
);
  conversionService.addConverter(new Converter<String[],Map<String,String>>(){
    @Override public Map<String,String> convert(    String[] source){
      return CollectionUtils.toStringMap(source);
    }
  }
);
  dataBinder.setConversionService(conversionService);
  dataBinder.bind(propertyValues);
  Assert.assertEquals(DemoService.class,referenceBean.getInterfaceClass());
  Assert.assertEquals("org.apache.dubbo.config.spring.api.DemoService",referenceBean.getInterface());
  Assert.assertEquals("1.0.0",referenceBean.getVersion());
  Assert.assertEquals("group",referenceBean.getGroup());
  Assert.assertEquals("dubbo://localhost:12345",referenceBean.getUrl());
  Assert.assertEquals("client",referenceBean.getClient());
  Assert.assertEquals(true,referenceBean.isGeneric());
  Assert.assertEquals(true,referenceBean.isInjvm());
  Assert.assertEquals(false,referenceBean.isCheck());
  Assert.assertEquals(true,referenceBean.isInit());
  Assert.assertEquals(true,referenceBean.getLazy());
  Assert.assertEquals(true,referenceBean.getStubevent());
  Assert.assertEquals("reconnect",referenceBean.getReconnect());
  Assert.assertEquals(true,referenceBean.getSticky());
  Assert.assertEquals("javassist",referenceBean.getProxy());
  Assert.assertEquals("stub",referenceBean.getStub());
  Assert.assertEquals("failover",referenceBean.getCluster());
  Assert.assertEquals(Integer.valueOf(1),referenceBean.getConnections());
  Assert.assertEquals(Integer.valueOf(1),referenceBean.getCallbacks());
  Assert.assertEquals("onconnect",referenceBean.getOnconnect());
  Assert.assertEquals("ondisconnect",referenceBean.getOndisconnect());
  Assert.assertEquals("owner",referenceBean.getOwner());
  Assert.assertEquals("layer",referenceBean.getLayer());
  Assert.assertEquals(Integer.valueOf(1),referenceBean.getRetries());
  Assert.assertEquals("random",referenceBean.getLoadbalance());
  Assert.assertEquals(true,referenceBean.isAsync());
  Assert.assertEquals(Integer.valueOf(1),referenceBean.getActives());
  Assert.assertEquals(true,referenceBean.getSent());
  Assert.assertEquals("mock",referenceBean.getMock());
  Assert.assertEquals("validation",referenceBean.getValidation());
  Assert.assertEquals(Integer.valueOf(2),referenceBean.getTimeout());
  Assert.assertEquals("cache",referenceBean.getCache());
  Assert.assertEquals("default,default",referenceBean.getFilter());
  Assert.assertEquals("default,default",referenceBean.getListener());
  Assert.assertEquals("protocol",referenceBean.getProtocol());
  Map<String,String> data=new LinkedHashMap<String,String>();
  data.put("key1","value1");
  Assert.assertEquals(data,referenceBean.getParameters());
  Assert.assertEquals(null,referenceBean.getApplication());
  Assert.assertEquals(null,referenceBean.getModule());
  Assert.assertEquals(null,referenceBean.getConsumer());
  Assert.assertEquals(null,referenceBean.getMonitor());
  Assert.assertEquals(null,referenceBean.getRegistry());
}
