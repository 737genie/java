package socketTest;

import java.util.*;

public class JobManager {
    
    public static List<Role> assignRoles(int playerCount) {
        List<Role> roles = new ArrayList<>();
        
        if (playerCount < 4 || playerCount > 15) {
            throw new IllegalArgumentException("플레이어 수는 4-15명이어야 합니다.");
        }
        
        // 기본 핵심 직업 (4명 기준)
        roles.add(Role.MAFIA);   // 마피아 1명 필수
        roles.add(Role.POLICE);  // 경찰 1명 필수  
        roles.add(Role.DOCTOR);  // 의사 1명 필수
        // 나머지 1명은 시민
        
        // 5명: 영매 추가 (죽은 자 대화 시스템)
        if (playerCount >= 5) {
            roles.add(Role.MEDIUM);
        }
        
        // 6명: 마피아 2명으로 증가
        if (playerCount >= 6) {
            roles.add(Role.MAFIA);
        }
        
        // 7명: 탐정 추가
        if (playerCount >= 7) {
            roles.add(Role.DETECTIVE);
        }
        
        // 8명: 군인 추가 (마피아 대응력 강화)
        if (playerCount >= 8) {
            roles.add(Role.SOLDIER);
        }
        
        // 9명: 마담 추가 (마피아팀 강화)
        if (playerCount >= 9) {
            roles.add(Role.MADAM);
        }
        
        // 10명: 기자 추가
        if (playerCount >= 10) {
            roles.add(Role.JOURNALIST);
        }
        
        // 11명: 마피아 3명으로 증가
        if (playerCount >= 11) {
            roles.add(Role.MAFIA);
        }
        
        // 12명: 판사 추가 (게임 체인저)
        if (playerCount >= 12) {
            roles.add(Role.JUDGE);
        }
        
        // 13명: 스파이 추가 (마피아팀 정보력 강화)
        if (playerCount >= 13) {
            roles.add(Role.SPY);
        }
        
        // 14명: 사냥꾼 추가 (시민팀 복수 능력)
        if (playerCount >= 14) {
            roles.add(Role.HUNTER);
        }
        
        // 15명: 도둑 추가 (최고 변수)
        if (playerCount >= 15) {
            roles.add(Role.THIEF);
        }
        
        // 나머지는 시민으로 채우기
        while (roles.size() < playerCount) {
            roles.add(Role.CITIZEN);
        }
        
        // 역할 셔플
        Collections.shuffle(roles);
        return roles;
    }
    
    public static void printRoleDistribution(int playerCount) {
        List<Role> roles = assignRoles(playerCount);
        Map<Role, Integer> roleCount = new HashMap<>();
        
        for (Role role : roles) {
            roleCount.put(role, roleCount.getOrDefault(role, 0) + 1);
        }
        
        System.out.println("=== " + playerCount + "명 게임 역할 분배 ===");
        
        long mafiaCount = roleCount.entrySet().stream()
            .filter(entry -> entry.getKey().getTeam() == Role.Team.MAFIA)
            .mapToLong(Map.Entry::getValue)
            .sum();
            
        long citizenCount = roleCount.entrySet().stream()
            .filter(entry -> entry.getKey().getTeam() == Role.Team.CITIZEN)
            .mapToLong(Map.Entry::getValue)
            .sum();
        
        System.out.println("🔴 " + Role.Team.MAFIA.getColoredName() + " (" + mafiaCount + "명):");
        roleCount.entrySet().stream()
            .filter(entry -> entry.getKey().getTeam() == Role.Team.MAFIA)
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println("  " + entry.getKey().getColoredName() + ": " + entry.getValue() + "명"));
            
        System.out.println("🔵 " + Role.Team.CITIZEN.getColoredName() + " (" + citizenCount + "명):");
        roleCount.entrySet().stream()
            .filter(entry -> entry.getKey().getTeam() == Role.Team.CITIZEN)
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println("  " + entry.getKey().getColoredName() + ": " + entry.getValue() + "명"));
            
        System.out.println("⚖️ 밸런스: 마피아 " + mafiaCount + " vs 시민 " + citizenCount);
    }
    
    public static boolean isBalanced(int playerCount) {
        List<Role> roles = assignRoles(playerCount);
        
        long mafiaCount = roles.stream()
            .filter(role -> role.getTeam() == Role.Team.MAFIA)
            .count();
            
        long citizenCount = roles.stream()
            .filter(role -> role.getTeam() == Role.Team.CITIZEN)
            .count();
        
        // 마피아는 시민의 30% ~ 40% 사이여야 밸런스가 좋음
        double ratio = (double) mafiaCount / citizenCount;
        return ratio >= 0.25 && ratio <= 0.45;
    }
    
    // 테스트용 메인 메소드
    public static void main(String[] args) {
        System.out.println("🎭 마피아42 직업 배정 시스템 테스트 🎭\n");
        
        for (int i = 4; i <= 15; i++) {
            printRoleDistribution(i);
            System.out.println("밸런스: " + (isBalanced(i) ? "✅ 양호" : "⚠️ 주의"));
            System.out.println();
        }
    }
}