/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.wildfly.extension.datasources.agroal;

import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.PathElement;
import org.jboss.as.subsystem.test.AbstractSubsystemTest;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.*;

/**
 * Tests all management expects for subsystem, parsing, marshaling, model definition and other
 * Here is an example that allows you a fine grained controller over what is tested and how. So it can give you ideas what can be done and tested.
 * If you have no need for advanced testing of subsystem you look at {@link SubsystemBaseParsingTestCase} that testes same stuff but most of the code
 * is hidden inside of test harness
 *
 * @author <a href="lbarreiro@redhat.com">Luis Barreiro</a>
 */
public class SubsystemParsingTestCase extends AbstractSubsystemTest {

    /**
	 * Starts a controller with a given subsystem xml and then checks that a second controller started with the xml marshaled from the first one results in the same model
	 */@Test public void testParseAndMarshalModel() throws Exception{String subsystemXml="<subsystem xmlns=\"" + AgroalNamespace.CURRENT.getUriString() + "\"/>";KernelServices servicesA=super.createKernelServicesBuilder(null).setSubsystemXml(subsystemXml).build();ModelNode modelA=servicesA.readWholeModel();String marshaled=servicesA.getPersistedSubsystemXml();KernelServices servicesB=super.createKernelServicesBuilder(null).setSubsystemXml(marshaled).build();ModelNode modelB=servicesB.readWholeModel();super.compare(modelA,modelB);}
}
