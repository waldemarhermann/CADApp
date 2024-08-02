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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class FigureDOM extends Figure {
    public static void writeToObjectStream(IFigure f, String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(f);
            out.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static IFigure readFromObjectStream(String filename) {
        IFigure f = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            f = (IFigure)in.readObject();
            in.close();
        } catch (IOException|ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return f;
    }

    public static void writeXMLEncoder(Figure f, String filename) {
        try {
            XMLEncoder out = new XMLEncoder(new FileOutputStream(filename));
            writeXMLEncoder(f, out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void writeXMLEncoder(Figure f, XMLEncoder out) {
        out.writeObject(f);
        out.writeObject(f.getPosition());
        out.writeObject(f.getSize());
        out.writeObject(f.getColors());
    }

    public static Figure readXMLDecoder(String filename) {
        Figure f = null;
        try {
            XMLDecoder in = new XMLDecoder(new FileInputStream(filename));
            f = readXMLDecoder(in);
            in.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return f;
    }

    public static Figure readXMLDecoder(XMLDecoder in) {
        Figure f = null;
        Tupel<Double> t = null;
        Tupel<Color> tc = null;
        f = (Figure)in.readObject();
        t = (Tupel<Double>)in.readObject();
        f.setPosition((Double)t.x, (Double)t.y);
        t = (Tupel<Double>)in.readObject();
        f.setSize((Double)t.x, (Double)t.y);
        tc = (Tupel<Color>)in.readObject();
        f.setColors((Color)tc.x, (Color)tc.y);
        return f;
    }

    public static void writeXMLDOM(Figure figure, String filename) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dokument = builder.newDocument();
            Element root = dokument.createElement("Figure");
            root = makeElement(dokument, root, figure);
            dokument.appendChild(root);
            saveXMLDocumentToFile(dokument, filename);
            System.out.println("XML-Datei erfolgreich erstellt.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Element makeElement(Document dokument, Element element, Object obj) {
        if (element.getNodeName().equals("Figure")) {
            Figure figure = (Figure)obj;
            Element form = dokument.createElement("Form");
            form.appendChild(dokument.createTextNode(figure.getForm()));
            element.appendChild(form);
            Element position = dokument.createElement("Position");
            position = TupelDOM.makeElement(dokument, position, figure.getPosition());
            element.appendChild(position);
            Element size = dokument.createElement("Size");
            size = TupelDOM.makeElement(dokument, size, figure.getSize());
            element.appendChild(size);
            Element colors = dokument.createElement("Colors");
            Element cx = dokument.createElement("CX");
            cx = ColorDOM.makeElement(dokument, cx, (figure.getColors()).x);
            Element cy = dokument.createElement("CY");
            cy = ColorDOM.makeElement(dokument, cy, (figure.getColors()).y);
            colors.appendChild(cx);
            colors.appendChild(cy);
            element.appendChild(colors);
        }
        return element;
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

    public static IFigure readXMLDOM(String filename) {
        IFigure f = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dokument = builder.parse(new File(filename));
            f = (IFigure)getInstance(dokument.getDocumentElement());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static Object getInstance(Node element) {
        Figure figure = new Rectangle();
        if (element.getNodeName().equals("Figure")) {
            NodeList childs = element.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                if (childs.item(i).getNodeName().contentEquals("Form")) {
                    String pform = childs.item(i).getTextContent();
                    figure = Figure.createInstanceByName(pform);
                    figure.setForm(pform);
                }
                if (childs.item(i).getNodeName().contentEquals("Position")) {
                    Tupel<Double> t = (Tupel<Double>)TupelDOM.getInstance(childs.item(i));
                    figure.setPosition((Double)t.x, (Double)t.y);
                }
                if (childs.item(i).getNodeName().contentEquals("Size")) {
                    Tupel<Double> t = (Tupel<Double>)TupelDOM.getInstance(childs.item(i));
                    figure.setSize((Double)t.x, (Double)t.y);
                }
                if (childs.item(i).getNodeName().contentEquals("Colors")) {
                    Color cx = new Color(), cy = new Color();
                    NodeList cchilds = childs.item(i).getChildNodes();
                    for (int k = 0; k < cchilds.getLength(); k++) {
                        if (cchilds.item(k).getNodeName().contentEquals("CX"))
                            cx = (Color)ColorDOM.getInstance(cchilds.item(k));
                        if (cchilds.item(k).getNodeName().contentEquals("CY"))
                            cy = (Color)ColorDOM.getInstance(cchilds.item(k));
                    }
                    figure.setColors(cx, cy);
                }
            }
        }
        return figure;
    }
}
