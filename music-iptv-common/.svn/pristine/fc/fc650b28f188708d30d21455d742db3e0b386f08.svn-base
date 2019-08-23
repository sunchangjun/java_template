package hk.reco.music.iptv.common.utils;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * XML 工具类
 *
 * @author Alistair.Chow
 * @date 2019/4/15 14:53
 */
public class XmlUtils {

    /**
     * XML序列化
     *
     * @param obj         被序列化对象
     * @param xmlFilePath 生成本地文件路径
     **/
    public static <T> void serialize(T obj, String xmlFilePath) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 编码格式,默认为utf-8
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // 是否省略xml头信息
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        File file = new File(xmlFilePath);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"
                )
        );
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        marshaller.marshal(obj, writer);
    }

    /***
     *
     * XML 序列化
     *
     * @param obj 被序列化对象
     * @return java.lang.String
     **/
    public static <T> String serialize(T obj) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 编码格式,默认为utf-8
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // 是否省略xml头信息
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty("jaxb.encoding", "utf-8");
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        marshaller.marshal(obj, writer);

        return writer.toString();
    }

    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(String xml, Class<T> clazz)
            throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshal = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        return (T) unmarshal.unmarshal(reader);
    }

    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserializeDelNamespanc(String xml, Class<T> clazz)
            throws JAXBException, ParserConfigurationException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        SAXParserFactory sax = SAXParserFactory.newInstance();
        sax.setNamespaceAware(false);
        XMLReader xmlReader = sax.newSAXParser().getXMLReader();
        Source source = new SAXSource(xmlReader, new InputSource(reader));
        return (T)unmarshaller.unmarshal(source);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parserXML(String xml) {
        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
        decoder.close();
        return (T) decoder.readObject();
    }

}  