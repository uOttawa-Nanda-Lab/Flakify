@Test public void testExtends(){
  CloseFrame frame=new CloseFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof ControlFrame);
}
