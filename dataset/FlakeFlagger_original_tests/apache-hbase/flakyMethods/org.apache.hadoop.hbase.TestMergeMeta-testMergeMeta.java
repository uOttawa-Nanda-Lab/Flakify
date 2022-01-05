/** 
 * test case
 * @throws IOException
 */
public void testMergeMeta() throws IOException {
  assertNotNull(dfsCluster);
  HMerge.merge(conf,dfsCluster.getFileSystem(),HConstants.META_TABLE_NAME);
}
