package fr.sharkhendrix.zerogravity.common.data;

import lombok.Data;

@Data
public class Vehicle {

    private static Vehicle[] vehicles;

    static {
        vehicles = new Vehicle[2];

        Vehicle monkey = new Vehicle();
        monkey.setName("Monkey");
        monkey.setId(0);
        monkey.setResistance(0.2);
        monkey.setPower(1);
        monkey.setAcceleration(1);
        monkey.setMaxSpeed(3);
        monkey.setManeuverability(0.8);
        vehicles[0] = monkey;

        Vehicle cheetah = new Vehicle();
        cheetah.setName("Cheetah");
        cheetah.setId(1);
        cheetah.setResistance(0.3);
        cheetah.setPower(0.9);
        cheetah.setAcceleration(1.4);
        cheetah.setMaxSpeed(5);
        cheetah.setManeuverability(0.5);
        vehicles[1] = cheetah;
    }

    private String name;
    private int id;
    private double resistance;
    private double power;
    private double acceleration;
    private double maxSpeed;
    private double maneuverability;

    public static Vehicle get(int id) {
        return vehicles[id];
    }

}
