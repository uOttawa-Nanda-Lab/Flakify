public void test1(){
  ControlLogger x=clc.getLogger("x");
  assertEquals("x",x.getName());
  assertEquals(clc.getRootLogger(),x.parent);
  ControlLogger abc=clc.getLogger("a.b.c");
  assertEquals("a.b.c",abc.getName());
  assertEquals(Level.DEBUG,abc.getEffectiveLevel());
}
