package marshalling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class MarshallingJSON {
    public MarshallingJSON() {
    }

    public void hacerMarshallingJSON(Base base){

        String jsonFilePath = "base.json";

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(base);

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFilePath))){

                bw.write(json);
                System.out.println("Json creado exitosamente");
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hacerUnmarshallingJSON() {
        String jsonFilePath = "base.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {

            Base base = gson.fromJson(br, Base.class);

            System.out.println(base);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
