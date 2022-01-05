@Test public void unsetField(){
  Settings settings=new Settings();
  assertEquals(-3,settings.getUploadBandwidth(-3));
}
