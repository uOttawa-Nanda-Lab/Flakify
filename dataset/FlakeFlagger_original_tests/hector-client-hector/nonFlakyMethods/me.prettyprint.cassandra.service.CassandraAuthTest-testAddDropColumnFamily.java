@Test public void testAddDropColumnFamily() throws Exception {
  ThriftCluster cassandraCluster=new ThriftCluster("Test Cluster",cassandraHostConfigurator,user1Credentials);
  ColumnFamilyDefinition cfDef=HFactory.createColumnFamilyDefinition("Keyspace1","DynCf");
  cassandraCluster.addColumnFamily(cfDef);
  String cfid2=cassandraCluster.dropColumnFamily("Keyspace1","DynCf");
  assertNotNull(cfid2);
}
