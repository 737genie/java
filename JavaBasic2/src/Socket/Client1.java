package Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
public class Client1 {

	
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            // 1. 탱고 서버에 접속을 시도합니다. (IP: localhost, Port: 9999)
            socket = new Socket("192.168.1.41", 9999);
            System.out.println("📞 탱고에게 전화를 걸었어요!");

            // 2. 서버와 데이터를 주고받을 입출력 스트림을 생성합니다. (UTF-8 인코딩 명시)
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 3. 서버에 첫 인사를 보냅니다.
            out.println("탱고야 안녕! 나는 누렁이야! 🐶");
            System.out.println("💌 탱고에게 메시지를 보냈어요.");

            // 4. 서버로부터 온 답장을 읽습니다.
            String response = in.readLine();
            System.out.println("😺 탱고의 답장: " + response);

        } catch (Exception e) {
            System.out.println("오류가 발생했습니다: " + e.getMessage());
        } finally {
            // 5. 사용이 끝난 리소스를 모두 닫아줍니다.
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
                System.out.println("📱 전화를 끊었어요.");
            } catch (Exception e) {
                System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
            }
        }
    }
}