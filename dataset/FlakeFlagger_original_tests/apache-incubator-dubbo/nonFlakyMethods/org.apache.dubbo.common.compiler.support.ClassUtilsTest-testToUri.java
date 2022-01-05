@Test(expected=RuntimeException.class) public void testToUri(){
  ClassUtils.toURI("#xx_abc#hello");
}
