@Test public void innerClass_II(){
  root.setLevel(Level.DEBUG);
  Logger a=lc.getLogger(this.getClass());
  a.setLevel(Level.INFO);
  Logger a_b=lc.getLogger(new Inner().getClass());
  assertEquals(Level.INFO,a_b.getEffectiveLevel());
}
