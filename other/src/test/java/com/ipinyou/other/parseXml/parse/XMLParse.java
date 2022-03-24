package com.ipinyou.other.parseXml.parse;

import com.ipinyou.other.enums.XmlClassEnum;
import com.ipinyou.other.util.BeanUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XMLParse {
    public static void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        try {
            Node topic;
            Element message;
            Document document = saxReader.read(inputXml);
            topic = document.selectSingleNode("//*[local-name()='topic']");
            message = (Element) document.selectSingleNode("//*[local-name()='message']");
            if (Objects.isNull(topic) || Objects.isNull(message)) {
                return;
            }
            var messageIte = message.elementIterator();
            List<Object> list = parserObjectList(messageIte);
            System.out.println(list);
            System.out.println(topic.getText());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static List<Object> parserObjectList(Iterator<Element> iterator) {
        if (iterator == null || !iterator.hasNext()) {
            return Collections.emptyList();
        }
        var list = new ArrayList<>();
        while (iterator.hasNext()) {
            try {
                var cur = iterator.next();
                Class<?> beanClass = XmlClassEnum.valueOf(cur.getName()).getBeanClass();
                // 实例化当前对象
                Object bean = beanClass.getConstructor().newInstance();
                setterValue(bean, cur.elementIterator());
                list.add(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void setterValue(Object bean, Iterator<Element> iterator) {
        if (iterator == null || !iterator.hasNext()) {
            return;
        }
        try {
            var descriptorMap = Arrays.stream(Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors())
                    .collect(Collectors.toMap(FeatureDescriptor::getName, Function.identity()));
            while (iterator.hasNext()) {
                var cur = iterator.next();
                var propertyDescriptor = descriptorMap.get(cur.getName());
                if (Objects.nonNull(propertyDescriptor) && propertyDescriptor.getWriteMethod() != null) {
                    Object value;
                    if (!cur.elementIterator().hasNext()) {
                        value = BeanUtil.turnToObject(cur.getText(), propertyDescriptor.getPropertyType());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        appendChildren(sb, cur);
                        value = sb.substring(0, sb.length() - 1);
                    }
                    propertyDescriptor.getWriteMethod().invoke(bean, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendChildren(StringBuilder sb, Element element) {
        if (element == null) {
            return;
        }
        var elementIterator = element.elementIterator();
        while (elementIterator.hasNext()) {
            var next = elementIterator.next();
            var innerIterator = next.elementIterator();
            while (innerIterator.hasNext()) {
                sb.append(innerIterator.next().getText()).append("=");
                sb.append(innerIterator.next().getText()).append(";");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        XMLParse.parserXml("/Users/wtai/Downloads/烽火.xml");
    }
}

