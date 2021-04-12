package com.vilin.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    public void test1(){
        LocalDate localDate = LocalDate.of(2019, 12, 05);
        System.out.println(localDate);
        localDate = LocalDate.now();
        System.out.println(localDate);

    }
    
    @Test
    public void test2() throws InterruptedException, ExecutionException { 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	
    	Callable<LocalDate> task = () -> LocalDate.parse("20210412", dtf);
    	
    	ExecutorService pool = Executors.newFixedThreadPool(10);
    	
    	List<Future<LocalDate>> results = new ArrayList<>();
    	
    	for(int i = 0; i < 10; i++) {
    		results.add(pool.submit(task));
    	}
    	
    	for (Future<LocalDate> future : results) {
			System.out.println(future.get());
		}
    	
    }
    
    //1.LocalDate, LocalTime, LocalDateTime
    @Test
    public void test3() {
    	LocalDateTime ldt = LocalDateTime.now();
    	System.out.println(ldt);
    	
    	LocalDateTime ldt2 = LocalDateTime.of(2021, 04, 12, 10, 33, 33);
    	System.out.println(ldt2);
    	
    	LocalDateTime ldt3 = ldt.plusYears(2);
    	System.out.println(ldt3);
    	
    	LocalDateTime ldt4 = ldt.minusMonths(2);
    	System.out.println(ldt4);
    	
    	System.out.println(ldt.getYear());
    	System.out.println(ldt.getMonth());
    	System.out.println(ldt.getDayOfMonth());
    	System.out.println(ldt.getHour());
    	System.out.println(ldt.getMinute());
    	System.out.println(ldt.getSecond());
    }
    
    //Instant:时间戳(以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值)
    @Test
    public void test4() {
    	Instant ins1 = Instant.now();//默认获取UTC时区
    	System.out.println(ins1);
    	
    	OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
    	System.out.println(odt);
    	
    	System.out.println(ins1.toEpochMilli());
    	
    	Instant ins2 = Instant.ofEpochMilli(60);
    	System.out.println(ins2);
    }
    
    //Duration: 计算两个时间之间的间隔
    //Period: 计算两个“日期”之间的间隔
    @Test
    public void test5() {
    	Instant ins1 = Instant.now();
    	
    	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Instant ins2 = Instant.now();
    	
    	Duration duration = Duration.between(ins1, ins2);
    	System.out.println(duration.toMillis());
    	
    	System.out.println("===============================================");
    	
    	LocalTime lt1 = LocalTime.now();
    	
    	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	LocalTime lt2 = LocalTime.now();
    	
    	duration.between(lt1, lt2);
    	
    	System.out.println(duration.toMillis());
    	
    }
    
    @Test
    public void test6() {
    	LocalDate ld1 = LocalDate.of(2016, 1, 1);
    	LocalDate ld2 = LocalDate.now();
    	
    	Period period = Period.between(ld1, ld2);
    	System.out.println(period);
    	
    	System.out.println(period.getYears());
    	System.out.println(period.getMonths());
    	System.out.println(period.getDays());
    }
    
    //TemporalAdjuster:时间矫正器
    @Test
    public void test7() {
    	LocalDateTime ldt = LocalDateTime.now();
    	System.out.println(ldt);
    	
    	LocalDateTime ldt2 = ldt.withDayOfMonth(10);
    	System.out.println(ldt2);
    	
    	LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    	System.out.println(ldt3);
    	
    	LocalDateTime ldt4 = ldt.with(TemporalAdjusters.firstDayOfNextYear());
    	System.out.println(ldt4);
    	
    	LocalDateTime ldt6 = ldt.with((l) -> {
    		LocalDateTime ldt5 = (LocalDateTime) l;
    		DayOfWeek dow = ldt5.getDayOfWeek();
    		
    		if(dow.equals(DayOfWeek.FRIDAY)) {
    			return ldt5.plusDays(3);
    		}else if(dow.equals(DayOfWeek.SATURDAY)) {
    			return ldt5.plusDays(2);
    		}else {
    			return ldt5.plusDays(1);
    		}
    	});
    	
    	System.out.println(ldt6);
    }
    
    //DateTimeFormatter : 格式化时间/日期
    @Test
    public void test8() {
    	DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
    	LocalDateTime ldt = LocalDateTime.now();
    	
    	String strDate = ldt.format(dtf);
        System.out.println(strDate);
    	
    	System.out.println("=======================================");
    	
    	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
    	String strDate2 = ldt.format(dtf2);
    	System.out.println(strDate2);
    	
    	
    	
//    	DateTime
}
