@Test public void testExtends(){
  ContinuousFrame frame=new ContinuousFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof DataFrame);
}
