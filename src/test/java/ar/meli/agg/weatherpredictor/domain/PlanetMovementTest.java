package ar.meli.agg.weatherpredictor.domain;

import org.junit.Test;

import static ar.meli.agg.weatherpredictor.utils.Constants.BETASOIDE;
import static ar.meli.agg.weatherpredictor.utils.Constants.VULCANO;
import static org.assertj.core.api.Assertions.assertThat;

public class PlanetMovementTest {


    @Test
    public void moveBetasoideAngleMoreThan0(){
        Planet vulcano = new Planet(BETASOIDE, new PolarPosition(2000,0), new Speed(3));
        vulcano.move();

        double expectedAngle = 3;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }

    @Test
    public void moveBetasoideAngleMoreThan360(){
        Planet vulcano = new Planet(BETASOIDE, new PolarPosition(2000,358), new Speed(3));
        vulcano.move();

        double expectedAngle = 1;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }

    @Test
    public void moveBetasoideAngleMoreThan360_2(){
        Planet vulcano = new Planet(BETASOIDE, new PolarPosition(2000,70), new Speed(3));
        vulcano.move();

        double expectedAngle = 73;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }

    @Test
    public void moveVulcanoAngleLessThan360(){
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,0), new Speed(-5));
        vulcano.move();

        double expectedAngle = 355;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }

    @Test
    public void moveVulcanoAngleLessThan360_2(){
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,3), new Speed(-5));
        vulcano.move();

        double expectedAngle = 358;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }

    @Test
    public void moveVulcanoAngleLessThan360_3(){
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,70), new Speed(-5));
        vulcano.move();

        double expectedAngle = 65;
        double currentAngle = vulcano.getPolarPosition().getAngle();

        assertThat(currentAngle).isEqualTo(expectedAngle);
    }
}