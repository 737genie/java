package Util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeExam {

	public static void main(String[] args) throws InterruptedException {
		// 날짜 타입
		// 기본 형태; yyyy-mm-dd HH:MM:SS
		
		// time 패키지
		
		// 1. LocalDate: 날짜 정보만 출력
		LocalDate today = LocalDate.now();
		System.out.println("오늘 날짜: "+today);
		
		// 2. LocalTime: 시간 정보만 출력
		LocalTime todayTime = LocalTime.now();
		System.out.println("현재 시각: "+todayTime);
		
		// 3. LocalDateTime
		LocalDateTime todayDateTime = LocalDateTime.now();
		System.out.println("현재 날짜와 시각: "+todayDateTime);
		
		// 4. ZonedDateTime 
		ZonedDateTime todayZonedDateTime = ZonedDateTime.now();
		System.out.println("현재 날짜, 시각, 위치: "+todayZonedDateTime);
		
		// 5. Period : 날짜 간의 간격 계산
		
		// Period 사용 예시
		// 1. 생일까지 남은 기간 계산
		
		LocalDate t1 = LocalDate.now();
		LocalDate t2 = LocalDate.of(t1.getYear(), 7, 28);
//		LocalDate t2 = LocalDate.of(t1.getYear()+1, 7, 28);
		
		if(t1.isAfter(t2)) {
			t2 = t2.plusYears(1);
		}
		
		Period birthday = Period.between(t1, t2);
		System.out.println("생일까지 남은 기간: "+birthday);
		
		int months = birthday.getMonths();
		int days = birthday.getDays();
		
		System.out.println("생일까지 남은 기간: "+months+"개월 "+days+"일");
		
		
		// 6. Duration : 시간 간격 계산

		// 급하냥의 다급함: "방금 '와플' 배송을 시작했어요! 
		// 배송 완료까지 총 몇 분, 몇 초가 걸렸는지 정확하게 기록해서 보고해야 해요. 
		// 배송 효율을 분석해야 한다고요!"
		
		System.out.println("====================================");
		
		LocalTime startTime = LocalTime.now();
		System.out.println("방금 '와플' 배송을 시작했어요!");
		System.out.println("급하냥: 배송 시작 시간 - "+startTime);
//		Thread.sleep(2500);
		
		LocalTime endTime = LocalTime.now();
		System.out.println("급하냥: 배송 완료 시간 - "+endTime);
		
		Duration delivery = Duration.between(startTime, endTime);
		
		System.out.println("배송에 걸린 시간: "+delivery.toMinutes()+"분 "+delivery.toSeconds()+"초");
		
		
		// 7. DateTimeFormatter : 날짜/시간을 원하는 문자열 형식으로 변환

		//잔망루피의 짜증: "서버에서 로그 데이터를 받았는데, 날짜가 '20250819113205' 
		// 이런 식으로 공백도 없이 붙어서 왔잖아! 이걸 누가 알아봐? 
		// '2025년 08월 19일 11시 32분 05초' 처럼 
		// 사람이 읽을 수 있는 형태로 빨리 바꿔줘!"
		
		String logData = "20250819113205";
		
		// 1) 해독기 생성
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		// 2) 해독기로 문자열을 날짜로 변환
		LocalDateTime logtime = LocalDateTime.parse(logData, parser);
		// 3) 사람이 보기 좋은 형식으로 바꾼다
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		// 4) 바뀐 형식을 보기 좋은 문자열로 변환
		String result = logtime.format(format);
		System.out.println(result);
		
		//바쁘개의 글로벌 배송 프로젝트
		// 바쁘개의 미션: "서울에서 2025년 9월 1일 오전 10시에 출발한 비행기가 
		// 11시간 30분을 날아서 LA에 도착했어요. LA 현지 도착 시간은 몇 시일까요? 
		// 그리고 프로젝트 마감일인 2025년 12월 31일까지 남은 기간은 정확히 몇 달 며칠인가요? 
		// 이 모든 걸 깔끔한 형식으로 보고해주세요!"
		
        // --- 1. ZonedDateTime으로 시차 계산 ---
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        ZoneId laZone = ZoneId.of("America/Los_Angeles");
        
        LocalDateTime departureTimeInSeoul = LocalDateTime.of(2025, 9, 1, 10, 0);
        ZonedDateTime seoulDeparture = ZonedDateTime.of(departureTimeInSeoul, seoulZone);

        // --- 2. Duration으로 비행 시간 계산 ---
        Duration flightDuration = Duration.ofHours(11).plusMinutes(30);
        ZonedDateTime laArrival = seoulDeparture.plus(flightDuration).withZoneSameInstant(laZone);

        // --- 3. Period로 남은 프로젝트 기간 계산 ---
        LocalDate projectStart = seoulDeparture.toLocalDate();
        LocalDate projectDeadline = LocalDate.of(2025, 12, 31);
        Period remainingPeriod = Period.between(projectStart, projectDeadline);

        // --- 4. DateTimeFormatter로 최종 보고서 작성 ---
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh:mm (zz)");
        
        System.out.println("--- 바쁘개의 글로벌 프로젝트 보고서 ---");
        System.out.println("출발 시간 (서울): " + seoulDeparture.format(formatter));
        System.out.println("총 비행 시간: " + flightDuration.toHours() + "시간 " + flightDuration.toMinutesPart() + "분");
        System.out.println("도착 시간 (LA): " + laArrival.format(formatter));
        System.out.println("프로젝트 마감까지 남은 기간: " + remainingPeriod.getMonths() + "개월 " + remainingPeriod.getDays() + "일");
		
	}
	
}
