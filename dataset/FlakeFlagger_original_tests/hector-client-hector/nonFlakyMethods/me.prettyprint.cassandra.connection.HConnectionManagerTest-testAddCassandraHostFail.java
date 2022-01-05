@Test public void testAddCassandraHostFail(){
  CassandraHost cassandraHost=new CassandraHost("127.0.0.1",9180);
  assertFalse(connectionManager.addCassandraHost(cassandraHost));
}
