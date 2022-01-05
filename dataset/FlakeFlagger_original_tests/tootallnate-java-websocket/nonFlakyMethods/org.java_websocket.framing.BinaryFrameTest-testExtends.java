@Test public void testExtends(){
  BinaryFrame frame=new BinaryFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof DataFrame);
}
