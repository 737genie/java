package test1t;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameRoomTest {
    public static void main(String[] args) throws IOException {
        System.out.println("🧪 GameRoom 14명 테스트 시작");
        
        GameRoom gameRoom = new GameRoom();
        List<MockPlayer> players = new ArrayList<>();
        
        // 14명의 Mock 플레이어 생성
        for (int i = 1; i <= 14; i++) {
            MockPlayer player = new MockPlayer(i);
            player.setNickname("테스트" + i);
            player.setAlive(true);
            players.add(player);
            
            gameRoom.addPlayer(player);
        }
        
        // 게임 시작
        gameRoom.startGame();
        
        // 역할 배정 확인
        System.out.println("\n🎭 역할 배정 결과:");
        for (MockPlayer p : players) {
            System.out.println(p.getNickname() + ": " + p.getRole().getKoreanName());
        }
        
        // 승리 조건 테스트
        System.out.println("\n🏆 승리 조건 테스트 시작");
        testWinConditions(gameRoom, players);
    }
    
    private static void testWinConditions(GameRoom gameRoom, List<MockPlayer> players) {
        // 마피아 승리 시나리오
        System.out.println("🔪 마피아 승리 시나리오 테스트");
        
        // 시민진영만 제거 (마피아 제외)
        int killedCount = 0;
        for (MockPlayer p : players) {
            if (p.getRole() != Role.MAFIA && killedCount < 8) {
                p.setAlive(false);
                killedCount++;
                System.out.println("💀 " + p.getNickname() + " 제거됨");
            }
        }
        
        // 승리 조건 확인
        long aliveMafia = players.stream()
            .filter(p -> p.isAlive() && p.getRole() == Role.MAFIA)
            .count();
        long aliveCitizens = players.stream()
            .filter(p -> p.isAlive() && p.getRole() != Role.MAFIA)
            .count();
            
        System.out.println("📊 생존자 현황:");
        System.out.println("   마피아: " + aliveMafia + "명");
        System.out.println("   시민진영: " + aliveCitizens + "명");
        
        if (aliveMafia >= aliveCitizens) {
            System.out.println("🏆 마피아 승리 조건 만족!");
        } else {
            System.out.println("⏳ 게임 계속 진행");
        }
    }
}
