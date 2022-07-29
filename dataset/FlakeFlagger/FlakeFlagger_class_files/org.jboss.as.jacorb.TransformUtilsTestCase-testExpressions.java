package org.jboss.as.jacorb;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ValueExpression;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author <a href=mailto:tadamski@redhat.com>Tomasz Adamski</a>
 * */
public class TransformUtilsTestCase {

    @Test
    public void testExpressions() {
        ModelNode legacyModel = new ModelNode();
        legacyModel.get("name").set(new ValueExpression("${name}"));
        legacyModel.get("giop-minor-version").set(new ValueExpression("${giop.minor.version:2}"));
        ModelNode newModel = TransformUtils.transformModel(legacyModel);
        Assert.assertTrue(newModel.get("name").equals(new ModelNode(new ValueExpression("${name}"))));
        Assert.assertTrue(newModel.get("giop-version").equals(new ModelNode(new ValueExpression("${giop.minor.version:1.2}"))));
    }

}
