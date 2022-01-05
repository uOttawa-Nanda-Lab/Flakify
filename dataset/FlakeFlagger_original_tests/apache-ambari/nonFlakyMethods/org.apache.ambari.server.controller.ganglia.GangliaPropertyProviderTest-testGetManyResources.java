@Test public void testGetManyResources() throws Exception {
  TestStreamProvider streamProvider=new TestStreamProvider();
  TestGangliaHostProvider hostProvider=new TestGangliaHostProvider();
  GangliaPropertyProvider propertyProvider=new GangliaHostPropertyProvider(PropertyHelper.getGangliaPropertyIds(Resource.Type.Host),streamProvider,hostProvider,CLUSTER_NAME_PROPERTY_ID,HOST_NAME_PROPERTY_ID);
  Set<Resource> resources=new HashSet<Resource>();
  Resource resource=new ResourceImpl(Resource.Type.Host);
  resource.setProperty(HOST_NAME_PROPERTY_ID,"domU-12-31-39-0E-34-E1.compute-1.internal");
  resources.add(resource);
  resource=new ResourceImpl(Resource.Type.Host);
  resource.setProperty(HOST_NAME_PROPERTY_ID,"domU-12-31-39-0E-34-E2.compute-1.internal");
  resources.add(resource);
  resource=new ResourceImpl(Resource.Type.Host);
  resource.setProperty(HOST_NAME_PROPERTY_ID,"domU-12-31-39-0E-34-E3.compute-1.internal");
  resources.add(resource);
  Map<String,TemporalInfo> temporalInfoMap=new HashMap<String,TemporalInfo>();
  temporalInfoMap.put(PROPERTY_ID,new TemporalInfoImpl(10L,20L,1L));
  Request request=PropertyHelper.getReadRequest(Collections.singleton(PROPERTY_ID),temporalInfoMap);
  Assert.assertEquals(3,propertyProvider.populateResources(resources,request,null).size());
  Assert.assertEquals("http://ec2-23-23-71-42.compute-1.amazonaws.com/cgi-bin/rrd.py?c=HDPSlaves&h=domU-12-31-39-0E-34-E3.compute-1.internal,domU-12-31-39-0E-34-E1.compute-1.internal,domU-12-31-39-0E-34-E2.compute-1.internal&m=jvm.metrics.gcCount&s=10&e=20&r=1",streamProvider.getLastSpec());
  for (  Resource res : resources) {
    Assert.assertEquals(2,PropertyHelper.getProperties(res).size());
    Assert.assertNotNull(res.getPropertyValue(PROPERTY_ID));
  }
}
