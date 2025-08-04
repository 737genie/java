package OOP0804;

/**
 * 스마트 홈 시스템 - 실생활 상속 예제
 * 다양한 가전제품들이 공통 기능과 고유 기능을 갖는 시스템
 */

// 최상위 부모 클래스 - 모든 스마트 기기의 공통 기능
abstract class SmartDevice {
    protected String deviceName;
    protected boolean isOn;
    protected String manufacturer;
    protected double powerConsumption; // 전력 소비량 (와트)
    
    // 부모 생성자
    public SmartDevice(String deviceName, String manufacturer) {
        this.deviceName = deviceName;
        this.manufacturer = manufacturer;
        this.isOn = false;
        this.powerConsumption = 0.0;
        System.out.println("📱 " + deviceName + " (" + manufacturer + ") 기기가 연결되었습니다.");
    }
    
    // 모든 스마트 기기의 공통 기능
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            powerConsumption = getBasePowerConsumption();
            System.out.println("🔌 " + deviceName + "이(가) 켜졌습니다. (소비전력: " + powerConsumption + "W)");
        } else {
            System.out.println("⚠️ " + deviceName + "은(는) 이미 켜져 있습니다.");
        }
    }
    
    public void turnOff() {
        if (isOn) {
            isOn = false;
            powerConsumption = 0.0;
            System.out.println("🔌 " + deviceName + "이(가) 꺼졌습니다.");
        } else {
            System.out.println("⚠️ " + deviceName + "은(는) 이미 꺼져 있습니다.");
        }
    }
    
    public void getStatus() {
        System.out.println("=== " + deviceName + " 상태 ===");
        System.out.println("제조업체: " + manufacturer);
        System.out.println("전원: " + (isOn ? "ON" : "OFF"));
        System.out.println("소비전력: " + powerConsumption + "W");
    }
    
    // 추상 메서드들 - 자식 클래스에서 구현 필수
    protected abstract double getBasePowerConsumption();
    public abstract void performMainFunction();
    
    // Getter 메서드들
    public String getDeviceName() { return deviceName; }
    public boolean isOn() { return isOn; }
    public double getPowerConsumption() { return powerConsumption; }
}

// 스마트 전등 클래스
class SmartLight extends SmartDevice {
    private int brightness;    // 밝기 (0-100)
    private String color;      // 색상
    
    public SmartLight(String deviceName, String manufacturer) {
        super(deviceName, manufacturer);
        this.brightness = 50;  // 기본 밝기
        this.color = "흰색";   // 기본 색상
    }
    
    @Override
    protected double getBasePowerConsumption() {
        return 10.0; // LED 전등 기본 소비전력
    }
    
    @Override
    public void performMainFunction() {
        if (!isOn) {
            System.out.println("⚠️ " + deviceName + "이(가) 꺼져 있습니다. 먼저 전원을 켜주세요.");
            return;
        }
        System.out.println("💡 " + deviceName + "이(가) " + color + " 빛으로 밝기 " + brightness + "%로 조명하고 있습니다.");
    }
    
    // 전등만의 고유 기능들
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("⚠️ 밝기는 0-100 사이여야 합니다.");
            return;
        }
        
        this.brightness = brightness;
        // 밝기에 따라 전력 소비량 조정
        if (isOn) {
            powerConsumption = getBasePowerConsumption() * (brightness / 100.0);
        }
        System.out.println("💡 " + deviceName + " 밝기를 " + brightness + "%로 설정했습니다.");
    }
    
    public void setColor(String color) {
        this.color = color;
        System.out.println("🌈 " + deviceName + " 색상을 " + color + "로 변경했습니다.");
    }
    
    @Override
    public void getStatus() {
        super.getStatus(); // 부모의 상태 정보 먼저 출력
        System.out.println("밝기: " + brightness + "%");
        System.out.println("색상: " + color);
    }
}

// 스마트 에어컨 클래스
class SmartAirConditioner extends SmartDevice {
    private int temperature;      // 설정 온도
    private String mode;         // 모드 (냉방, 난방, 송풍)
    private int fanSpeed;        // 팬 속도 (1-5)
    
    public SmartAirConditioner(String deviceName, String manufacturer) {
        super(deviceName, manufacturer);
        this.temperature = 24;    // 기본 온도
        this.mode = "냉방";       // 기본 모드
        this.fanSpeed = 3;        // 기본 팬 속도
    }
    
    @Override
    protected double getBasePowerConsumption() {
        return 1200.0; // 에어컨 기본 소비전력
    }
    
