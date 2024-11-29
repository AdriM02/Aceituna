package marshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class MarshallingXML {

    public MarshallingXML() {
    }

    public void hacerMarshalling(Base base){

        // Fichero para serializar el objeto Base
        String xmlFilePath = "base.xml";

        try {
            // Crear un contexto JAXB para la clase Base
            JAXBContext jaxbContext = JAXBContext.newInstance(Base.class);

            // Crear un marshaller para convertir el objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar el objeto Base a una cadena XML
            StringWriter sw = new StringWriter();
            marshaller.marshal(base, sw);

            // Serializar el objeto Base a un fichero
            marshaller.marshal(base, new File(xmlFilePath));


            System.out.println("XML creado con Éxito\n");


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void hacerUnmarshalling(){
        // Fichero para deserializar el objeto Base
        String xmlFilePath = "base.xml";

        try {
            // Crear un contexto JAXB para la clase Base
            JAXBContext jaxbContext = JAXBContext.newInstance(Base.class);

            // Deserializar objeto Base desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Base baseFromXml = (Base) unmarshaller.unmarshal(new File(xmlFilePath));

            // Mostrar la Base recuperada del XML
            System.out.println("Objeto Base después de deserializar:");
            System.out.println(baseFromXml.toString());
            System.out.println("\n");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}