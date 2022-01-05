@Test public void removeAttributeTest(){
  header.setAttribute("test","test");
  Assert.assertEquals(header.getAttribute("test"),"test");
  header.removeAttribute("test");
  Assert.assertFalse(header.hasAttribute("test"));
}
