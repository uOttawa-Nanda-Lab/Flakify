@Test public void testGetResources() throws Exception {
  TestStreamProvider streamProvider=new TestStreamProvider();
  TestGangliaHostProvider hostProvider=new TestGangliaHostProvider();
  GangliaPropertyProvider propertyProvider=new GangliaHostComponentPropertyProvider(PropertyHelper.getGangliaPropertyIds(Resource.Type.HostComponent),streamProvider,hostProvider,CLUSTER_NAME_PROPERTY_ID,HOST_NAME_PROPERTY_ID,COMPONENT_NAME_PROPERTY_ID);
  Resource resource=new ResourceImpl(Resource.Type.HostComponent);
  resource.setProperty(HOST_NAME_PROPERTY_ID,"domU-12-31-39-0E-34-E1.compute-1.internal");
  resource.setProperty(COMPONENT_NAME_PROPERTY_ID,"DATANODE");
  Map<String,TemporalInfo> temporalInfoMap=new HashMap<String,TemporalInfo>();
  temporalInfoMap.put(PROPERTY_ID,new TemporalInfoImpl(10L,20L,1L));
  Request request=PropertyHelper.getReadRequest(Collections.singleton(PROPERTY_ID),temporalInfoMap);
  Assert.assertEquals(1,propertyProvider.populateResources(Collections.singleton(resource),request,null).size());
  Assert.assertEquals("http://ec2-23-23-71-42.compute-1.amazonaws.com/cgi-bin/rrd.py?c=HDPSlaves&h=domU-12-31-39-0E-34-E1.compute-1.internal&m=jvm.metrics.gcCount&s=10&e=20&r=1",streamProvider.getLastSpec());
  Assert.assertEquals(3,PropertyHelper.getProperties(resource).size());
  Assert.assertNotNull(resource.getPropertyValue(PROPERTY_ID));
}
