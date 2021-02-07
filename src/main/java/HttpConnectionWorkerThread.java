import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private Socket socket;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;

    }

    private static String getHtmlResponse(String htmlPage){
        final String crlf = "\n\r";
        String reponse =
                "HTTP/1.1 200 OK" + crlf + // Status Line : HTTP VERSION RESPONSE CODE RESPONSE MESSAGE#
                        "Content-Length: " + htmlPage.getBytes().length + crlf + // Header
                        crlf +
                        htmlPage +
                        crlf + crlf;
        return reponse;
    }

    private void inputStreamClose(){
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputStreamClose(){
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void socketClose(){
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(){
        inputStreamClose();
        outputStreamClose();
        socketClose();
    }

    private void readInputStream(){
        int counter;
        try {
            while ((counter = inputStream.read()) >= 0) {
                System.out.print((char) counter);
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            readInputStream();


            String htmlPage = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This is a page is by Derek, with Sara to correct the spelling</h1></body></html>";

            String response = getHtmlResponse(htmlPage);

            outputStream.write(response.getBytes());
            // TODO we would read

            //TODO we would write


        } catch(IOException ex){
            ex.printStackTrace();
        } finally {
            close();
        }
    }
}
