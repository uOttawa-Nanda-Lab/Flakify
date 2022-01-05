@Test public void testExtends(){
  TextFrame frame=new TextFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof DataFrame);
}
