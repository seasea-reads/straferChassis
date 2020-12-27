package org.firstinspires.ftc.teamcode.Opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

/*
* Created by C.C.
*/

@TeleOp(name = "DriverControlled", group = "TeleOp")

public class straferChassisOpmode extends OpMode {
    // Define robot
    RobotHardware robot = new RobotHardware();

    //Constants

    //Run once on init
    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    //Run once on start()
    @Override
    public void start() {
    }

    // Loops on start
    @Override
    public void loop() {
        //Basic Driving Controls
        double LeftY = gamepad1.left_stick_y;
        double LeftX = gamepad1.left_stick_x;
        double RightX = gamepad1.right_stick_x;

        // Calculates direction each word should turn(range -3 to 3)
        double frontLeft = LeftY + LeftX - RightX;
        double frontRight = LeftY + LeftX + RightX;
        double backLeft = LeftY - LeftX - RightX;
        double backRight = LeftY - LeftX + RightX;

        // Calculates the motor which requires the most power
        double largestVariable = Math.abs(frontLeft);

        if (largestVariable < Math.abs(frontRight))
            largestVariable = Math.abs(frontRight);

        if (largestVariable < Math.abs(backLeft))
            largestVariable = Math.abs(backLeft);

        if (largestVariable < Math.abs(backRight))
            largestVariable = Math.abs(backRight);

        // Sets range from -1 to 1
        frontLeft /= largestVariable;
        frontRight /= largestVariable;
        backLeft /= largestVariable;
        backRight /= largestVariable;

        // Sets motor powers
        robot.frontLeft.setPower(frontLeft);
        robot.frontRight.setPower(-frontRight);
        robot.backLeft.setPower(backLeft);
        robot.backRight.setPower(-backRight);
    }

    // Runs once - on stop
    @Override
    public void stop(){
    }

}
