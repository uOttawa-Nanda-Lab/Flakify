@Test public void testWildcardFilterAcceptAnything() throws Exception {
  VirtualFileFilter filter=Filters.wildcard("*");
  VirtualFile good=VFS.getChild("/this/is/a/test.txt");
  assertTrue(filter.accepts(good));
}
