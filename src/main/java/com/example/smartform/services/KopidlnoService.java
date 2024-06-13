package com.example.smartform.services;

import com.example.smartform.models.Village;
import com.example.smartform.models.VillagePart;
import com.example.smartform.repositories.VillagePartRepository;
import com.example.smartform.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class KopidlnoService {

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private VillagePartRepository villagePartRepository;


    private void fetchVillages(Document doc) {
        NodeList villages = doc.getElementsByTagName("vf:Obec");
        for (int i = 0; i < villages.getLength(); i++) {
            Node obecNode = villages.item(i);
            if (obecNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) obecNode;
                String villageCode = element.getElementsByTagName("obi:Kod").item(0).getTextContent();
                String villageName = element.getElementsByTagName("obi:Nazev").item(0).getTextContent();

                Village v = new Village();
                v.setCode(villageCode);
                v.setName(villageName);
                villageRepository.save(v);
            }
        }
    }

    private void fetchVillageParts(Document doc) {
        NodeList villageParts = doc.getElementsByTagName("vf:CastObce");
        for (int j = 0; j < villageParts.getLength(); j++) {
            Node castNode = villageParts.item(j);
            if (castNode.getNodeType() == Node.ELEMENT_NODE) {
                Element castElement = (Element) castNode;
                String villagePartCode = castElement.getElementsByTagName("coi:Kod").item(0).getTextContent();
                String villagePartName = castElement.getElementsByTagName("coi:Nazev").item(0).getTextContent();


                VillagePart villagePart = new VillagePart();
                villagePart.setCode(villagePartCode);
                villagePart.setVillagePartName(villagePartName);


                NodeList obecList = castElement.getElementsByTagName("coi:Obec");
                Node obecNode = obecList.item(0);
                String kodObec = obecNode.getTextContent();
                System.out.println(kodObec);

                villagePart.setVillageCode(kodObec);

                villagePartRepository.save(villagePart);
            }
        }
    }

    private Document prepareDocument() throws Exception {
        URL url = new URL("https://www.smartform.cz/download/kopidlno.xml.zip");

        InputStream inputStream = new ZipInputStream(url.openStream());
        ZipInputStream zipStream = (ZipInputStream) inputStream;
        ZipEntry entry = zipStream.getNextEntry();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(zipStream);

        doc.getDocumentElement().normalize();
        return doc;
    }

    public void fetchAndStoreData() {
        Document doc = null;
        try {
            doc = prepareDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fetchVillages(doc);
        fetchVillageParts(doc);
    }
}
