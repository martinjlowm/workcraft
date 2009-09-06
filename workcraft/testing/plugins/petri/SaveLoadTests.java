package org.workcraft.testing.plugins.petri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.workcraft.dom.Model;
import org.workcraft.dom.Node;
import org.workcraft.dom.math.MathConnection;
import org.workcraft.dom.math.MathNode;
import org.workcraft.dom.visual.Movable;
import org.workcraft.dom.visual.MovableHelper;
import org.workcraft.framework.Framework;
import org.workcraft.plugins.petri.PetriNet;
import org.workcraft.plugins.petri.Place;
import org.workcraft.plugins.petri.Transition;
import org.workcraft.plugins.petri.VisualPetriNet;
import org.workcraft.util.Hierarchy;

public class SaveLoadTests {

	String testDataMathModel = "3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822207374616e64616c6f6e653d226e6f223f3e0d0a3c776f726b63726166742076657273696f6e3d22322e646576223e0d0a20203c6d6f64656c20636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e50657472694e6574223e0d0a202020203c4d6174684d6f64656c207469746c653d22223e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e5472616e736974696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223422206c6162656c3d227472616e7332222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e5472616e736974696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223322206c6162656c3d227472616e7331222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223222206c6162656c3d22706c61636533222f3e0d0a20202020202020203c506c61636520746f6b656e733d2232222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223122206c6162656c3d22706c61636532222f3e0d0a20202020202020203c506c61636520746f6b656e733d2233222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223022206c6162656c3d22706c61636531222f3e0d0a20202020202020203c506c61636520746f6b656e733d2235222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223822206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223222207365636f6e643d2234222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223722206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223322207365636f6e643d2232222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223622206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223322207365636f6e643d2231222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223522206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223022207365636f6e643d2233222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a202020203c2f4d6174684d6f64656c3e0d0a20203c2f6d6f64656c3e0d0a3c2f776f726b63726166743e0d0a";
	String testDataVisualModel = "3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822207374616e64616c6f6e653d226e6f223f3e0d0a3c776f726b63726166742076657273696f6e3d22322e646576223e0d0a20203c6d6f64656c20636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e50657472694e6574223e0d0a202020203c4d6174684d6f64656c207469746c653d22223e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e5472616e736974696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223422206c6162656c3d227472616e7332222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e5472616e736974696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223322206c6162656c3d227472616e7331222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223222206c6162656c3d22706c61636533222f3e0d0a20202020202020203c506c61636520746f6b656e733d2232222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223122206c6162656c3d22706c61636532222f3e0d0a20202020202020203c506c61636520746f6b656e733d2233222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e7420636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e506c616365223e0d0a20202020202020203c4d6174684e6f64652049443d223022206c6162656c3d22706c61636531222f3e0d0a20202020202020203c506c61636520746f6b656e733d2235222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223822206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223222207365636f6e643d2234222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223722206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223322207365636f6e643d2232222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223622206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223322207365636f6e643d2231222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e436f6e6e656374696f6e223e0d0a20202020202020203c4d6174684e6f64652049443d223522206c6162656c3d22222f3e0d0a20202020202020203c436f6e6e656374696f6e2066697273743d223022207365636f6e643d2233222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a202020203c2f4d6174684d6f64656c3e0d0a20203c2f6d6f64656c3e0d0a20203c76697375616c2d6d6f64656c20636c6173733d226f72672e776f726b63726166742e706c7567696e732e70657472692e56697375616c50657472694e6574223e0d0a202020203c56697375616c4d6f64656c3e0d0a2020202020203c636f6d706f6e656e74207265663d2232223e0d0a20202020202020203c56697375616c5472616e73666f726d61626c654e6f646520583d22372e3330383738313930373033323930392220593d22342e313030383038313134393232303137222f3e0d0a20202020202020203c56697375616c436f6d706f6e656e742066696c6c436f6c6f723d22236666666666662220666f726567726f756e64436f6c6f723d22233022206c6162656c436f6c6f723d222330222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e74207265663d2231223e0d0a20202020202020203c56697375616c5472616e73666f726d61626c654e6f646520583d22322e3037373134383431333039373137312220593d22332e33323731373035353935393531313138222f3e0d0a20202020202020203c56697375616c436f6d706f6e656e742066696c6c436f6c6f723d22236666666666662220666f726567726f756e64436f6c6f723d22233022206c6162656c436f6c6f723d222330222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e74207265663d2233223e0d0a20202020202020203c56697375616c5472616e73666f726d61626c654e6f646520583d22392e3637373535393039343234313230372220593d22302e303631313731383232363537363133303036222f3e0d0a20202020202020203c56697375616c436f6d706f6e656e742066696c6c436f6c6f723d22236666666666662220666f726567726f756e64436f6c6f723d22233022206c6162656c436f6c6f723d222330222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e74207265663d2230223e0d0a20202020202020203c56697375616c5472616e73666f726d61626c654e6f646520583d22392e3633373034373937303233323037372220593d22392e333938363533383837383139303938222f3e0d0a20202020202020203c56697375616c436f6d706f6e656e742066696c6c436f6c6f723d22236666666666662220666f726567726f756e64436f6c6f723d22233022206c6162656c436f6c6f723d222330222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6d706f6e656e74207265663d2234223e0d0a20202020202020203c56697375616c5472616e73666f726d61626c654e6f646520583d22392e3437313934393137363633313933382220593d22392e333730383231343838393539363937222f3e0d0a20202020202020203c56697375616c436f6d706f6e656e742066696c6c436f6c6f723d22236666666666662220666f726567726f756e64436f6c6f723d22233022206c6162656c436f6c6f723d222330222f3e0d0a2020202020203c2f636f6d706f6e656e743e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e76697375616c2e56697375616c436f6e6e656374696f6e223e0d0a20202020202020203c56697375616c436f6e6e656374696f6e206172726f774c656e6774683d22302e3422206172726f7757696474683d22302e313522206c696e6557696474683d22302e3032222072656649443d22382220747970653d22504f4c594c494e45222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e76697375616c2e56697375616c436f6e6e656374696f6e223e0d0a20202020202020203c56697375616c436f6e6e656374696f6e206172726f774c656e6774683d22302e3422206172726f7757696474683d22302e313522206c696e6557696474683d22302e3032222072656649443d22362220747970653d22504f4c594c494e45222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e76697375616c2e56697375616c436f6e6e656374696f6e223e0d0a20202020202020203c56697375616c436f6e6e656374696f6e206172726f774c656e6774683d22302e3422206172726f7757696474683d22302e313522206c696e6557696474683d22302e3032222072656649443d22372220747970653d22504f4c594c494e45222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a2020202020203c636f6e6e656374696f6e20636c6173733d226f72672e776f726b63726166742e646f6d2e76697375616c2e56697375616c436f6e6e656374696f6e223e0d0a20202020202020203c56697375616c436f6e6e656374696f6e206172726f774c656e6774683d22302e3422206172726f7757696474683d22302e313522206c696e6557696474683d22302e3032222072656649443d22352220747970653d22504f4c594c494e45222f3e0d0a2020202020203c2f636f6e6e656374696f6e3e0d0a202020203c2f56697375616c4d6f64656c3e0d0a20203c2f76697375616c2d6d6f64656c3e0d0a3c2f776f726b63726166743e0d0a";

