package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TupelDOM<T> extends Tupel<T> {
    public static Element makeElement(Document dokument, Element element, Object obj) {
        if (element.getNodeName().equals("Position") || element.getNodeName().equals("Size")) {
            Element xx = dokument.createElement("X");
            Element yy = dokument.createElement("Y");
            Tupel<Double> t = (Tupel<Double>) obj;
            xx.appendChild(dokument.createTextNode(((Double) t.x).toString()));
            yy.appendChild(dokument.createTextNode(((Double) t.y).toString()));
            element.appendChild(xx);
            element.appendChild(yy);
        }
        return element;
    }

    public static Tupel<Double> getInstance(Node element) {
        Tupel<Double> t = new Tupel<>();
        NodeList childs = element.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node child = childs.item(i);
            if (child.getNodeName().equals("X")) {
                t.x = Double.parseDouble(child.getTextContent());
            } else if (child.getNodeName().equals("Y")) {
                t.y = Double.parseDouble(child.getTextContent());
            }
        }
        return t;
    }
}





