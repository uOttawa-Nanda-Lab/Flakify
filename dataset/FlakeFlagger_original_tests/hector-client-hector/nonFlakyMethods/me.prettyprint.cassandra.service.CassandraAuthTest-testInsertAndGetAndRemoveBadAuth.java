@Test public void testInsertAndGetAndRemoveBadAuth() throws IllegalArgumentException, NoSuchElementException, IllegalStateException, HNotFoundException, Exception {
  KeyspaceService keyspace=new KeyspaceServiceImpl("Keyspace1",new QuorumAllConsistencyLevelPolicy(),connectionManager,FailoverPolicy.ON_FAIL_TRY_ALL_AVAILABLE,user1CredentialsBad);
  try {
    ColumnPath cp=new ColumnPath("Standard1");
    cp.setColumn(bytes("testInsertAndGetAndRemove"));
    for (int i=0; i < 100; i++) {
      keyspace.insert("testInsertAndGetAndRemove_" + i,cp,StringSerializer.get().toByteBuffer("testInsertAndGetAndRemove_value_" + i));
    }
    fail("Should have gotten a wrapped AuthenticationException");
  }
 catch (  HectorException he) {
    assertTrue(he.getCause() instanceof AuthenticationException);
  }
}
