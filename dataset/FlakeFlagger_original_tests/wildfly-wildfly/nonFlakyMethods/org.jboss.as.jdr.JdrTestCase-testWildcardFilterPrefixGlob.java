@Test public void testWildcardFilterPrefixGlob() throws Exception {
  VirtualFileFilter filter=Filters.wildcard("*.txt");
  VirtualFile good=VFS.getChild("/this/is/a/test.txt");
  VirtualFile bad=VFS.getChild("/this/is/a/test.xml");
  VirtualFile wingood=VFS.getChild("/C:/this/is/a/test.txt");
  VirtualFile winbad=VFS.getChild("/C:/this/is/a/test.xml");
  assertTrue(filter.accepts(good));
  assertFalse(filter.accepts(bad));
  assertTrue(filter.accepts(wingood));
  assertFalse(filter.accepts(winbad));
}
