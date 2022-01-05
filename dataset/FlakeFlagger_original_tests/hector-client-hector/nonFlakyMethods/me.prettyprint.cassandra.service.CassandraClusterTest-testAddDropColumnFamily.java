@Test public void testAddDropColumnFamily() throws Exception {
  ColumnFamilyDefinition cfDef=HFactory.createColumnFamilyDefinition("Keyspace1","DynCf");
  cassandraCluster.addColumnFamily(cfDef);
  String cfid2=cassandraCluster.dropColumnFamily("Keyspace1","DynCf");
  assertNotNull(cfid2);
}
