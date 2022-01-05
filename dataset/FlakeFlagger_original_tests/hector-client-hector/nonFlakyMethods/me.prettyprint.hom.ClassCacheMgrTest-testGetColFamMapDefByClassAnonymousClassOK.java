@Test public void testGetColFamMapDefByClassAnonymousClassOK(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(tmplMap.get(1L).getClass());
  assertTrue(tmplMap.get(1L).getClass().isAnonymousClass());
  CFMappingDef<?,Long> cfMapDef=cacheMgr.getCfMapDef(tmplMap.get(1L).getClass(),false);
  assertNotNull(cfMapDef);
  assertEquals(MyTestBean.class,cfMapDef.getClazz());
}
