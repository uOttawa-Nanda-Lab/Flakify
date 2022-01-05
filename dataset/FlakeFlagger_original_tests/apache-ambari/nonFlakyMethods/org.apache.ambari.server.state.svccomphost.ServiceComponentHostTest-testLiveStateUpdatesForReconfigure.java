@Test public void testLiveStateUpdatesForReconfigure() throws Exception {
  ServiceComponentHost sch=createNewServiceComponentHost("HDFS","DATANODE","h1",false);
  ServiceComponentHostImpl impl=(ServiceComponentHostImpl)sch;
  sch.setDesiredState(State.INSTALLED);
  sch.setState(State.INSTALLED);
  Map<String,Config> desired=new HashMap<String,Config>();
  ConfigFactory configFactory=injector.getInstance(ConfigFactory.class);
  Cluster cluster=clusters.getCluster("C1");
  Config c1=configFactory.createNew(cluster,"type1",new HashMap<String,String>());
  Config c2=configFactory.createNew(cluster,"type2",new HashMap<String,String>());
  Config c3=configFactory.createNew(cluster,"type3",new HashMap<String,String>());
  Config c4v5=configFactory.createNew(cluster,"type4",new HashMap<String,String>());
  Config c2v3=configFactory.createNew(cluster,"type2",new HashMap<String,String>());
  c1.setVersionTag("v1");
  c2.setVersionTag("v1");
  c3.setVersionTag("v1");
  c4v5.setVersionTag("v5");
  c2v3.setVersionTag("v3");
  c1.persist();
  c2.persist();
  c3.persist();
  c4v5.persist();
  c2v3.persist();
  desired.put("type1",c1);
  desired.put("type2",c2);
  desired.put("type3",c3);
  impl.updateDesiredConfigs(desired);
  impl.persist();
  HostComponentDesiredStateEntityPK desiredPK=new HostComponentDesiredStateEntityPK();
  desiredPK.setClusterId(clusters.getCluster("C1").getClusterId());
  desiredPK.setServiceName("HDFS");
  desiredPK.setComponentName("DATANODE");
  desiredPK.setHostName("h1");
  HostComponentDesiredStateEntity desiredEntity=hostComponentDesiredStateDAO.findByPK(desiredPK);
  Assert.assertEquals(3,desiredEntity.getHostComponentDesiredConfigMappingEntities().size());
  Map<String,String> oldConfigs=new HashMap<String,String>();
  oldConfigs.put("type1","v1");
  oldConfigs.put("type2","v1");
  oldConfigs.put("type3","v1");
  HostComponentStateEntityPK primaryKey=new HostComponentStateEntityPK();
  primaryKey.setClusterId(clusters.getCluster("C1").getClusterId());
  primaryKey.setServiceName("HDFS");
  primaryKey.setComponentName("DATANODE");
  primaryKey.setHostName("h1");
  HostComponentStateEntity entity=hostComponentStateDAO.findByPK(primaryKey);
  Collection<HostComponentConfigMappingEntity> entities=entity.getHostComponentConfigMappingEntities();
  Assert.assertEquals(0,entities.size());
  impl.setConfigs(oldConfigs);
  impl.persist();
  Assert.assertEquals(3,impl.getConfigVersions().size());
  entity=hostComponentStateDAO.findByPK(primaryKey);
  entities=entity.getHostComponentConfigMappingEntities();
  Assert.assertEquals(3,entities.size());
  Map<String,String> newConfigs=new HashMap<String,String>();
  newConfigs.put("type1","v1");
  newConfigs.put("type2","v3");
  newConfigs.put("type4","v5");
  ServiceComponentHostStartEvent startEvent=new ServiceComponentHostStartEvent("DATANODE","h1",1,newConfigs);
  impl.handleEvent(startEvent);
  Assert.assertEquals(newConfigs.size(),impl.getConfigVersions().size());
  entity=hostComponentStateDAO.findByPK(primaryKey);
  entities=entity.getHostComponentConfigMappingEntities();
  Assert.assertEquals(3,entities.size());
  for (  HostComponentConfigMappingEntity e : entities) {
    LOG.debug("Found live config " + e.getConfigType() + ":"+ e.getVersionTag());
    Assert.assertTrue(e.getComponentName().equals("DATANODE") && e.getClusterId() == primaryKey.getClusterId() && e.getHostName().equals("h1") && e.getServiceName().equals("HDFS"));
    if (e.getConfigType().equals("type1")) {
      Assert.assertEquals("v1",e.getVersionTag());
    }
 else     if (e.getConfigType().equals("type2")) {
      Assert.assertEquals("v3",e.getVersionTag());
    }
 else     if (e.getConfigType().equals("type4")) {
      Assert.assertEquals("v5",e.getVersionTag());
    }
 else {
      fail("Found invalid type");
    }
  }
}
