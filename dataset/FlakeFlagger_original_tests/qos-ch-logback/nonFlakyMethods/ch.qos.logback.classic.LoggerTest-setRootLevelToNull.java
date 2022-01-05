@Test public void setRootLevelToNull(){
  try {
    root.setLevel(null);
    fail("The level of the root logger should not be settable to null");
  }
 catch (  IllegalArgumentException e) {
  }
}
