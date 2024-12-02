package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SoloDrive extends LinearOpMode {
    private RoboController roboController;

    @Override
    public void runOpMode() {
        roboController = new RoboController(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // while opMode is running, allow the methods for manipulating the wheels and arms
            // to be used and print data values
            moveRobot(gamepad1);

            telemetry.addData("FLW Motor Power", roboController.FLW.getPower());
            telemetry.addData("FRW Motor Power", roboController.FRW.getPower());
            telemetry.addData("BLW Motor Power", roboController.BLW.getPower());
            telemetry.addData("BRW Motor Power", roboController.BRW.getPower());

            telemetry.addData("Outtake Arm Motor Power", roboController.VLS.getPower());
            telemetry.addData("Intake Arm Motor Power", roboController.HLS.getPower());
            telemetry.addData("Outtake Claw Servo Position", roboController.outClaw.getPosition());
            telemetry.addData("Intake Claw Servo Position", roboController.inClaw.getPosition());
            telemetry.addData("Shoulder Servo Position", roboController.shoulder.getPosition());
            telemetry.addData("Elbow Servo Position", roboController.elbow.getPosition());

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    public void moveRobot(Gamepad gamepad){
        // ** the functions below could be for any bot, depending on what controls you want to use to drive the bot **
        double drivePower = 0;
        double strafePower = 0;
        double turnPower = 0;

        // moves the robot's (wheel) motors forward and back using the game pad 1 left joystick
        drivePower = -gamepad.left_stick_y;

        // moves the robot's (wheel) motors left and right using the game pad 1 left joystick
        strafePower = gamepad.left_stick_x;

        // turns the robot's (wheel) motors left and right using the game pad 1 right joystick
        turnPower = gamepad.right_stick_x;

        // drive, turn, and strafe logic
        // https://youtu.be/jRVUHapKx4o?si=1jVJ-ts7d2rkHCdq
        roboController.FLW.setPower(drivePower + turnPower + strafePower);
        roboController.FRW.setPower(drivePower - turnPower - strafePower);
        roboController.BLW.setPower(drivePower + turnPower - strafePower);
        roboController.BRW.setPower(drivePower - turnPower + strafePower);


        telemetry.addData("Drive Power", drivePower);
        telemetry.addData("Strafe Power", strafePower);
        telemetry.addData("Turn Power", turnPower);


        // ** the functions below are for the polymorphism bot **
        // presets servo positions

        // powers the robot's (arm) motors using the game pad 2 left joystick
        // (this will make the arm's motor power vary depending on how much you're using the joystick.
        // this can be changed later if we want the motor to have a constant power no matter what
        // value the joystick is giving)
        // linear slide(s) for outtake arm
        // (provides a threshold for deactivating motor power since joystick may not be at exactly 0)
        if(gamepad.right_trigger > 0.25) {
            roboController.HLS.setPower(-gamepad.right_trigger);
        } else if(gamepad.left_trigger > 0.25){
            roboController.HLS.setPower(gamepad.left_trigger);
        } else{
            roboController.HLS.setPower(0);
        }

        if(gamepad.right_bumper){
            roboController.VLS.setPower(1);
        } else if(gamepad.left_bumper) {
            roboController.VLS.setPower(-1);
        } else {
            roboController.VLS.setPower(0);
        }

        if(gamepad.circle && !roboController.inArmLastState){
            if(roboController.inArmState == 2){
                roboController.inArmState = 0;
            } else {
                roboController.inArmState++;
            }

            if(roboController.inArmState == 0){
                // neutral position
                roboController.shoulder.setPosition(0.2);
                roboController.elbow.setPosition(0.6);
            } else if(roboController.inArmState == 1){
                // dropoff position
                roboController.shoulder.setPosition(0.32);
                roboController.elbow.setPosition(0.2);
            } else if(roboController.inArmState == 2){
                // pickup position
                roboController.shoulder.setPosition(0.8);
                roboController.elbow.setPosition(0.3);
            }
        }

        roboController.inArmLastState = gamepad.circle;

        // 1 = open
        // 0 = closed
        if(gamepad.triangle && !roboController.inClawLastState) {
            if (roboController.inClaw.getPosition() <= 0.6) {
                roboController.inClaw.setPosition(1);
            }else{
                roboController.inClaw.setPosition(0.2);
            }
        }
        roboController.inClawLastState = gamepad.triangle;


        if(gamepad.x && !roboController.outClawLastState){
            if (roboController.outClaw.getPosition() < 0.5) {
                roboController.outClaw.setPosition(1);
            }else{
                roboController.outClaw.setPosition(0);
            }
        }

        roboController.outClawLastState = gamepad.x;

        if(gamepad.square){
            roboController.outClaw.setPosition(0.5);
        }
    }
}