    @Override
    public void performMainFunction() {
        if (!isOn) {
            System.out.println("⚠️ " + deviceName + "이(가) 꺼져 있습니다. 먼저 전원을 켜주세요.");
            return;
        }
        System.out.println("❄️ " + deviceName + "이(가) " + mode + " 모드로 " + 
                          temperature + "°C, 팬 속도 " + fanSpeed + "단으로 작동 중입니다.");
    }
    
    // 에어컨만의 고유 기능들
    public void setTemperature(int temperature) {
        if (temperature < 16 || temperature > 30) {
            System.out.println("⚠️ 온도는 16-30°C 사이여야 합니다.");
            return;
        }
        
        this.temperature = temperature;
        System.out.println("🌡️ " + deviceName + " 설정 온도를 " + temperature + "°C로 변경했습니다.");
    }
    
    public void setMode(String mode) {
        if (!mode.equals("냉방") && !mode.equals("난방") && !mode.equals("송풍")) {
            System.out.println("⚠️ 모드는 '냉방', '난방', '송풍' 중 하나여야 합니다.");
            return;
        }
        
        this.mode = mode;
        // 모드에 따라 전력 소비량 조정
        if (isOn) {
            double multiplier = mode.equals("송풍") ? 0.3 : 1.0;
            powerConsumption = getBasePowerConsumption() * multiplier;
        }
        System.out.println("🔄 " + deviceName + " 모드를 " + mode + "로 변경했습니다.");
    }
    
    public void setFanSpeed(int fanSpeed) {
        if (fanSpeed < 1 || fanSpeed > 5) {
            System.out.println("⚠️ 팬 속도는 1-5단 사이여야 합니다.");
            return;
        }
        
        this.fanSpeed = fanSpeed;
        System.out.println("💨 " + deviceName + " 팬 속도를 " + fanSpeed + "단으로 설정했습니다.");
    }
    
    @Override
    public void getStatus() {
        super.getStatus();
        System.out.println("설정 온도: " + temperature + "°C");
        System.out.println("모드: " + mode);
        System.out.println("팬 속도: " + fanSpeed + "단");
    }
}

// 스마트 TV 클래스
class SmartTV extends SmartDevice {
    private int channel;         // 현재 채널
    private int volume;          // 음량
    private String currentApp;   // 현재 실행 중인 앱
    
    public SmartTV(String deviceName, String manufacturer) {
        super(deviceName, manufacturer);
        this.channel = 1;
        this.volume = 20;
        this.currentApp = "지상파";
    }
    
    @Override
    protected double getBasePowerConsumption() {
        return 150.0; // TV 기본 소비전력
    }
    
    @Override
    public void performMainFunction() {
        if (!isOn) {
            System.out.println("⚠️ " + deviceName + "이(가) 꺼져 있습니다. 먼저 전원을 켜주세요.");
            return;
        }
        System.out.println("📺 " + deviceName + "에서 " + currentApp + 
                          " (채널 " + channel + ")을 음량 " + volume + "으로 시청 중입니다.");
    }
    
    // TV만의 고유 기능들
    public void changeChannel(int channel) {
        if (channel < 1 || channel > 999) {
            System.out.println("⚠️ 채널은 1-999 사이여야 합니다.");
            return;
        }
        
        this.channel = channel;
        this.currentApp = "지상파"; // 채널 변경 시 지상파로 전환
        System.out.println("📺 " + deviceName + " 채널을 " + channel + "번으로 변경했습니다.");
    }
    
    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            System.out.println("⚠️ 음량은 0-100 사이여야 합니다.");
            return;
        }
        
        this.volume = volume;
        System.out.println("🔊 " + deviceName + " 음량을 " + volume + "으로 설정했습니다.");
    }
    
    public void launchApp(String appName) {
        this.currentApp = appName;
        System.out.println("📱 " + deviceName + "에서 " + appName + " 앱을 실행했습니다.");
    }
    
    @Override
    public void getStatus() {
        super.getStatus();
        System.out.println("현재 채널: " + channel);
        System.out.println("음량: " + volume);
        System.out.println("현재 앱: " + currentApp);
    }
}

// 스마트 홈 컨트롤러 - 모든 기기 관리
class SmartHomeController {
    private SmartDevice[] devices;
    private int deviceCount;
    
    public SmartHomeController(int maxDevices) {
        devices = new SmartDevice[maxDevices];
        deviceCount = 0;
    }
    
