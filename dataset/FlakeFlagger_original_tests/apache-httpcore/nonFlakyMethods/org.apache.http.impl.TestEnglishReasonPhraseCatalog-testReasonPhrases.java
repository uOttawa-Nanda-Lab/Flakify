@Test public void testReasonPhrases() throws IllegalAccessException {
  Field[] publicFields=HttpStatus.class.getFields();
  Assert.assertNotNull(publicFields);
  Assert.assertTrue(publicFields.length > 0);
  for (int i=0; i < publicFields.length; i++) {
    final Field f=publicFields[i];
    final int modifiers=f.getModifiers();
    if ((f.getType() == int.class) && Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers)&& Modifier.isStatic(modifiers)) {
      final int iValue=f.getInt(null);
      final String text=EnglishReasonPhraseCatalog.INSTANCE.getReason(iValue,null);
      Assert.assertNotNull("text is null for HttpStatus." + f.getName(),text);
      Assert.assertTrue(text.length() > 0);
    }
  }
}
