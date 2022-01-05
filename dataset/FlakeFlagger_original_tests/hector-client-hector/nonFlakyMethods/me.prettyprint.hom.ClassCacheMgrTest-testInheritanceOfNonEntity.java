@Test public void testInheritanceOfNonEntity(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  CFMappingDef<MyPurpleTestBean,String> cfMapDef=cacheMgr.initializeCacheForClass(MyPurpleTestBean.class);
  assertEquals(2,cfMapDef.getAllProperties().size());
  assertNull(cfMapDef.getCfBaseMapDef());
  assertEquals(MyPurpleTestBean.class,cfMapDef.getClazz());
}
