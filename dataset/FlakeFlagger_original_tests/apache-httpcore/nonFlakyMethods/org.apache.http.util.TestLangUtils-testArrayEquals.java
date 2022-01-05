@Test public void testArrayEquals(){
  Assert.assertFalse(LangUtils.equals(null,new Object[]{}));
  Assert.assertFalse(LangUtils.equals(new Object[]{},null));
  Assert.assertTrue(LangUtils.equals(new Object[]{},new Object[]{}));
  Assert.assertFalse(LangUtils.equals(new Object[]{new Integer(1),new Integer(2)},new Object[]{new Integer(1)}));
  Assert.assertFalse(LangUtils.equals(new Object[]{new Integer(1),new Integer(2)},new Object[]{new Integer(1),new Integer(3)}));
  Assert.assertTrue(LangUtils.equals(new Object[]{new Integer(1),new Integer(2)},new Object[]{new Integer(1),new Integer(2)}));
}
