@Test public void testFilterReply(){
  setter.setProperty("filterReply","ACCEPT");
  assertEquals(FilterReply.ACCEPT,house.getFilterReply());
}
