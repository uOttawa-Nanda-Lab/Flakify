@Test public void testLevelChange(){
  checkLevelChange("a",Level.INFO);
  checkLevelChange("a.b",Level.ERROR);
  checkLevelChange("a.b.c",Level.DEBUG);
}
