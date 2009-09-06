package org.workcraft.plugins.stg.serialisation;

import org.w3c.dom.Element;
import org.workcraft.framework.exceptions.SerialisationException;
import org.workcraft.framework.serialisation.ReferenceProducer;
import org.workcraft.framework.serialisation.xml.ReferencingXMLSerialiser;
import org.workcraft.plugins.stg.ImplicitPlaceArc;

public class ImplicitArcSerialiser implements ReferencingXMLSerialiser {

	@Override
	public void serialise(Element element, Object object,
			ReferenceProducer internalReferences,
			ReferenceProducer externalReferences) throws SerialisationException {
		ImplicitPlaceArc arc = (ImplicitPlaceArc) object;

		element.setAttribute("first", internalReferences.getReference(arc.getFirst()));
		element.setAttribute("second", internalReferences.getReference(arc.getSecond()));

		element.setAttribute("refCon1", externalReferences.getReference(arc.getRefCon1()));
		element.setAttribute("refCon2", externalReferences.getReference(arc.getRefCon2()));
		element.setAttribute("refPlace", externalReferences.getReference(arc.getImplicitPlace()));
	}

	@Override
	public String getClassName() {
		return ImplicitPlaceArc.class.getName();
	}

}
