package se.lexicon.meetingcalendarapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MeetingCalendarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingCalendarApiApplication.class, args);
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner runner () {
        return (args) -> {

        };
    }

}
