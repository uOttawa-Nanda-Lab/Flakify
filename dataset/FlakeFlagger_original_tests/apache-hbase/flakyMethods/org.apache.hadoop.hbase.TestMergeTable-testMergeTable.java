/** 
 * Test case
 * @throws IOException
 */
public void testMergeTable() throws IOException {
  assertNotNull(dfsCluster);
  HMerge.merge(conf,dfsCluster.getFileSystem(),desc.getName());
}
