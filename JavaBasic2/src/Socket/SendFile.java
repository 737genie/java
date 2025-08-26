package Socket;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.File;
public class SendFile {
    public static void main(String[] args) throws Exception {
        // 1. 전송할 파일의 전체 경로를 지정합니다.
        // 자바 문자열에서 '\'는 특별한 의미(이스케이프 문자)를 가지므로 '\\' 두 개를 사용해야 합니다.
        String filePath = "C:\\backup\\탱고.PNG";
        File file = new File(filePath);

        // 2. 파일이 실제로 존재하는지 확인합니다.
        if (!file.exists()) {
            System.out.println("🚨 '" + filePath + "' 파일을 찾을 수 없어요! 먼저 파일을 준비해주세요.");
            return;
        }

        Socket socket = new Socket("127.0.0.1", 9876);
        System.out.println("📞 탱고에게 선물을 보내기 위해 연결했어요.");

        // 파일을 읽기 위한 스트림
        FileInputStream fis = new FileInputStream(file);
        // 서버로 데이터를 보내기 위한 스트림
        OutputStream out = socket.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        System.out.println("🎁 '" + file.getName() + "' 선물을 포장해서 보내는 중...");
        // 파일의 끝에 도달할 때까지(-1) 계속해서 데이터를 읽어 서버로 전송한다.
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush(); // 버퍼에 남은 데이터를 모두 전송

        System.out.println("✅ 선물을 성공적으로 보냈어요!");

        fis.close();
        out.close();
        socket.close();
    }
}