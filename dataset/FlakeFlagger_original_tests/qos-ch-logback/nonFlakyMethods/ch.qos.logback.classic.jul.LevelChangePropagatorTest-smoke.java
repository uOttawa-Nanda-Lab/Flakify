@Test public void smoke(){
  checkLevelChange("a",Level.INFO);
  checkLevelChange("a.b",Level.DEBUG);
}
