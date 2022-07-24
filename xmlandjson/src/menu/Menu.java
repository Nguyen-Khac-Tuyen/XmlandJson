package menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Menu {

    App app = new App();

    public Menu() throws SQLException {

    }

    public static void main(String[] args) throws SQLException, JAXBException, ParserConfigurationException, TransformerException, IOException {
        Scanner input = new Scanner(System.in);
        menu();
        while (true){
            System.out.println("#: ");
            int choice = input.nextInt();
            switch (choice){
                case 1->{
                    App.LoadData();
                }
                case 2->{
                    App.writeJson();
                    System.out.println("jsonwrite thanh cong!");
                }
                case 3->{
                    App.writeXml();
                    System.out.println("WriteXML thanh cong!");
                }
                case 4->{
                    System.out.println("Name:");

//                    findByName();

                }

                case 5->{
                    return;
                }
            }
        }
    }

    public static void menu(){
        System.out.println("=====Menu=====");
        System.out.println("1.Display student");
        System.out.println("2.Write data to Json");
        System.out.println("3.Write data to XML");
        System.out.println("4.Find student by name");
        System.out.println("5.Exit");
    }
}