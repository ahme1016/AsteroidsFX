package dk.sdu.mmmi.cbse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystem {

    private int totalScore = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }

    @GetMapping("/update-score")
    public int UpdateTotalScore(@RequestParam(value = "point") Long point) {
        totalScore += point;
        return totalScore ;
    }

    @GetMapping("/score")
    public String getTotalScore() {
        return String.valueOf(totalScore);
    }

    @GetMapping("/reset")
    public void resetScore() {
        totalScore = 0;
    }



}
