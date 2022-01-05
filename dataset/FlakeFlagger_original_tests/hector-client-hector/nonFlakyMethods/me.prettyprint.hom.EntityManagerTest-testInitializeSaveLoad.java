@Test public void testInitializeSaveLoad(){
  EntityManagerImpl em=new EntityManagerImpl(keyspace,"me.prettyprint.hom.beans");
  MyTestBean o1=new MyTestBean();
  o1.setBaseId(UUID.randomUUID());
  o1.setIntProp1(1);
  o1.setBoolProp1(Boolean.TRUE);
  o1.setLongProp1(123L);
  em.persist(o1);
  MyTestBean o2=em.find(MyTestBean.class,o1.getBaseId());
  assertEquals(o1.getBaseId(),o2.getBaseId());
  assertEquals(o1.getIntProp1(),o2.getIntProp1());
  assertEquals(o1.isBoolProp1(),o2.isBoolProp1());
  assertEquals(o1.getLongProp1(),o2.getLongProp1());
}
