@Test public void assertAddDataListener(){
  TreeCache treeCache=mock(TreeCache.class);
  @SuppressWarnings("unchecked") Listenable<TreeCacheListener> listeners=mock(Listenable.class);
  TreeCacheListener listener=mock(TreeCacheListener.class);
  when(treeCache.getListenable()).thenReturn(listeners);
  when(regCenter.getRawCache("/test_job")).thenReturn(treeCache);
  jobNodeStorage.addDataListener(listener);
  verify(listeners).addListener(listener);
}
