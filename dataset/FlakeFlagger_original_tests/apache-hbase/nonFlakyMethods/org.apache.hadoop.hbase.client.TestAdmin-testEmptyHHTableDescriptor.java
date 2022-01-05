/** 
 * HADOOP-2156
 * @throws IOException
 */
@Test(expected=IllegalArgumentException.class) public void testEmptyHHTableDescriptor() throws IOException {
  this.admin.createTable(new HTableDescriptor());
}
