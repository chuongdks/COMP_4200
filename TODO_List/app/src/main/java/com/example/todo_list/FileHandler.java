package com.example.todo_list;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    // Write data to a file
    public void writeData(String fileName, ArrayList<String> arrayList, Context context) {
        try {
            // open the FileOutputStream using the specified filename (data.dat)
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            // ObjectOutputStream to write data to the file
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write data to the file using writeObject()
            oos.writeObject(arrayList);

            //
            oos.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read data from file
    public ArrayList<String> readData(String fileName, Context context) {
        //
        ArrayList<String> list;

        //
        try {
            // File Stream open the file
            FileInputStream fis = context.openFileInput(fileName);

            // wrap the FileStream with ObjectStream
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Read data from input stream into ArrayList
            list = (ArrayList<String>) ois.readObject();
        }
        catch (Exception e) {
            list = new ArrayList<>(); // Declare an empty list just in case the list return nothing
        }

        return list;
    }
}
