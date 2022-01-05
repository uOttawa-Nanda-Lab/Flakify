@Test(expected=IllegalStateException.class) public void testDupeEntityColumnFamilyMapping(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyDupeCF1.class);
  cacheMgr.initializeCacheForClass(MyDupeCF2.class);
}
