package ro.isdc.wro.model.resource.processor.support;

import org.junit.Test;


/**
 * Test for less specific import statements.
 *
 * @author Alex Objelean
 */
public class TestLessCssImportInspector
    extends TestCssImportInspector {
  @Test public void shouldDetectImportOnceStatement(){assertHasImport("@import-once url('import/blue-theme.less'");assertHasImport("@import-once 'import/blue-theme.less'");}

}
