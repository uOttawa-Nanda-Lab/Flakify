@Test(expected=IllegalStateException.class) public void testGetColFamMapDefByClassSubclassNotOK(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyTestBean.class);
  NewBean obj=new NewBean();
  assertFalse(obj.getClass().isAnonymousClass());
  cacheMgr.getCfMapDef(obj.getClass(),true);
}
