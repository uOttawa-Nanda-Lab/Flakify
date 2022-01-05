@Test public void testGetColFamMapDefByClass(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  cacheMgr.initializeCacheForClass(MyTestBean.class);
  CFMappingDef<?,Long> cfMapDef=cacheMgr.getCfMapDef(MyTestBean.class,false);
  assertNotNull(cfMapDef);
  assertEquals(MyTestBean.class,cfMapDef.getClazz());
  assertEquals("did not find @Id properly","baseId",cfMapDef.getIdPropertyDef().getPropDesc().getName());
}
