@Test(expected=IllegalStateException.class) public void testBadPojo(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyBadTestBean.class);
}
