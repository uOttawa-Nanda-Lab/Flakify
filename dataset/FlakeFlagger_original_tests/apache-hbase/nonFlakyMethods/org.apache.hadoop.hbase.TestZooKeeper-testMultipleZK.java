@Test public void testMultipleZK(){
  try {
    HTable localMeta=new HTable(conf,HConstants.META_TABLE_NAME);
    Configuration otherConf=HBaseConfiguration.create(conf);
    otherConf.set(HConstants.ZOOKEEPER_QUORUM,"127.0.0.1");
    HTable ipMeta=new HTable(conf,HConstants.META_TABLE_NAME);
    localMeta.exists(new Get(HConstants.LAST_ROW));
    ipMeta.exists(new Get(HConstants.LAST_ROW));
    assertFalse(HConnectionManager.getClientZooKeeperWatcher(conf).getZooKeeperWrapper() == HConnectionManager.getClientZooKeeperWatcher(otherConf).getZooKeeperWrapper());
    assertFalse(HConnectionManager.getConnection(conf).getZooKeeperWrapper().getQuorumServers().equals(HConnectionManager.getConnection(otherConf).getZooKeeperWrapper().getQuorumServers()));
  }
 catch (  Exception e) {
    e.printStackTrace();
    fail();
  }
}
