@Test public void testAddDropKeyspace() throws Exception {
  ColumnFamilyDefinition cfDef=HFactory.createColumnFamilyDefinition("DynKeyspace","DynCf");
  cassandraCluster.addKeyspace(new ThriftKsDef("DynKeyspace","org.apache.cassandra.locator.SimpleStrategy",1,Arrays.asList(cfDef)));
  String ksid2=cassandraCluster.dropKeyspace("DynKeyspace");
  assertNotNull(ksid2);
}
