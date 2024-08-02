package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ColorDOM extends Color {
    public static Element makeElement(Document dokument, Element element, Object obj) {
        if (element.getNodeName().equals("CX") || element.getNodeName().equals("CY")) {
            Element rr = dokument.createElement("R");
            Element gg = dokument.createElement("G");
            Element bb = dokument.createElement("B");
            Element aa = dokument.createElement("A");
            Color c = (Color)obj;
            rr.appendChild(dokument.createTextNode(Integer.toString(c.getRed())));
            gg.appendChild(dokument.createTextNode(Integer.toString(c.getGreen())));
            bb.appendChild(dokument.createTextNode(Integer.toString(c.getBlue())));
            aa.appendChild(dokument.createTextNode(Integer.toString(c.getAlpha())));
            element.appendChild(rr);
            element.appendChild(gg);
            element.appendChild(bb);
            element.appendChild(aa);
        }
        return element;
    }

    public static Color getInstance(Node element) {
        Color c = new Color();
        int r = c.getRed(), g = c.getGreen(), b = c.getBlue(), a = c.getAlpha();
        if (element.getNodeName().equals("CX") || element.getNodeName().equals("CY")) {
            NodeList childs = element.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) {
                if (childs.item(i).getNodeName().contentEquals("R"))
                    r = Integer.parseInt(childs.item(i).getTextContent());
                if (childs.item(i).getNodeName().contentEquals("G"))
                    g = Integer.parseInt(childs.item(i).getTextContent());
                if (childs.item(i).getNodeName().contentEquals("B"))
                    b = Integer.parseInt(childs.item(i).getTextContent());
                if (childs.item(i).getNodeName().contentEquals("A"))
                    a = Integer.parseInt(childs.item(i).getTextContent());
            }
            c.setColor(r, g, b, a);
        }
        return new Color();
    }
}
