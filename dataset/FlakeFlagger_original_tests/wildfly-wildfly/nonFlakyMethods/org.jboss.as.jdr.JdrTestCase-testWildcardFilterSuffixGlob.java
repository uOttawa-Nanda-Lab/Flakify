@Test public void testWildcardFilterSuffixGlob() throws Exception {
  VirtualFileFilter filter=Filters.wildcard("/this/is*");
  VirtualFile good=VFS.getChild("/this/is/a/test.txt");
  VirtualFile bad=VFS.getChild("/that/is/a/test.txt");
  VirtualFile wingood=VFS.getChild("/C:/this/is/a/test.txt");
  VirtualFile winbad=VFS.getChild("/C:/that/is/a/test.txt");
  assertTrue(filter.accepts(good));
  assertFalse(filter.accepts(bad));
  assertTrue(filter.accepts(wingood));
  assertFalse(filter.accepts(winbad));
}
