@Test(expected=IllegalStateException.class) public void testBadIdGetterSetter(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyMissingIdSetterBean.class);
}
