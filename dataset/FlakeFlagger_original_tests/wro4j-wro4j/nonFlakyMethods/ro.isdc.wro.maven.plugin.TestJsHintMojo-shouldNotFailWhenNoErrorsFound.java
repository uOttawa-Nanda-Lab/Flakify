/** 
 * Checks that build doesn't fail when the failFast is true and there is no resources to be processed.
 */
@Test public void shouldNotFailWhenNoErrorsFound() throws Exception {
  final JsHintMojo jsHintMojo=(JsHintMojo)getMojo();
  jsHintMojo.setFailThreshold(0);
  jsHintMojo.setFailFast(false);
  jsHintMojo.setIgnoreMissingResources(true);
  jsHintMojo.setTargetGroups("invalidWildcardResource");
  getMojo().execute();
}
