@Test public void testNewServiceComponentHost() throws AmbariException {
  createNewServiceComponentHost("HDFS","NAMENODE","h1",false);
  createNewServiceComponentHost("HDFS","HDFS_CLIENT","h1",true);
}
