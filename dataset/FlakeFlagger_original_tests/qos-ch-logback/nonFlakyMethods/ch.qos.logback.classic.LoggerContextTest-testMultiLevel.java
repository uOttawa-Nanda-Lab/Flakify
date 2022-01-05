@Test public void testMultiLevel(){
  Logger wxyz=lc.getLogger("w.x.y.z");
  LoggerTestHelper.assertNameEquals(wxyz,"w.x.y.z");
  LoggerTestHelper.assertLevels(null,wxyz,Level.DEBUG);
  Logger wx=lc.getLogger("w.x");
  wx.setLevel(Level.INFO);
  LoggerTestHelper.assertNameEquals(wx,"w.x");
  LoggerTestHelper.assertLevels(Level.INFO,wx,Level.INFO);
  LoggerTestHelper.assertLevels(null,lc.getLogger("w.x.y"),Level.INFO);
  LoggerTestHelper.assertLevels(null,wxyz,Level.INFO);
}
