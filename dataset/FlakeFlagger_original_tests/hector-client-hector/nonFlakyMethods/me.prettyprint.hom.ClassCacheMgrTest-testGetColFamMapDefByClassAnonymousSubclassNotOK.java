@Test(expected=IllegalStateException.class) public void testGetColFamMapDefByClassAnonymousSubclassNotOK(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyTestBean.class);
  assertTrue(tmplMap.get(2L).getClass().isAnonymousClass());
  cacheMgr.getCfMapDef(tmplMap.get(2L).getClass(),true);
}
