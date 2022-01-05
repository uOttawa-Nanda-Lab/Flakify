private void testAliases(KernelServices services,ModelNode noAliasModel,ModelNode aliasModel,String[] targetAddr,String[] aliasAddr) throws Exception {
  Assert.assertTrue(noAliasModel.get(targetAddr).isDefined());
  Assert.assertFalse(noAliasModel.get(aliasAddr).isDefined());
  Assert.assertEquals(aliasModel.get(targetAddr),aliasModel.get(aliasAddr));
}
