@Test public void hasAttributeTest(){
  Assert.assertFalse(header.hasAttribute("test"));
  header.setAttribute("test","test");
  Assert.assertTrue(header.hasAttribute("test"));
}