    public void addDevice(SmartDevice device) {
        if (deviceCount < devices.length) {
            devices[deviceCount] = device;
            deviceCount++;
            System.out.println("✅ " + device.getDeviceName() + "이(가) 스마트 홈에 추가되었습니다.");
        } else {
            System.out.println("⚠️ 더 이상 기기를 추가할 수 없습니다.");
        }
    }
    
    // 모든 기기 켜기 (다형성 활용)
    public void turnOnAllDevices() {
        System.out.println("🏠 모든 스마트 기기를 켭니다...");
        for (int i = 0; i < deviceCount; i++) {
            devices[i].turnOn();
        }
    }
    
    // 모든 기기 끄기
    public void turnOffAllDevices() {
        System.out.println("🏠 모든 스마트 기기를 끕니다...");
        for (int i = 0; i < deviceCount; i++) {
            devices[i].turnOff();
        }
    }
    
    // 전체 전력 소비량 계산
    public double getTotalPowerConsumption() {
        double total = 0.0;
        for (int i = 0; i < deviceCount; i++) {
            total += devices[i].getPowerConsumption();
        }
        return total;
    }
    
    // 모든 기기 상태 출력
    public void showAllDeviceStatus() {
        System.out.println("🏠 스마트 홈 전체 현황");
        System.out.println("=".repeat(40));
        for (int i = 0; i < deviceCount; i++) {
            devices[i].getStatus();
            System.out.println();
        }
        System.out.println("총 전력 소비량: " + getTotalPowerConsumption() + "W");
    }
}

// 실행 데모
public class SmartHomeDemo {
    public static void main(String[] args) {
        System.out.println("🏠 스마트 홈 시스템 데모");
        System.out.println("=".repeat(50));
        
        // 스마트 홈 컨트롤러 생성
        SmartHomeController homeController = new SmartHomeController(10);
        
        // 다양한 스마트 기기들 생성
        SmartLight livingRoomLight = new SmartLight("거실 조명", "Philips");
        SmartAirConditioner aircon = new SmartAirConditioner("거실 에어컨", "LG");
        SmartTV tv = new SmartTV("거실 TV", "Samsung");
        SmartLight bedRoomLight = new SmartLight("침실 조명", "IKEA");
        
        // 스마트 홈에 기기들 추가
        homeController.addDevice(livingRoomLight);
        homeController.addDevice(aircon);
        homeController.addDevice(tv);
        homeController.addDevice(bedRoomLight);
        
        System.out.println("\n=".repeat(50));
        System.out.println("🌅 아침 루틴 - 모든 기기 켜기");
        System.out.println("=".repeat(50));
        homeController.turnOnAllDevices();
        
        System.out.println("\n=".repeat(50));
        System.out.println("⚙️ 기기별 설정 조정");
        System.out.println("=".repeat(50));
        
        // 각 기기의 고유 기능 사용
        livingRoomLight.setBrightness(80);
        livingRoomLight.setColor("따뜻한 흰색");
        
        aircon.setTemperature(22);
        aircon.setMode("냉방");
        aircon.setFanSpeed(2);
        
        tv.changeChannel(11);
        tv.setVolume(30);
        tv.launchApp("Netflix");
        
        bedRoomLight.setBrightness(30);
        bedRoomLight.setColor("노란색");
        
        System.out.println("\n=".repeat(50));
        System.out.println("🎬 각 기기의 주요 기능 실행");
        System.out.println("=".repeat(50));
        
        // 다형성을 활용한 주요 기능 실행
        SmartDevice[] devices = {livingRoomLight, aircon, tv, bedRoomLight};
        for (SmartDevice device : devices) {
            device.performMainFunction(); // 각 기기마다 다른 동작 수행
        }
        
        System.out.println("\n=".repeat(50));
        System.out.println("📊 스마트 홈 현황");
        System.out.println("=".repeat(50));
        homeController.showAllDeviceStatus();
        
        System.out.println("=".repeat(50));
        System.out.println("🌙 취침 루틴 - 모든 기기 끄기");
        System.out.println("=".repeat(50));
        homeController.turnOffAllDevices();
        
        System.out.println("\n=".repeat(50));
        System.out.println("✅ 상속의 장점 확인");
        System.out.println("=".repeat(50));
        System.out.println("✅ 코드 중복 제거: 공통 기능(전원, 상태 등)을 한 번만 구현");
        System.out.println("✅ 확장성: 새로운 스마트 기기 추가 시 기본 구조 재사용");
        System.out.println("✅ 다형성: 배열로 다양한 기기를 동일하게 처리");
        System.out.println("✅ 유지보수성: 공통 기능 수정 시 부모 클래스만 변경");
    }
}