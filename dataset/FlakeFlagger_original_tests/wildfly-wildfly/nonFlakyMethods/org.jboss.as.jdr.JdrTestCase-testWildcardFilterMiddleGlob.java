@Test public void testWildcardFilterMiddleGlob() throws Exception {
  VirtualFileFilter filter=Filters.wildcard("/this*test.txt");
  VirtualFile good=VFS.getChild("/this/is/a/test.txt");
  VirtualFile bad1=VFS.getChild("/that/is/a/test.txt");
  VirtualFile bad2=VFS.getChild("/this/is/a/test.xml");
  VirtualFile win=VFS.getChild("/C:/this/is/a/test.txt");
  VirtualFile winbad=VFS.getChild("/C:/this/is/a/test.xml");
  assertTrue(filter.accepts(good));
  assertTrue(filter.accepts(win));
  assertFalse(filter.accepts(bad1));
  assertFalse(filter.accepts(bad2));
  assertFalse(filter.accepts(winbad));
}
