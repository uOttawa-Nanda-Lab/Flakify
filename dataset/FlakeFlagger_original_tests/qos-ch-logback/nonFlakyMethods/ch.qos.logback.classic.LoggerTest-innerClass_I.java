@Test public void innerClass_I(){
  root.setLevel(Level.DEBUG);
  Logger a=lc.getLogger("a");
  a.setLevel(Level.INFO);
  Logger a_b=lc.getLogger("a$b");
  assertEquals(Level.INFO,a_b.getEffectiveLevel());
}
