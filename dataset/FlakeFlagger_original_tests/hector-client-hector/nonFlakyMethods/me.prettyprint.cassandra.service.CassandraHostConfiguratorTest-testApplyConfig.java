@Test public void testApplyConfig(){
  CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost:9170");
  cassandraHostConfigurator.setMaxActive(15);
  CassandraHost extraHost=new CassandraHost("localhost:9171");
  cassandraHostConfigurator.applyConfig(extraHost);
  assertEquals(15,extraHost.getMaxActive());
}
