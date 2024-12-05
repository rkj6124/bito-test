import java.io.*;
import java.util.function.Consumer;

public class WriteAndRead {
    private void readFromDatabase(Consumer<String> callback) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                callback.accept("Hello from the Database!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void writeAndRead() {
        readFromDatabase(data -> {
            try {
                FileWriter writer = new FileWriter("message.txt");
                writer.write(data);
                writer.close();
                System.out.println("File written successfully.");
            } catch (IOException e) {
                System.err.println("Write Error: " + e.getMessage());
            }
        });

        try {
            BufferedReader reader = new BufferedReader(new FileReader("message.txt"));
            String content = reader.readLine();
            reader.close();
            System.out.println("File Content: " + content);
        } catch (IOException e) {
            System.err.println("Read Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new WriteAndRead().writeAndRead();
    }
}