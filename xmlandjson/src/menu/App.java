package menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
//import program.entities.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;


public class App {
    Mssql mssql = (Mssql) getConnection();
    static List<Product> studentList = new ArrayList<>();
    public static void addStudent(Product product){
        studentList.add(product);
    }
    public static void LoadData() throws SQLException{
        Connection connection = getConnection();
        String query ="Select * from Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet =statement.executeQuery(query);
        while (resultSet.next()){
            int id =resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String mobile = resultSet.getString(4);
            System.out.println(id + "|" +name +"|"+ address + "|" +mobile );
            Product product = new Product(id,name,address,mobile);
            addStudent(product);
        }

    }

    private static Connection getConnection() {
        return (Connection) studentList;
    }

    public static void writeJson() throws SQLException, IOException {
        Writer writer =  new FileWriter("student.json");
        new Gson().toJson(studentList,writer);
        writer.close();
        System.out.println("complete");
    }
    public static void writeXml() throws ParserConfigurationException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newNSInstance().newDocumentBuilder();
        Document dom = builder.newDocument();
        Element root = dom.createElement("user");
        dom.appendChild(root);

        for (Product s: studentList) {
            Element student = dom.createElement("student");
            root.appendChild(student);

            Attr attr = dom.createAttribute("id");
            attr.setValue(String.valueOf(s.getId()));
            student.setAttributeNode(attr);

            Element name = dom.createElement("name");
            name.setTextContent(s.getName());
            Element address = dom.createElement("address");
            address.setTextContent(s.getAddress());
            Element mobile = dom.createElement("mobile");
            mobile.setTextContent(s.getMobile());

            student.appendChild(name);
            student.appendChild(address);
            student.appendChild(mobile);

        }


        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(dom),new StreamResult((new File("Students.xml"))));
        System.out.println("Success!");

    }

    public void findByName(String name) throws FileNotFoundException {
        FileReader reader = new FileReader("student.json");
        studentList = new Gson().fromJson(reader,new TypeToken<List<Product>>(){}.getType());

        for (Product s:studentList) {
            if (s.getName().equals(name)){
                System.out.println(s);
                break;
            }else {
                System.out.println("Not found");
            }
        }

    }
    public void findByNameXml(String name) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document dom = builder.parse(new File("Students.xml"));

        dom.normalizeDocument();
        Element root = dom.getDocumentElement();

        for (Product s:studentList) {

            if (s.getName().equals(name)){
                System.out.println(root.getElementsByTagName("name").item(0).getTextContent());
                break;
            }else {
                System.out.println("Not found");
            }
        }
    }
}