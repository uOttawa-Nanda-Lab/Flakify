@Test public void getAttributeAndSetAttributeTest(){
  header.setAttribute("test","test");
  Assert.assertEquals(header.getAttribute("test"),"test");
  Assert.assertTrue(header.hasAttribute("test"));
}
