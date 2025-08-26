package test1t;

public enum Role {
    // 🔵 시민팀
    CITIZEN("시민", "\033[0;37m", Team.CITIZEN, "평범한 시민입니다. 토론과 투표로 마피아를 찾아내세요."),
    POLICE("경찰", "\033[0;34m", Team.CITIZEN, "매일 밤 한 명을 조사하여 마피아 여부를 확인할 수 있습니다."),
    DOCTOR("의사", "\033[0;32m", Team.CITIZEN, "매일 밤 한 명을 보호하여 마피아의 공격으로부터 지킬 수 있습니다."),
    SOLDIER("군인", "\033[0;33m", Team.CITIZEN, "마피아의 첫 번째 공격을 한 번 무시할 수 있습니다."),
    JOURNALIST("기자", "\033[0;36m", Team.CITIZEN, "특정 플레이어의 정보를 수집하고 공개할 수 있습니다."),
    DETECTIVE("탐정", "\033[0;35m", Team.CITIZEN, "대상이 밤에 누구에게 능력을 사용하는지 추적할 수 있습니다."),
    JUDGE("판사", "\033[0;31m", Team.CITIZEN, "투표 결과를 뒤집을 수 있는 선고권을 가지고 있습니다."),
    MEDIUM("영매", "\033[0;95m", Team.CITIZEN, "죽은 자들과 대화하고 그들의 정보를 얻을 수 있습니다."),
    HUNTER("사냥꾼", "\033[0;93m", Team.CITIZEN, "자신이 죽을 때 한 명을 도연사시킬 수 있습니다."),

    // 🔴 마피아팀
    MAFIA("마피아", "\033[0;91m", Team.MAFIA, "매일 밤 시민을 제거합니다. 동료 마피아와 협력하세요."),
    MADAM("마담", "\033[0;92m", Team.MAFIA, "마피아팀의 리더로 특별한 지시 능력을 가지고 있습니다."),
    SPY("스파이", "\033[0;94m", Team.MAFIA, "다른 플레이어들의 밤 행동을 감시할 수 있습니다."),
    THIEF("도둑", "\033[0;96m", Team.MAFIA, "다른 플레이어의 능력을 훔쳐서 사용할 수 있습니다.");

    private final String koreanName;
    private final String color;
    private final Team team;
    private final String description;

    public static final String RESET = "\033[0m";

    Role(String koreanName, String color, Team team, String description) {
        this.koreanName = koreanName;
        this.color = color;
        this.team = team;
        this.description = description;
    }

    public String getKoreanName() { return koreanName; }
    public String getColor() { return color; }
    public Team getTeam() { return team; }
    public String getDescription() { return description; }
    
    public String getColoredName() {
        return color + koreanName + RESET;
    }

    public enum Team {
        CITIZEN("시민팀", "\033[0;34m"),
        MAFIA("마피아팀", "\033[0;91m");

        private final String koreanName;
        private final String color;

        Team(String koreanName, String color) {
            this.koreanName = koreanName;
            this.color = color;
        }

        public String getKoreanName() { return koreanName; }
        public String getColoredName() { return color + koreanName + Role.RESET; }
    }

    public boolean needsNightAction() {
        return this == POLICE || this == DOCTOR || this == DETECTIVE ||
               this == MAFIA || this == MADAM || this == SPY || this == JOURNALIST;
    }

    public boolean canInvestigate() {
        return this == POLICE || this == DETECTIVE || this == SPY;
    }

    public String getAbilityDescription() {
        switch (this) {
            case CITIZEN:
                return "📝 특별한 능력이 없지만 토론과 투표로 마피아를 찾아내세요";
            case POLICE:
                return "👮 밤에 한 명을 조사하여 마피아/시민 판별";
            case DOCTOR:
                return "⚕️ 밤에 한 명을 보호하여 마피아 공격 방어";
            case DETECTIVE:
                return "🔍 밤에 한 명을 추적하여 그들의 행동 확인";
            case SOLDIER:
                return "🛡️ 마피아의 첫 번째 공격을 한 번 무효화";
            case JOURNALIST:
                return "📰 밤에 한 명을 취재하여 정보 수집";
            case JUDGE:
                return "⚖️ 투표 결과를 뒤집을 수 있는 선고권 (한 게임당 1회)";
            case MEDIUM:
                return "🔮 죽은 자들과 대화하며 정보 수집";
            case HUNTER:
                return "🏹 죽을 때 한 명을 복수로 제거 (즉시 발동)";
            case MAFIA:
                return "🔪 밤에 시민을 제거하는 마피아";
            case MADAM:
                return "🔪 마피아 팀 리더, 밤에 시민을 제거";
            case SPY:
                return "👁️ 밤에 다른 플레이어의 행동 감시";
            case THIEF:
                return "🥷 다른 플레이어의 능력을 훔쳐서 사용";
            default:
                return "알 수 없는 직업";
        }
    }
}
