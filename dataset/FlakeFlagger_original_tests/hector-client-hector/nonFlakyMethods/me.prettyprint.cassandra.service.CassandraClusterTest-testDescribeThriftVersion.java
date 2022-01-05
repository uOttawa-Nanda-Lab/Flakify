/** 
 * This will need to be updated as we update the Thrift API, but probably a good sanity check
 */
@Test public void testDescribeThriftVersion() throws Exception {
  assertEquals("19.4.0",cassandraCluster.describeThriftVersion());
}
