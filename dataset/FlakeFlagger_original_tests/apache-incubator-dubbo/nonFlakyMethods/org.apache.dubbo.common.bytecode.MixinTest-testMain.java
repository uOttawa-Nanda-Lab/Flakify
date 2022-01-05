@Test public void testMain() throws Exception {
  Mixin mixin=Mixin.mixin(new Class[]{I1.class,I2.class,I3.class},new Class[]{C1.class,C2.class});
  Object o=mixin.newInstance(new Object[]{new C1(),new C2()});
  assertEquals(o instanceof I1,true);
  assertEquals(o instanceof I2,true);
  assertEquals(o instanceof I3,true);
  ((I1)o).m1();
  ((I2)o).m2();
  ((I3)o).m3();
}
