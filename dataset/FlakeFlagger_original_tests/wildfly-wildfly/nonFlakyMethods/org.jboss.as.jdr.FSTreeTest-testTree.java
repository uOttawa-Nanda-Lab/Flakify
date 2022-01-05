@Test public void testTree() throws Exception {
  FSTree tree=new FSTree(baseDirectory.getPath());
  assertEquals(tree.toString(),"FSTreeTest\n");
}
