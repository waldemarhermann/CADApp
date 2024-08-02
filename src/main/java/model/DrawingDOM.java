package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DrawingDOM extends Drawing {
    public static void writeToObjectStream(Drawing f, String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(f);
            out.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Drawing readFromObjectStream(String filename) {
        Drawing d = null;
        IFigure f = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            d = (Drawing)in.readObject();
            in.close();
        } catch (IOException|ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return d;
    }

    public static void writeXMLEncoder(Drawing d, String filename) {
        try {
            XMLEncoder out = new XMLEncoder(new FileOutputStream(filename));
            out.writeObject(d);
            for (IFigure o : d.getShapes())
                FigureDOM.writeXMLEncoder((Figure)o, out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Drawing readXMLDecoder(String filename) {
        Drawing d = null;
        try {
            XMLDecoder in = new XMLDecoder(new FileInputStream(filename));
            d = (Drawing)in.readObject();
            try {
                while (true)
                    d.add(FigureDOM.readXMLDecoder(in));
            } catch (Exception exception) {
                in.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return d;
    }

    public static void writeXMLDOM(Drawing d, String filename) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dokument = builder.newDocument();
            Element root = dokument.createElement("Drawing");
            for (IFigure f : d.getShapes()) {
                Element figure = dokument.createElement("Figure");
                figure = FigureDOM.makeElement(dokument, figure, f);
                root.appendChild(figure);
            }
            dokument.appendChild(root);
            saveXMLDocumentToFile(dokument, filename);
            System.out.println("XML-Datei erfolgreich erstellt.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void saveXMLDocumentToFile(Document pXMLdocument, String pFileName) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            Source src = new DOMSource(pXMLdocument);
            File file = new File(pFileName);
            Result dest = new StreamResult(file);
            transformer.transform(src, dest);
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static Drawing readXMLDOM(String filename) {
        Drawing d = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dokument = builder.parse(new File(filename));
            d = getDrawing(dokument.getDocumentElement());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return d;
    }

    private static Drawing getDrawing(Element element) {
        Drawing d = new Drawing();
        d.remove();
        IFigure f = null;
        NodeList fig = element.getElementsByTagName("Drawing");
        if (element.getNodeName().equals("Drawing")) {
            NodeList ff = element.getElementsByTagName("Figure");
            for (int i = 0; i < ff.getLength(); i++) {
                f = (IFigure)FigureDOM.getInstance(ff.item(i));
                d.add(f);
            }
        }
        return d;
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(Double.valueOf(50.0D), Double.valueOf(20.0D), Double.valueOf(100.0D), Double.valueOf(50.0D));
        Ellipse e = new Ellipse(Double.valueOf(150.0D), Double.valueOf(200.0D), Double.valueOf(50.0D), Double.valueOf(200.0D));
        Drawing c1 = new Drawing(3);
        c1.add(r);
        c1.add(e);
        System.out.println("c1: " + c1);
        System.out.println("Serializer:");
        writeToObjectStream(c1, "D2.ser");
        Drawing c3 = readFromObjectStream("D2.ser");
        System.out.println("c3: " + c3);
        System.out.println("c1 == c3: " + c1.equals(c3));
        System.out.println();
        System.out.println("XMLEncoder:");
        writeXMLEncoder(c1, "DEC2.xml");
        Drawing c4 = readXMLDecoder("DEC2.xml");
        System.out.println("c4: " + c4);
        System.out.println("c1 == c4: " + c1.equals(c4));
        System.out.println();
        System.out.println("XMLDOM:");
        writeXMLDOM(c1, "DOM2.xml");
        Drawing c5 = readXMLDOM("DOM2.xml");
        System.out.println("c5: " + c5);
        System.out.println();
        System.out.println("c1 == c5: " + c1.equals(c5));
        c1.sort(null);
    }
}
