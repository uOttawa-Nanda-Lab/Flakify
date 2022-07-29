/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.extensions.processor.support.linter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;


/**
 * @author Alex Objelean
 */
public class TestOptionsBuilder {
  private final OptionsBuilder optionsBuilder = new OptionsBuilder();


  @Test public void splitOptionsWithHiphen(){final String option="ids,adjoining-classes,box-model,box-sizing,compatible-vendor-prefixes,display-property-grouping,duplicate-background-images,duplicate-properties,empty-rules,errors,fallback-colors,floats,font-faces,font-sizes,gradients,import,important,known-properties,outline-none,overqualified-elements,qualified-headings,regex-selectors,rules-count,shorthand,text-indent,unique-headings,universal-selector,unqualified-attributes";final String[] result=optionsBuilder.splitOptions(option);assertEquals(28,result.length);}
}