	InputStream stringToStream(String string) throws IOException
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(bytes);
		writer.write(string);
		writer.close();

		return new ByteArrayInputStream(bytes.toByteArray());
	}

	@Test
	public void TestMathModelLoad() throws Exception
	{
		Framework framework = new Framework();
		framework.getPluginManager().loadManifest();

		Model model = framework.load(new Base16Reader(testDataMathModel));
		PetriNet petri = (PetriNet)model;

		Assert.assertNotNull(petri);

		assertPetriEquals(petri, buildSamplePetri());
	}

	@Test
	public void TestVisualModelLoad() throws Exception
	{
		Framework framework = new Framework();
		framework.getPluginManager().loadManifest();

		Model model = framework.load(new Base16Reader(testDataVisualModel));
		VisualPetriNet petriVisual = (VisualPetriNet)model;
		PetriNet petri = (PetriNet)petriVisual.getMathModel();

		Assert.assertNotNull(petriVisual);
		Assert.assertNotNull(petri);

		VisualPetriNet sample = buildSampleVisualPetri();
		assertPetriEquals(petri, (PetriNet)sample.getMathModel());
		assertVisualPetriEquals(petriVisual, sample);
	}

	private void assertVisualPetriEquals(VisualPetriNet petriVisual,
			VisualPetriNet sample) {
		// TODO Auto-generated method stub

	}

	@Test
	public void GenerateSample() throws Exception
	{
		Framework f = new Framework();
		f.getPluginManager().loadManifest();
		StringWriter writer = new StringWriter();
		f.save(buildSamplePetri(), new Base16Writer(writer));
		System.err.print("\"");
		System.err.print(writer.toString());
		System.err.println("\"");
	}

	private Collection<MathNode> getComponents(PetriNet net)
	{
		ArrayList<MathNode> result = new ArrayList<MathNode>(net.getTransitions());
		result.addAll(net.getPlaces());
		return result;
	}

	private Collection<MathConnection> getConnections(PetriNet net)
	{
		return Hierarchy.getChildrenOfType(net.getRoot(), MathConnection.class);
	}

	private void assertPetriEquals(PetriNet expected, PetriNet actual) {
		Finder<MathNode> componentFinder = new Finder<MathNode>(getComponents(actual), new ComponentLabelExtractor());

		Assert.assertEquals(getComponents(expected).size(), getComponents(actual).size());
		for(MathNode component : getComponents(expected))
			assertComponentEquals(component, componentFinder.getMatching(component));

		Finder<MathConnection> connectionFinder = new Finder<MathConnection>(getConnections(actual), new ConnectionByComponentsIdentifier(new ComponentLabelExtractor()));
		Assert.assertEquals(getConnections(expected).size(), getConnections(actual).size());
		for(MathConnection connection : getConnections(expected))
			assertConnectionEquals(connection, connectionFinder.getMatching(connection));
	}

	private void assertConnectionEquals(MathConnection expected, MathConnection actual) {
		assertComponentEquals(expected.getFirst(), actual.getFirst());
		assertComponentEquals(expected.getSecond(), actual.getSecond());
	}

	int toHexchar(int ch)
	{
		if(ch<10)
			return '0'+ch;
		else
			return 'a'+ch-10;
	}

	int fromHexchar(int ch)
	{
		if(ch <= 'f' && ch >= 'a')
			return ch-'a'+10;
		if(ch <= 'F' && ch >= 'A')
			return ch-'A'+10;
		if(ch <= '9' && ch >= '0')
			return ch-'0';
		throw new RuntimeException("Hex parse error");
	}

	class Base16Writer extends OutputStream
	{
		private final Writer output;

		public Base16Writer(Writer output)
		{
			this.output = output;
		}

		@Override
		public void write(int b) throws IOException {
			output.write(toHexchar(b/16));
			output.write(toHexchar(b%16));
		}
	}

	class Base16Reader extends InputStream
	{
		private final Reader stringReader;

		Base16Reader(String string)
		{
			this(new StringReader(string));
		}
		Base16Reader(Reader stringReader)
		{
			this.stringReader = stringReader;
		}

		@Override
		public int read() throws IOException {
			int ch1 = stringReader.read();
			if(ch1 == -1)
				return -1;
			int ch2 = stringReader.read();
			if(ch2 == -1)
				throw new RuntimeException("Length must be even");

			return fromHexchar(ch1)*16+fromHexchar(ch2);
		}
	}

	public void assertComponentEquals(MathNode node, MathNode node2)
	{
		if(node == null)
		{
			Assert.assertNull(node2);
			return;
		}
		Assert.assertNotNull(node2);

		Class<? extends Node> type = node.getClass();
		Assert.assertEquals(type, node2.getClass());

		Assert.assertEquals(node.getLabel(), node2.getLabel());

		if(type == Transition.class)
			assertTransitionEquals((Transition)node, (Transition)node2);
		if(type == Place.class)
			assertPlaceEquals((Place)node, (Place)node2);
	}

	private void assertTransitionEquals(Transition expected, Transition actual) {
	}

	private void assertPlaceEquals(Place expected, Place actual) {
		Assert.assertEquals(expected.getTokens(), actual.getTokens());
	}

	private PetriNet buildSamplePetri() throws Exception
	{
		return (PetriNet) buildSampleVisualPetri().getMathModel();
	}

	private VisualPetriNet buildSampleVisualPetri() throws Exception {
		PetriNet petri = new PetriNet();

		Place place1 = new Place();
		place1.setTokens(5);
		Place place2 = new Place();
		place2.setTokens(3);
		Place place3 = new Place();
		place3.setTokens(2);
		petri.add(place1);
		place1.setLabel("place1");
		petri.add(place2);
		place2.setLabel("place2");
		petri.add(place3);
		place3.setLabel("place3");

		Transition trans1 = new Transition();
		trans1.setLabel("trans1");
		Transition trans2 = new Transition();
		trans2.setLabel("trans2");

		petri.add(trans1);
		petri.add(trans2);

		petri.connect(place1, trans1);
		petri.connect(trans1, place2);
		petri.connect(trans1, place3);
		petri.connect(place3, trans2);


		VisualPetriNet visual = new VisualPetriNet(petri);
/*		VisualPlace vp1 = new VisualPlace(place1);
		VisualPlace vp2 = new VisualPlace(place2);
		VisualPlace vp3 = new VisualPlace(place3);

		VisualTransition vt1 = new VisualTransition(trans1);
		VisualTransition vt2 = new VisualTransition(trans2);

		VisualGroup gr1 = new VisualGroup();
		VisualGroup gr2 = new VisualGroup();
		VisualGroup gr3 = new VisualGroup();

		//todo: add components

		gr1.add(vp1);
		gr1.add(vt2);

		gr2.add(gr1);
		gr2.add(vp2);

		gr3.add(vp3);
		gr3.add(vt2);

		visual.getRoot().add(gr2);
		visual.getRoot().add(gr3);

		VisualConnection vc1 = new VisualConnection(con1, vp1, vt1);
		VisualConnection vc2 = new VisualConnection(con1, vt1, vp2);
		VisualConnection vc3 = new VisualConnection(con1, vt1, vp3);
		VisualConnection vc4 = new VisualConnection(con1, vp3, vt2);

		visual.addConnection(vc1);
		visual.addConnection(vc2);
		visual.addConnection(vc3);
		visual.addConnection(vc4);*/

		r = new Random(1);

		for(Node component : visual.getRoot().getChildren())
			randomPosition(component);

		/*randomPosition(vp1);
		randomPosition(vp2);
		randomPosition(vp3);

		randomPosition(vt1);
		randomPosition(vt2);

		randomPosition(gr1);
		randomPosition(gr2);
		randomPosition(gr3);*/

		return visual;
	}
	Random r;

	private void randomPosition(Node node) {
		if(node instanceof Movable)
			MovableHelper.translate((Movable)node, r.nextDouble()*10, r.nextDouble()*10);
	}
}
