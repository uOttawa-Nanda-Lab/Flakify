@Test public void turboFilterStopOnReset(){
  NOPTurboFilter nopTF=new NOPTurboFilter();
  nopTF.start();
  lc.addTurboFilter(nopTF);
  assertTrue(nopTF.isStarted());
  lc.reset();
  assertFalse(nopTF.isStarted());
}
